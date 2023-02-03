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
        _firebaseFirestoreService.getUserDetails(event.userId).stream,
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

        await _firebaseFirestoreService.createDirectMessage(
          DirectMessageDetails(
            users: [
              currentUserId,
              selectedUserId,
            ],
            createdByUserId: currentUserId,
            createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
            createdByUserDeviceDetails: await _deviceDetails.getDeviceDetails(),
            lastMessage: event.firstMessage,
            lastMessageOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
            lastMessageByUserId: currentUserId,
          ),
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
        _firebaseFirestoreService
            .getMessageDetails(currentUserId, selectedUserId)
            .stream,
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
            return state.copyWith(
              directMessageDetails: directMessageDetails,
            );
          }
        },
      );
    }
  }
}
