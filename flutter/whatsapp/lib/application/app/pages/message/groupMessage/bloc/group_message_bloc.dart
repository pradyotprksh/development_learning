import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/device/device.dart';
import 'package:whatsapp/application/domain/domain.dart';

class GroupMessageBloc extends Bloc<GroupMessageEvent, GroupMessageState> {
  GroupMessageBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._firebaseStorageService,
    this._deviceDetails,
  ) : super(const GroupMessageState()) {
    on<FetchGroupDetails>(_fetchGroupDetails);
    on<ToggleGroupEmojisOption>(_showEmojisOption);
    on<GetGroupAllMessages>(_getAllMessages);
    on<AddGroupMessage>(_addANewMessage);
    on<UpdateUsersDetails>(_updateUsersDetails);
    on<SaveGroupMessageEvent>(_saveMessage);
    on<GroupMessageAttachmentSelectedEvent>(_attachmentSelected);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final DeviceDetails _deviceDetails;
  final FirebaseStorageService _firebaseStorageService;

  void _showEmojisOption(
    ToggleGroupEmojisOption event,
    Emitter<GroupMessageState> emit,
  ) {
    emit(
      state.copyWith(
        isEmojiOptionVisible: event.shouldShow ?? !state.isEmojiOptionVisible,
      ),
    );
  }

  void _updateUsersDetails(
    UpdateUsersDetails event,
    Emitter<GroupMessageState> emit,
  ) async {
    final users = state.groupMessageDetails?.usersDetails;
    if (users != null) {
      await emit.forEach(
        users,
        onData: (usersDetails) => state.copyWith(
          usersDetails: usersDetails,
        ),
      );
    }
  }

  void _fetchGroupDetails(
    FetchGroupDetails event,
    Emitter<GroupMessageState> emit,
  ) async {
    await emit.forEach(
      _firebaseFirestoreService.getGroupMessageWithUsersDetails(event.groupId),
      onData: (details) {
        if (details?.groupMessageDetails?.groupId != null) {
          add(GetGroupAllMessages(details?.groupMessageDetails?.groupId ?? ''));
          add(const UpdateUsersDetails());
        }
        return state.copyWith(
          groupMessageDetails: details,
        );
      },
    );
  }

  void _getAllMessages(
    GetGroupAllMessages event,
    Emitter<GroupMessageState> emit,
  ) async {
    if (event.messageId.isNotEmpty) {
      await emit.forEach(
        _firebaseFirestoreService.getGroupMessages(event.messageId),
        onData: (messages) => state.copyWith(messages: messages),
      );
    }
  }

  void _addANewMessage(
    AddGroupMessage event,
    Emitter<GroupMessageState> emit,
  ) async {
    final currentUserId = _firebaseAuthService.getUserId();
    final groupId = state.groupMessageDetails?.groupMessageDetails?.groupId;
    if (currentUserId != null && groupId != null) {
      final deviceTimeStamp = DeviceUtilsMethods.getCurrentTimeStamp();
      final deviceDetails = await _deviceDetails.getDeviceDetails();

      var attachments = <FileInformationDetails>[];
      if (state.attachments.isNotEmpty) {
        for (final file in state.attachments) {
          emit(
            state.copyWith(uploadingFile: file),
          );
          String? imageUrl;
          String? firestorePath;
          try {
            firestorePath =
                CoreConstants.groupMessagesAttachments(groupId).replaceAll(
              CoreConstants.userIdPlaceholder,
              currentUserId,
            );
            final compressedFilePath =
                await FileCompressor.tryAllCompression(file.filePath);
            imageUrl = await _firebaseStorageService.uploadFile(
              compressedFilePath,
              firestorePath,
              {
                FirestoreItemKey.userId: currentUserId,
                ...deviceDetails.toStringMap(),
              },
            );
          } catch (e) {
            firestorePath = null;
            FirebaseUtils.recordFlutterError(e);
          }

          if (imageUrl != null) {
            attachments.add(
              file.copyFirestoreDetails(
                firestorePath ?? '',
                imageUrl,
              ),
            );
          }

          emit(
            state.copyWith(
              uploadingFile: null,
            ),
          );
        }
      }

      attachments.removeWhere((element) => element.fileUrl.isEmpty);

      await _firebaseFirestoreService.sendGroupMessage(
        SingleMessageDetails(
          message: event.message,
          sentByUserId: currentUserId,
          sentByUserDeviceDetails: deviceDetails,
          sentOnTimeStamp: deviceTimeStamp,
          isSystemMessage: false,
          attachments: attachments.isEmpty ? null : attachments,
        ),
        groupId,
      );
      await _firebaseFirestoreService.updateGroupMessage(
        groupId,
        {
          FirestoreItemKey.lastMessage:
              EncryptorService.encryptData(event.message),
          FirestoreItemKey.lastMessageOnTimeStamp: deviceTimeStamp,
          FirestoreItemKey.lastMessageByUserId: currentUserId,
        },
      );
      emit(
        state.copyWith(
          uploadingFile: null,
          attachments: [],
        ),
      );
    }
  }

  void _saveMessage(
    SaveGroupMessageEvent event,
    Emitter<GroupMessageState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      var messageId = event.messageId;
      if (event.directMessageId != null) {
        messageId =
            '${CoreConstants.directMessageCollection}/${event.directMessageId}/$messageId';
      } else if (event.groupId != null) {
        messageId =
            '${CoreConstants.groupMessageCollection}/${event.groupId}/$messageId';
      }

      unawaited(
        _firebaseFirestoreService.saveMessage(
          userId,
          event.messageId,
          SavedMessageDetails(
            messageSentByUserId: event.sentByUserId,
            messageId: messageId,
            savedOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
            userDeviceDetails: await _deviceDetails.getDeviceDetails(),
          ),
        ),
      );
    }
  }

  void _attachmentSelected(
    GroupMessageAttachmentSelectedEvent event,
    Emitter<GroupMessageState> emit,
  ) {
    final attachments = {
      ...event.fileInformation,
      ...state.attachments,
    };
    emit(
      state.copyWith(
        attachments: attachments.toList(),
      ),
    );
  }
}
