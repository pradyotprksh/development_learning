import 'package:collection/collection.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/device/device.dart';
import 'package:whatsapp/application/domain/domain.dart';

class StatusBloc extends Bloc<StatusEvent, StatusState> {
  StatusBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._deviceDetails,
  ) : super(const StatusState()) {
    on<FetchStatus>(_fetchStatusEvent);
    on<MarkStatusAsSeen>(_markStatusAsSeen);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final DeviceDetails _deviceDetails;

  void _markStatusAsSeen(
    MarkStatusAsSeen event,
    Emitter<StatusState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await _firebaseFirestoreService.setStatusSeen(
        event.statusId,
        StatusSeenDetails(
          userId: userId,
          statusId: event.statusId,
          userDeviceDetails: await _deviceDetails.getDeviceDetails(),
          seenOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
        ),
      );
    }
  }

  void _fetchStatusEvent(
    FetchStatus event,
    Emitter<StatusState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService.getStatus(),
        onData: (status) {
          if (status != null) {
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
}
