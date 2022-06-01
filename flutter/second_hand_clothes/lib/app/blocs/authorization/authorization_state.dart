import 'package:equatable/equatable.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A state class for authorization which will be used to fetch if the user
/// authenticated or not. And based on that the state of the app screen will
/// change.
///
/// This will contain all the auth details of the current user.
class AuthorizationState extends Equatable {
  const AuthorizationState({
    this.authenticationStatus = UtilsAuthenticationStatus.unknown,
    this.userDetails,
    this.noUserDetails = true,
  });

  AuthorizationState copyWith({
    UtilsAuthenticationStatus? authenticationStatus,
    UserDetails? userDetails,
    bool? noUserDetails,
  }) =>
      AuthorizationState(
        authenticationStatus: authenticationStatus ?? this.authenticationStatus,
        userDetails: userDetails ?? this.userDetails,
        noUserDetails: noUserDetails ?? this.noUserDetails,
      );

  final UtilsAuthenticationStatus authenticationStatus;
  final UserDetails? userDetails;
  final bool noUserDetails;

  @override
  List<Object?> get props => [
    authenticationStatus,
        userDetails,
    noUserDetails,
      ];
}
