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

/// A child of [AuthorizationEvent] which will trigger the profile details
/// of the current user is found
class FoundProfileDetailsAuthorizationEvent extends AuthorizationEvent {
  const FoundProfileDetailsAuthorizationEvent(this.userDetails);

  final UserDetails? userDetails;

  @override
  List<Object?> get props => [userDetails];
}
