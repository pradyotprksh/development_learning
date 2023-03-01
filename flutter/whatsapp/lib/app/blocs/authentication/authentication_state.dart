import 'package:equatable/equatable.dart';
import 'package:firebase_auth/firebase_auth.dart';

enum AuthenticationStatus {
  unknown,
  authenticated,
  unauthenticated,
  applicationDown,
}

enum LocalAuthTime {
  none,
  immediately,
  after1Minute,
  after30Minutes,
}

class AuthenticationState extends Equatable {
  const AuthenticationState({
    this.authenticationState = AuthenticationStatus.unauthenticated,
    this.firebaseUserDetails,
    this.lastLocalAuthAsk,
    this.isLocalAuthAvailable = false,
    this.isLocalAuthEnabled = false,
    this.showMessageOnLock = true,
    this.localAuthTime = LocalAuthTime.none,
  });

  final AuthenticationStatus authenticationState;
  final User? firebaseUserDetails;
  final bool isLocalAuthAvailable;
  final bool isLocalAuthEnabled;
  final bool showMessageOnLock;
  final LocalAuthTime localAuthTime;
  final int? lastLocalAuthAsk;

  AuthenticationState authenticated(
    User? firebaseUserDetails,
  ) =>
      AuthenticationState(
        authenticationState: AuthenticationStatus.authenticated,
        firebaseUserDetails: firebaseUserDetails,
        isLocalAuthAvailable: isLocalAuthAvailable,
        isLocalAuthEnabled: isLocalAuthEnabled,
        showMessageOnLock: showMessageOnLock,
        localAuthTime: localAuthTime,
        lastLocalAuthAsk: lastLocalAuthAsk,
      );

  AuthenticationState copyWith({
    AuthenticationStatus? authenticationState,
    User? firebaseUserDetails,
    bool? isLocalAuthAvailable,
    bool? isLocalAuthEnabled,
    bool? showMessageOnLock,
    LocalAuthTime? localAuthTime,
    int? lastLocalAuthAsk,
  }) =>
      AuthenticationState(
        authenticationState: authenticationState ?? this.authenticationState,
        firebaseUserDetails: firebaseUserDetails ?? this.firebaseUserDetails,
        isLocalAuthAvailable: isLocalAuthAvailable ?? this.isLocalAuthAvailable,
        isLocalAuthEnabled: isLocalAuthEnabled ?? this.isLocalAuthEnabled,
        showMessageOnLock: showMessageOnLock ?? this.showMessageOnLock,
        localAuthTime: localAuthTime ?? this.localAuthTime,
        lastLocalAuthAsk: lastLocalAuthAsk ?? this.lastLocalAuthAsk,
      );

  @override
  List<Object?> get props => [
        authenticationState,
        firebaseUserDetails,
        isLocalAuthAvailable,
        isLocalAuthEnabled,
        showMessageOnLock,
        localAuthTime,
        lastLocalAuthAsk,
      ];
}
