import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class AuthenticationBloc
    extends Bloc<AuthenticationEvent, AuthenticationState> {
  AuthenticationBloc(
    this._firebaseAuthService,
    this._firebaseFirestoreService,
    this._deviceDetails,
  ) : super(const AuthenticationState()) {
    on<CheckForRemoteConfigs>(_checkForRemoteConfigs);
    on<CheckForAuthenticationStatus>(_checkForAuthenticationStatus);
    on<UserAuthenticatedEvent>(_userAuthenticatedEvent);
  }

  final FirebaseAuthService _firebaseAuthService;
  final FirebaseFirestoreService _firebaseFirestoreService;
  final DeviceDetails _deviceDetails;

  void _checkForAuthenticationStatus(
    CheckForAuthenticationStatus event,
    Emitter<AuthenticationState> emit,
  ) async {
    if (_firebaseAuthService.isUserLoggedIn()) {
      try {
        final firebaseUserDetails = _firebaseAuthService.getUserDetails();
        if (firebaseUserDetails != null) {
          final firestoreUserDetails = await _firebaseFirestoreService
              .getUserDetails(firebaseUserDetails.uid)
              .first;

          if (firestoreUserDetails != null) {
            final deviceDetails = await _deviceDetails.getDeviceDetails();
            await _firebaseFirestoreService.updateUserDetails(
              firebaseUserDetails.uid,
              {
                FirestoreItemKey.isPhoneNumberVerified:
                    firestoreUserDetails.phoneNumber ==
                        firestoreUserDetails.phoneNumber,
                FirestoreItemKey.isEmailVerified:
                    firebaseUserDetails.emailVerified,
                FirestoreItemKey.userDeviceDetails: deviceDetails.toMap(),
              },
            );
          }
        }
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
      }
      emit(
        AuthenticationState(
          authenticationState: AuthenticationStatus.authenticated,
          firebaseUserDetails: _firebaseAuthService.getUserDetails(),
        ),
      );
    } else {
      emit(
        const AuthenticationState(
          authenticationState: AuthenticationStatus.unauthenticated,
        ),
      );
    }
  }

  void _userAuthenticatedEvent(
    UserAuthenticatedEvent event,
    Emitter<AuthenticationState> emit,
  ) {
    emit(
      AuthenticationState(
        authenticationState: AuthenticationStatus.authenticated,
        firebaseUserDetails: event.firebaseUserDetails,
      ),
    );
  }

  void _checkForRemoteConfigs(
    CheckForRemoteConfigs event,
    Emitter<AuthenticationState> emit,
  ) {
    if (FirebaseRemoteConfigService.isApplicationDown()) {
      emit(
        state.copyWith(
          authenticationState: AuthenticationStatus.applicationDown,
        ),
      );
    } else {
      add(const CheckForAuthenticationStatus());
    }
  }
}
