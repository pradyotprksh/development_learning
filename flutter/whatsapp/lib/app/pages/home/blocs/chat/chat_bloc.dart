import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class ChatBloc extends Bloc<ChatEvent, ChatState> {
  ChatBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
  ) : super(const ChatState()) {
    on<GetMessagesList>(_getCurrentUserMessages);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;

  void _getCurrentUserMessages(
    GetMessagesList event,
    Emitter<ChatState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService
            .getDirectMessagesForCurrentUser(userId)
            .stream,
        onData: (messages) => state.copyWith(
          messageListWithUserDetails: messages ?? [],
        ),
      );
    }
  }
}
