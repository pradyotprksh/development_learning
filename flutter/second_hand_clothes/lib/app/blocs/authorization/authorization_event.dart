import 'package:equatable/equatable.dart';

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
