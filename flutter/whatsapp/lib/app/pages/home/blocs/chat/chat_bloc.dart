import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class ChatBloc extends Bloc<ChatEvent, ChatState> {
  ChatBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
  ) : super(const ChatState()) {
    on<GetDirectMessagesList>(_getDirectMessages);
    on<GetGroupMessagesList>(_getGroupMessages);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;

  void _getDirectMessages(
    GetDirectMessagesList event,
    Emitter<ChatState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService.getDirectMessagesFor(userId),
        onData: (messages) => state.copyWith(
          directMessageListWithUserDetails: messages ?? [],
        ),
      );
    }
  }

  void _getGroupMessages(
    GetGroupMessagesList event,
    Emitter<ChatState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService.getGroupMessagesFor(userId),
        onData: (messages) => state.copyWith(
          groupMessages: messages ?? [],
        ),
      );
    }
  }
}
