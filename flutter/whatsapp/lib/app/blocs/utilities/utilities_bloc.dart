import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class UtilitiesBloc extends Bloc<UtilitiesEvent, UtilitiesState> {
  UtilitiesBloc(
    this._firebaseAuthService,
    this._firebaseFirestoreService,
    this._deviceDetails,
  ) : super(const UtilitiesState()) {
    on<InitiateConnectivityCheck>(_onConnectivityCheck);
    on<ScreenshotTaken>(_onScreenshotTaken);
  }

  final FirebaseAuthService _firebaseAuthService;
  final FirebaseFirestoreService _firebaseFirestoreService;
  final DeviceDetails _deviceDetails;

  void _onConnectivityCheck(
    InitiateConnectivityCheck event,
    Emitter<UtilitiesState> emit,
  ) async {
    await emit.forEach(
      Connectivity().onConnectivityChanged,
      onData: (connectionResult) => state.copyWith(
        connectivityResult: connectionResult,
      ),
    );
  }

  void _onScreenshotTaken(
    ScreenshotTaken event,
    Emitter<UtilitiesState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await _firebaseFirestoreService.createScreenshot(
        ScreenshotDetails(
          userId: userId,
          userDeviceDetails: await _deviceDetails.getDeviceDetails(),
          createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
          route: event.name,
          arguments: event.arguments,
        ),
      );
    }
  }
}
