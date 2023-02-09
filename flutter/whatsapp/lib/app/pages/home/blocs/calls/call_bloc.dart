import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class CallBloc extends Bloc<CallEvent, CallState> {
  CallBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
  ) : super(const CallState()) {
    on<FetchCurrentUserCalls>(_fetchCurrentUserCalls);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;

  void _fetchCurrentUserCalls(
    FetchCurrentUserCalls event,
    Emitter<CallState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService.getCurrentUserCalls(userId),
        onData: (details) => state.copyWith(
          userGroupCallDetails: details,
        ),
      );
    }
  }
}
