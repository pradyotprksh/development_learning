import 'package:equatable/equatable.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A state class for splash which will be used to fetch if the user
/// authenticated or not. And based on that the state of the splash screen will
/// change.
class SplashState extends Equatable {
  const SplashState({
    this.authenticationStatus = UtilsAuthenticationStatus.unknown,
  });

  const SplashState.unknown() : this();

  const SplashState.unauthenticated()
      : this(
          authenticationStatus: UtilsAuthenticationStatus.unauthenticated,
        );

  const SplashState.authenticated()
      : this(
          authenticationStatus: UtilsAuthenticationStatus.authenticated,
        );

  final UtilsAuthenticationStatus authenticationStatus;

  @override
  List<Object?> get props => [];
}
