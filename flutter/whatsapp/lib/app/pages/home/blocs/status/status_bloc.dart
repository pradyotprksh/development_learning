import 'package:collection/collection.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class StatusBloc extends Bloc<StatusEvent, StatusState> {
  StatusBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
  ) : super(const StatusState()) {
    on<FetchStatus>(_fetchStatusEvent);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;

  void _fetchStatusEvent(
    FetchStatus event,
    Emitter<StatusState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    await emit.forEach(
      _firebaseFirestoreService.getStatus().stream,
      onData: (status) {
        if (status != null && userId != null) {
          if (status.isEmpty) {
            return const StatusState();
          } else {
            var currentUserStatus = status.firstWhereOrNull(
              (element) => element.userId == userId,
            );
            var otherStatus = status
                .where(
                  (element) => element.userId != userId,
                )
                .toList();
            return state.copyWith(
              otherStatus: otherStatus,
              currentUserStatus: currentUserStatus,
            );
          }
        }
        return state;
      },
    );
  }
}