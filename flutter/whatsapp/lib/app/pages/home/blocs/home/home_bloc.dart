import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_fgbg/flutter_fgbg.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class HomeBloc extends Bloc<HomeEvent, HomeState> {
  HomeBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._deviceDetails,
  ) : super(
          const HomeState(),
        ) {
    on<UpdateLoginHistory>(_updateLoginHistory);
    on<DeleteCallLogs>(_deleteCallLogs);
    on<ApplicationBackgroundCheck>(_startApplicationBackgroundCheck);
    on<AskForPinConfirmation>(_askForPinConfirmation);
    on<PinVerified>(_onPinVerified);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final DeviceDetails _deviceDetails;

  void _updateLoginHistory(
    UpdateLoginHistory event,
    Emitter<HomeState> emit,
  ) async {
    if (!AppDetails.isDebugMode) {
      final userId = _firebaseAuthService.getUserId();
      if (userId != null) {
        final deviceDetails = await _deviceDetails.getDeviceDetails();
        await _firebaseFirestoreService.updateUserDetails(
          userId,
          {
            FirestoreItemKey.isOnline: true,
            FirestoreItemKey.userDeviceDetails: deviceDetails.toMap(),
          },
        );
        await _firebaseFirestoreService.setUserLogInHistory(
          LoginHistoryDetails(
            userId: userId,
            userDeviceDetails: await _deviceDetails.getDeviceDetails(),
            createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
          ),
        );
      }
    }
  }

  void _deleteCallLogs(
    DeleteCallLogs event,
    Emitter<HomeState> emit,
  ) {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      _firebaseFirestoreService.clearCallLogs(userId);
    }
  }

  void _startApplicationBackgroundCheck(
    ApplicationBackgroundCheck event,
    Emitter<HomeState> emit,
  ) async {
    if (AppDetails.isPhone) {
      final userId = _firebaseAuthService.getUserId();
      if (userId != null) {
        await emit.forEach(
          FGBGEvents.stream,
          onData: (data) {
            if (data == FGBGType.background) {
              _firebaseFirestoreService.updateUserDetails(
                userId,
                {
                  FirestoreItemKey.isOnline: false,
                },
              );
            } else {
              _firebaseFirestoreService.updateUserDetails(
                userId,
                {
                  FirestoreItemKey.isOnline: true,
                },
              );
            }
            return state;
          },
        );
      }
    }
  }

  void _askForPinConfirmation(
    AskForPinConfirmation event,
    Emitter<HomeState> emit,
  ) {
    if (event.lastPinConfirmationTimeStamp != null) {
      emit(
        state.copyWith(
          askForPinConfirmation: DeviceUtilsMethods.getTimeDifferenceInDays(
                  event.lastPinConfirmationTimeStamp) >
              FirebaseRemoteConfigService.pinConfirmationTimeValue(),
        ),
      );
    }
  }

  void _onPinVerified(
    PinVerified event,
    Emitter<HomeState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await _firebaseFirestoreService.updateUserDetails(
        userId,
        {
          FirestoreItemKey.lastPinConfirmationTimeStamp:
              DeviceUtilsMethods.getCurrentTimeStamp(),
        },
      );
    }
  }
}
