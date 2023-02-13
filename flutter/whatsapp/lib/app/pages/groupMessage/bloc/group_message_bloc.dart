import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class GroupMessageBloc extends Bloc<GroupMessageEvent, GroupMessageState> {
  GroupMessageBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._deviceDetails,
  ) : super(const GroupMessageState()) {
    on<FetchGroupDetails>(_fetchGroupDetails);
    on<ToggleGroupEmojisOption>(_showEmojisOption);
    on<GetGroupAllMessages>(_getAllMessages);
    on<AddGroupMessage>(_addANewMessage);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final DeviceDetails _deviceDetails;

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

  void _fetchGroupDetails(
    FetchGroupDetails event,
    Emitter<GroupMessageState> emit,
  ) async {
    await emit.forEach(
      _firebaseFirestoreService.getGroupMessageWithUsersDetails(event.groupId),
      onData: (details) {
        if (details?.groupMessageDetails?.groupId != null) {
          add(GetGroupAllMessages(details?.groupMessageDetails?.groupId ?? ''));
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
      await _firebaseFirestoreService.sendGroupMessage(
        SingleMessageDetails(
          message: event.message,
          sentByUserId: currentUserId,
          sentByUserDeviceDetails: await _deviceDetails.getDeviceDetails(),
          sentOnTimeStamp: deviceTimeStamp,
          isSystemMessage: false,
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
    }
  }
}
