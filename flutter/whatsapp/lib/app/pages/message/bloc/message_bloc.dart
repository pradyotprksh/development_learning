import 'package:replay_bloc/replay_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class MessageBloc extends ReplayBloc<MessageEvent, MessageState> {
  MessageBloc(
    this._firebaseFirestoreService,
  ) : super(const MessageState()) {
    on<FetchSelectedUserDetails>(_fetchUserDetails);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;

  void _fetchUserDetails(
    FetchSelectedUserDetails event,
    Emitter<MessageState> emit,
  ) async {
    if (event.userId.isNotEmpty) {
      await emit.forEach(
        _firebaseFirestoreService.getUserDetails(event.userId).stream,
        onData: (userDetails) => state.copyWith(userDetails: userDetails),
      );
    }
  }
}
