import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class ContactUsBloc extends Bloc<ContactUsEvent, ContactUsState> {
  ContactUsBloc(
    this._firebaseAuthService,
    this._firebaseFirestoreService,
    this._deviceDetails,
  ) : super(const ContactUsState()) {
    on<ToggleDeviceInformation>(_toggleDeviceInformationOption);
    on<CreateIssue>(_createIssue);
  }

  final FirebaseAuthService _firebaseAuthService;
  final FirebaseFirestoreService _firebaseFirestoreService;
  final DeviceDetails _deviceDetails;

  void _toggleDeviceInformationOption(
    ToggleDeviceInformation event,
    Emitter<ContactUsState> emit,
  ) {
    emit(
      state.copyWith(
        shareDeviceInformation: !state.shareDeviceInformation,
      ),
    );
  }

  void _createIssue(
    CreateIssue event,
    Emitter<ContactUsState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      emit(
        state.copyWith(pageState: PageState.loading),
      );
      await _firebaseFirestoreService.createAnIssue(
        ContactUsDetails(
          description: event.description,
          createdByUserId: userId,
          createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
          userDeviceDetails: state.shareDeviceInformation
              ? await _deviceDetails.getDeviceDetails()
              : null,
        ),
      );
      emit(
        state.copyWith(pageState: PageState.success),
      );
    }
  }
}
