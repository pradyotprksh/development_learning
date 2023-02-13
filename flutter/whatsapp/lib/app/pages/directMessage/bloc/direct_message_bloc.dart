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
    this._deviceDetails,
  ) : super(const DirectMessageState()) {
    on<FetchSelectedUserDetails>(_fetchUserDetails);
    on<GetMessages>(_fetchUserMessages);
    on<GetMessageDetails>(_fetchMessageDetails);
    on<CreateDirectMessage>(_createDirectMessage);
    on<ToggleEmojisOption>(_showEmojisOption);
    on<GetAllMessages>(_getAllMessages);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final DeviceDetails _deviceDetails;

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

  void _fetchUserMessages(
    GetMessages event,
    Emitter<DirectMessageState> emit,
  ) {}

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
        await _firebaseFirestoreService.sendMessage(
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

  void _fetchMessageDetails(
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
}
