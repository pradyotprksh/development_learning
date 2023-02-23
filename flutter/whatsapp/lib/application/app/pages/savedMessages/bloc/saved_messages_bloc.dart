import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';

class SavedMessagesBloc extends Bloc<SavedMessagesEvent, SavedMessagesState> {
  SavedMessagesBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
  ) : super(const SavedMessagesState()) {
    on<FetchSavedMessages>(_getSavedMessages);
    on<ClearSavedMessages>(_clearSavedMessages);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;

  void _getSavedMessages(
    FetchSavedMessages event,
    Emitter<SavedMessagesState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService.getSavedMessages(userId),
        onData: (details) => state.copyWith(
          messages: details,
        ),
      );
    }
  }

  void _clearSavedMessages(
    ClearSavedMessages event,
    Emitter<SavedMessagesState> emit,
  ) async {
    try {
      emit(state.copyWith(pageState: PageState.loading));
      final userId = _firebaseAuthService.getUserId();
      if (userId != null) {
        await _firebaseFirestoreService.deleteAllSavedMessages(userId);
      }
    } finally {
      emit(state.copyWith(pageState: PageState.idle));
    }
  }
}
