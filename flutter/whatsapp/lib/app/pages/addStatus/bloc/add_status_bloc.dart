import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class AddStatusBloc extends Bloc<AddStatusEvent, AddStatusState> {
  AddStatusBloc(
    String currentFont,
    this._firebaseAuthService,
    this._firebaseFirestoreService,
    this._deviceDetails,
  ) : super(AddStatusState(currentFontFamily: currentFont)) {
    on<UpdateBackgroundColor>(_backgroundColorChangeEvent);
    on<UpdateFontFamily>(_fontChangeEvent);
    on<UploadStatus>(_uploadStatusEvent);
  }

  final FirebaseAuthService _firebaseAuthService;
  final FirebaseFirestoreService _firebaseFirestoreService;
  final DeviceDetails _deviceDetails;

  void _backgroundColorChangeEvent(
    UpdateBackgroundColor event,
    Emitter<AddStatusState> emit,
  ) {
    emit(state.copyWith(chosenColor: event.newBackgroundColor));
  }

  void _fontChangeEvent(
    UpdateFontFamily event,
    Emitter<AddStatusState> emit,
  ) {
    emit(state.copyWith(currentFontFamily: event.newFont));
  }

  void _uploadStatusEvent(
    UploadStatus event,
    Emitter<AddStatusState> emit,
  ) async {
    final status = event.statusValue;
    final color = state.chosenColor;
    final fontFamily = state.currentFontFamily;
    final deviceDetails = await _deviceDetails.getDeviceDetails();

    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      emit(state.copyWith(pageState: PageState.loading));

      try {
        await _firebaseFirestoreService.setStatus(
          StatusDetails(
            status: status,
            color: color,
            fontFamily: fontFamily,
            userId: userId,
            userDeviceDetails: deviceDetails,
            createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
          ),
        );

        emit(state.copyWith(pageState: PageState.success));
      } catch (e) {
        UtilsLogger.debugLog(e);

        emit(state.copyWith(pageState: PageState.error));
      }
    }
  }
}
