import 'package:equatable/equatable.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// An event class for authorization bloc, this will be used to
/// trigger an event.
abstract class AuthorizationEvent extends Equatable {
  const AuthorizationEvent();

  @override
  List<Object?> get props => [];
}

/// A child of [AuthorizationEvent] which will trigger the auth check.
class CheckAuthorizationEvent extends AuthorizationEvent {
  const CheckAuthorizationEvent();
}

/// A child of [AuthorizationEvent] which will trigger the authentication
/// process for the user.
class AuthenticateAuthorizationEvent extends AuthorizationEvent {
  /// [authenticate] = authentication details
  const AuthenticateAuthorizationEvent({
    required this.authenticate,
  });

  final Authenticate authenticate;

  @override
  List<Object?> get props => [authenticate];
}
