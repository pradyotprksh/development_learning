import 'package:contacts_service/contacts_service.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class SelectContactBloc extends Bloc<SelectContactEvent, SelectContactState> {
  SelectContactBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._deviceDetails,
  ) : super(const SelectContactState()) {
    on<ContactPermissionStatus>(_contactPermissionStatusEvent);
    on<LocalContactsDetails>(_gotLocalContactsEvent);
    on<UpdatePageStateEvent>(_updatePageStateEvent);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final DeviceDetails _deviceDetails;

  void _contactPermissionStatusEvent(
    ContactPermissionStatus event,
    Emitter<SelectContactState> emit,
  ) {
    emit(
      state.copyWith(permissionStatus: event.permissionStatus),
    );
  }

  void _gotLocalContactsEvent(
    LocalContactsDetails event,
    Emitter<SelectContactState> emit,
  ) async {
    var existingAccount = <UserDetails>[];
    var nonExistingAccount = <Contact>[];
    for (final contact in event.localContacts) {
      final emailAddresses = contact.emails ?? [];
      final phoneNumbers = contact.phones ?? [];
      UserDetails? userDetails;
      for (final phoneNumber in phoneNumbers) {
        if (phoneNumber.value != null) {
          userDetails =
              await _firebaseFirestoreService.getUserAccountByPhoneNumber(
            phoneNumber.value!,
          );
        }
      }
      if (userDetails == null) {
        for (final emailAddress in emailAddresses) {
          if (emailAddress.value != null) {
            userDetails =
                await _firebaseFirestoreService.getUserAccountByEmailAddress(
              emailAddress.value!,
            );
          }
        }
      }

      if (userDetails != null) {
        final userId = _firebaseAuthService.getUserId();
        if (userId != null) {
          await _firebaseFirestoreService.setContactAvailableDetails(
            userId,
            ContactsAvailableDetails(
              userId: userDetails.userId,
              detailsFetchedOn: DeviceUtilsMethods.getCurrentTimeStamp(),
              userDeviceDetails: await _deviceDetails.getDeviceDetails(),
            ),
          );
        }
        existingAccount.add(userDetails);
      } else {
        nonExistingAccount.add(contact);
      }
    }

    emit(
      state.copyWith(
        existingAccount: existingAccount,
        nonExistingAccount: nonExistingAccount,
        pageState: PageState.success,
        localContacts: event.localContacts,
        checkForContacts: false,
      ),
    );
  }

  void _updatePageStateEvent(
    UpdatePageStateEvent event,
    Emitter<SelectContactState> emit,
  ) {
    emit(
      state.copyWith(
        pageState: event.pageState,
      ),
    );
  }
}
