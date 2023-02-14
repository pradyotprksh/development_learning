import 'dart:async';

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
    on<GetAvailableContacts>(_getAvailableContacts);
    on<GetNotAvailableContacts>(_getNotAvailableContacts);
    on<RefreshContacts>(_refreshContacts);
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

  void _getAvailableContacts(
    GetAvailableContacts event,
    Emitter<SelectContactState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService.getUserContactsAvailable(userId).stream,
        onData: (event) => state.copyWith(
          existingAccount: event,
        ),
      );
    }
  }

  void _getNotAvailableContacts(
    GetNotAvailableContacts event,
    Emitter<SelectContactState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService.getUserContactsNotAvailable(userId),
        onData: (event) => state.copyWith(
          nonExistingAccount: event,
        ),
      );
    }
  }

  void _gotLocalContactsEvent(
    LocalContactsDetails event,
    Emitter<SelectContactState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();

    if (userId != null) {
      final isDetailsAlreadyPresent = await _firebaseFirestoreService
              .isContactsAvailableListPresent(userId) &&
          await _firebaseFirestoreService
              .isContactsNotAvailableListPresent(userId);
      if (!isDetailsAlreadyPresent) {
        await _refreshContactsOnServer(event.localContacts, userId);
      }

      emit(
        state.copyWith(
          pageState: PageState.success,
          checkForContacts: false,
        ),
      );
    } else {
      emit(
        state.copyWith(
          pageState: PageState.error,
          checkForContacts: false,
        ),
      );
    }
    add(const GetAvailableContacts());
    add(const GetNotAvailableContacts());
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

  Future<void> _refreshContactsOnServer(
    List<Contact> localContacts,
    String currentUserId,
  ) async {
    for (final contact in localContacts) {
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
        if (userDetails.userId != currentUserId) {
          await _firebaseFirestoreService.setContactAvailableDetails(
            currentUserId,
            ContactsAvailableDetails(
              userId: userDetails.userId,
              detailsFetchedOn: DeviceUtilsMethods.getCurrentTimeStamp(),
              userDeviceDetails: await _deviceDetails.getDeviceDetails(),
            ),
          );
        }
      } else {
        await _firebaseFirestoreService.setContactNotAvailableDetails(
          currentUserId,
          ContactsNotAvailableDetails(
            displayName: contact.displayName,
            phoneNumber:
                (contact.phones?.isEmpty == true ? null : contact.phones)
                    ?.first
                    .value,
            emailId: (contact.emails?.isEmpty == true ? null : contact.emails)
                ?.first
                .value,
            detailsFetchedOn: DeviceUtilsMethods.getCurrentTimeStamp(),
            userDeviceDetails: await _deviceDetails.getDeviceDetails(),
          ),
        );
      }
    }
  }

  void _refreshContacts(
    RefreshContacts event,
    Emitter<SelectContactState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      try {
        await _refreshContactsOnServer(event.localContacts, userId);
        emit(
          state.copyWith(
            pageState: PageState.success,
          ),
        );
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
      }
    }
  }
}
