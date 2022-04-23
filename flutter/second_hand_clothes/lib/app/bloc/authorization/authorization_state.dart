import 'package:equatable/equatable.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A state class for authorization which will be used to fetch if the user
/// authenticated or not. And based on that the state of the app screen will
/// change.
///
/// This will contain all the auth details of the current user.
class AuthorizationState extends Equatable {
  const AuthorizationState({
    this.authenticationStatus = UtilsAuthenticationStatus.unknown,
  });

  const AuthorizationState.unknown() : this();

  const AuthorizationState.unauthenticated()
      : this(
    authenticationStatus: UtilsAuthenticationStatus.unauthenticated,
  );

  const AuthorizationState.authenticated()
      : this(
    authenticationStatus: UtilsAuthenticationStatus.authenticated,
  );

  final UtilsAuthenticationStatus authenticationStatus;

  @override
  List<Object?> get props => [];
}
