import 'package:flutter_bloc/flutter_bloc.dart';
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
}
