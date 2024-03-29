import 'dart:async';

import 'package:collection/collection.dart';
import 'package:replay_bloc/replay_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class DirectMessageBloc
    extends ReplayBloc<DirectMessageEvent, DirectMessageState> {
  DirectMessageBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._firebaseStorageService,
    this._deviceDetails,
  ) : super(const DirectMessageState()) {
    on<FetchSelectedMessageDetails>(_fetchMessageDetails);
    on<FetchSelectedUserDetails>(_fetchUserDetails);
    on<GetMessageDetails>(_getMessageDetails);
    on<CreateDirectMessage>(_createDirectMessage);
    on<ToggleEmojisOption>(_showEmojisOption);
    on<GetAllMessages>(_getAllMessages);
    on<AddMessage>(_addANewMessage);
    on<SaveDirectMessageEvent>(_saveMessage);
    on<DirectMessageAttachmentSelectedEvent>(_attachmentSelected);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final FirebaseStorageService _firebaseStorageService;
  final DeviceDetails _deviceDetails;

  void _fetchMessageDetails(
    FetchSelectedMessageDetails event,
    Emitter<DirectMessageState> emit,
  ) async {
    emit(state.copyWith(pageState: PageState.loading));
    await emit.forEach(
      _firebaseFirestoreService.getDirectMessageDetails(
        event.messageId,
      ),
      onData: (directMessageDetails) {
        emit(state.copyWith(pageState: PageState.success));
        if (directMessageDetails == null &&
            state.directMessageDetails != null) {
          return DirectMessageState(
            directMessageDetails: null,
            userDetails: state.userDetails,
            pageState: state.pageState,
          );
        } else {
          final currentUserId = _firebaseAuthService.getUserId();
          add(
            FetchSelectedUserDetails(
              directMessageDetails?.users
                      .where((element) => element != currentUserId)
                      .firstOrNull ??
                  '',
            ),
          );
          add(GetAllMessages(directMessageDetails?.messageId ?? ''));
          return state.copyWith(
            directMessageDetails: directMessageDetails,
          );
        }
      },
    );
  }

  void _fetchUserDetails(
    FetchSelectedUserDetails event,
    Emitter<DirectMessageState> emit,
  ) async {
    if (event.userId.isNotEmpty) {
      await emit.forEach(
        _firebaseFirestoreService.getUserDetails(event.userId),
        onData: (userDetails) {
          if (state.directMessageDetails == null) {
            add(const GetMessageDetails());
          }
          return state.copyWith(userDetails: userDetails);
        },
      );
    }
  }

  void _createDirectMessage(
    CreateDirectMessage event,
    Emitter<DirectMessageState> emit,
  ) async {
    final currentUserId = _firebaseAuthService.getUserId();
    final selectedUserId = state.userDetails?.userId;

    if (currentUserId != null && selectedUserId != null) {
      try {
        emit(state.copyWith(pageState: PageState.loading));

        final deviceTimeStamp = DeviceUtilsMethods.getCurrentTimeStamp();

        final directMessageId =
            await _firebaseFirestoreService.createDirectMessage(
          DirectMessageDetails(
            users: [
              currentUserId,
              selectedUserId,
            ],
            createdByUserId: currentUserId,
            createdOnTimeStamp: deviceTimeStamp,
            createdByUserDeviceDetails: await _deviceDetails.getDeviceDetails(),
            lastMessage: event.firstMessage,
            lastMessageOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
            lastMessageByUserId: currentUserId,
          ),
        );
        await _firebaseFirestoreService.sendDirectMessage(
          SingleMessageDetails(
            message: event.firstMessage,
            sentByUserId: currentUserId,
            sentByUserDeviceDetails: await _deviceDetails.getDeviceDetails(),
            sentOnTimeStamp: deviceTimeStamp,
            isSystemMessage: true,
          ),
          directMessageId,
        );

        emit(state.copyWith(pageState: PageState.success));
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
        emit(state.copyWith(pageState: PageState.error));
      }
    }
  }

  void _getMessageDetails(
    GetMessageDetails event,
    Emitter<DirectMessageState> emit,
  ) async {
    final currentUserId = _firebaseAuthService.getUserId();
    final selectedUserId = state.userDetails?.userId;

    if (currentUserId != null && selectedUserId != null) {
      emit(state.copyWith(pageState: PageState.loading));
      await emit.forEach(
        _firebaseFirestoreService.getMessageDetails(
          currentUserId,
          selectedUserId,
        ),
        onData: (directMessageDetails) {
          emit(state.copyWith(pageState: PageState.success));
          if (directMessageDetails == null &&
              state.directMessageDetails != null) {
            return DirectMessageState(
              directMessageDetails: null,
              userDetails: state.userDetails,
              pageState: state.pageState,
            );
          } else {
            add(GetAllMessages(directMessageDetails?.messageId ?? ''));
            return state.copyWith(
              directMessageDetails: directMessageDetails,
            );
          }
        },
      );
    }
  }

  void _showEmojisOption(
    ToggleEmojisOption event,
    Emitter<DirectMessageState> emit,
  ) {
    emit(
      state.copyWith(
        isEmojiOptionVisible: event.shouldShow ?? !state.isEmojiOptionVisible,
      ),
    );
  }

  void _getAllMessages(
    GetAllMessages event,
    Emitter<DirectMessageState> emit,
  ) async {
    if (event.messageId.isNotEmpty) {
      await emit.forEach(
        _firebaseFirestoreService.getDirectMessages(event.messageId),
        onData: (messages) => state.copyWith(messages: messages),
      );
    }
  }

  void _addANewMessage(
    AddMessage event,
    Emitter<DirectMessageState> emit,
  ) async {
    final currentUserId = _firebaseAuthService.getUserId();
    final directMessageId = state.directMessageDetails?.messageId;
    final otherUserId = state.userDetails?.userId;
    if (currentUserId != null && directMessageId != null) {
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
                CoreConstants.directMessagesAttachments(directMessageId)
                    .replaceAll(
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

      await _firebaseFirestoreService.sendDirectMessage(
        SingleMessageDetails(
          message: event.message,
          sentByUserId: currentUserId,
          sentByUserDeviceDetails: deviceDetails,
          sentOnTimeStamp: deviceTimeStamp,
          isSystemMessage: false,
          sentToUserId: otherUserId,
          attachments: attachments.isEmpty ? null : attachments,
        ),
        directMessageId,
      );
      await _firebaseFirestoreService.updateDirectMessage(
        directMessageId,
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
    SaveDirectMessageEvent event,
    Emitter<DirectMessageState> emit,
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
    DirectMessageAttachmentSelectedEvent event,
    Emitter<DirectMessageState> emit,
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
