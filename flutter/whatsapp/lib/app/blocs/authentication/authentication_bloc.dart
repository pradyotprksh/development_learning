import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:local_auth/local_auth.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class AuthenticationBloc
    extends HydratedBloc<AuthenticationEvent, AuthenticationState> {
  AuthenticationBloc(
    this._firebaseAuthService,
    this._firebaseFirestoreService,
    this._deviceDetails,
    this._localAuth,
  ) : super(const AuthenticationState()) {
    on<CheckForRemoteConfigs>(_checkForRemoteConfigs);
    on<CheckForAuthenticationStatus>(_checkForAuthenticationStatus);
    on<UserAuthenticatedEvent>(_userAuthenticatedEvent);
    on<UnAuthenticateUserEvent>(_unAuthenticateUserEvent);
    on<ListenToAuthStateChange>(_listenToAuthStateChangeEvent);
    on<CheckForBiometric>(_checkFOrBiometric);
    on<EnableBiometric>(_enabledBiometric);
    on<DisableBiometric>(_disableBiometric);
    on<ChangeLocalAuthTime>(_changeLocalAuthTime);
    on<ToggleMessageVisibilityInNotification>(
        _toggleMessageVisibilityInNotification);
  }

  final FirebaseAuthService _firebaseAuthService;
  final FirebaseFirestoreService _firebaseFirestoreService;
  final DeviceDetails _deviceDetails;
  final LocalAuthentication _localAuth;

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

            final firebaseEmailId = firebaseUserDetails.email;
            final firebasePhoneNumber = firebaseUserDetails.phoneNumber;
            final firebaseName = firebaseUserDetails.displayName;
            final firestoreEmailId = firestoreUserDetails.emailId;
            final firestorePhoneNumber = firestoreUserDetails.phoneNumber;
            final firestoreName = firestoreUserDetails.name;

            await _firebaseFirestoreService.updateUserDetails(
              firebaseUserDetails.uid,
              {
                FirestoreItemKey.isPhoneNumberVerified:
                    firestoreUserDetails.phoneNumber ==
                        firestoreUserDetails.phoneNumber,
                FirestoreItemKey.isEmailVerified:
                    firebaseUserDetails.emailVerified,
                FirestoreItemKey.userDeviceDetails: deviceDetails.toMap(),
                if (firebaseEmailId != null &&
                    firebaseEmailId != firestoreEmailId)
                  FirestoreItemKey.emailId: firebaseEmailId,
                if (firebasePhoneNumber != null &&
                    firebasePhoneNumber != firestorePhoneNumber)
                  FirestoreItemKey.phoneNumber: firebasePhoneNumber,
                if (firebaseName != null && firebaseName != firestoreName)
                  FirestoreItemKey.name: firebaseName,
              },
            );
          }
        }
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
      }
      if (state.isLocalAuthEnabled && state.isLocalAuthAvailable) {
        var shouldContinueWithAuth = true;
        final difference = DeviceUtilsMethods.getTimeDifferenceInMins(
          state.lastLocalAuthAsk,
        );
        switch (state.localAuthTime) {
          case LocalAuthTime.none:
            shouldContinueWithAuth = true;
            break;
          case LocalAuthTime.immediately:
            shouldContinueWithAuth = true;
            break;
          case LocalAuthTime.after1Minute:
            shouldContinueWithAuth = difference > 1;
            break;
          case LocalAuthTime.after30Minutes:
            shouldContinueWithAuth = difference > 30;
            break;
        }

        if (shouldContinueWithAuth) {
          try {
            final didAuthenticate = await _localAuth.authenticate(
              localizedReason:
                  '${_firebaseAuthService.getUserDetails()?.displayName}',
            );
            if (didAuthenticate) {
              emit(
                state
                    .authenticated(_firebaseAuthService.getUserDetails())
                    .copyWith(
                      lastLocalAuthAsk:
                          DeviceUtilsMethods.getCurrentTimeStamp(),
                    ),
              );
            } else {
              add(const CheckForAuthenticationStatus());
            }
          } catch (e) {
            FirebaseUtils.recordFlutterError(e);
            emit(
              state.authenticated(_firebaseAuthService.getUserDetails()),
            );
          }
        } else {
          emit(
            state.authenticated(_firebaseAuthService.getUserDetails()),
          );
        }
      } else {
        emit(
          state.authenticated(_firebaseAuthService.getUserDetails()),
        );
      }
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
      state.authenticated(event.firebaseUserDetails),
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
      add(const CheckForBiometric());
    }
  }

  void _unAuthenticateUserEvent(
    UnAuthenticateUserEvent event,
    Emitter<AuthenticationState> emit,
  ) {
    emit(
      state.copyWith(
        authenticationState: AuthenticationStatus.unauthenticated,
      ),
    );
  }

  void _listenToAuthStateChangeEvent(
    ListenToAuthStateChange event,
    Emitter<AuthenticationState> emit,
  ) async {
    await emit.forEach(
      _firebaseAuthService.isUserAuthenticated(),
      onData: (userDetails) => state.copyWith(
        authenticationState: userDetails != null
            ? AuthenticationStatus.authenticated
            : AuthenticationStatus.unauthenticated,
      ),
    );
  }

  void _checkFOrBiometric(
    CheckForBiometric event,
    Emitter<AuthenticationState> emit,
  ) async {
    final canAuthenticateWithBiometrics = await _localAuth.canCheckBiometrics;
    final canAuthenticate =
        canAuthenticateWithBiometrics || await _localAuth.isDeviceSupported();
    final availableBiometrics = await _localAuth.getAvailableBiometrics();

    emit(
      state.copyWith(
        isLocalAuthAvailable: canAuthenticate && availableBiometrics.isNotEmpty,
      ),
    );

    add(const CheckForAuthenticationStatus());
  }

  void _enabledBiometric(
    EnableBiometric event,
    Emitter<AuthenticationState> emit,
  ) async {
    try {
      final didAuthenticate = await _localAuth.authenticate(
        localizedReason: event.message,
      );
      if (didAuthenticate) {
        emit(
          state.copyWith(
            isLocalAuthEnabled: true,
            localAuthTime: LocalAuthTime.after1Minute,
            lastLocalAuthAsk: DeviceUtilsMethods.getCurrentTimeStamp(),
          ),
        );
      }
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
    }
  }

  void _disableBiometric(
    DisableBiometric event,
    Emitter<AuthenticationState> emit,
  ) {
    emit(
      state.copyWith(
        isLocalAuthEnabled: false,
        localAuthTime: LocalAuthTime.none,
      ),
    );
  }

  void _changeLocalAuthTime(
    ChangeLocalAuthTime event,
    Emitter<AuthenticationState> emit,
  ) {
    emit(
      state.copyWith(localAuthTime: event.authTime),
    );
  }

  void _toggleMessageVisibilityInNotification(
    ToggleMessageVisibilityInNotification event,
    Emitter<AuthenticationState> emit,
  ) {
    emit(
      state.copyWith(
        showMessageOnLock: !state.showMessageOnLock,
      ),
    );
  }

  @override
  AuthenticationState? fromJson(Map<String, dynamic> json) =>
      AuthenticationState(
        isLocalAuthAvailable: state.isLocalAuthAvailable,
        lastLocalAuthAsk: json[Keys.lastLocalAuthAsk] as int? ?? 0,
        isLocalAuthEnabled: json[Keys.isLocalAuthEnabled] as bool? ?? false,
        showMessageOnLock:
            json[Keys.showMessagesInNotificationWhenBiometricEnable] as bool? ??
                true,
        localAuthTime: (json[Keys.localAuthTime] as String? ?? '')
                .stringToEnum(LocalAuthTime.values) ??
            LocalAuthTime.none,
        authenticationState: state.authenticationState,
        firebaseUserDetails: state.firebaseUserDetails,
      );

  @override
  Map<String, dynamic>? toJson(AuthenticationState state) => <String, dynamic>{
        Keys.isLocalAuthEnabled: state.isLocalAuthEnabled,
        Keys.showMessagesInNotificationWhenBiometricEnable:
            state.showMessageOnLock,
        Keys.localAuthTime: state.localAuthTime.name,
        Keys.lastLocalAuthAsk: state.lastLocalAuthAsk,
      };
}
