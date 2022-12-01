import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

class AuthenticationState extends Equatable {
  const AuthenticationState({
    this.authenticationState = AuthenticationStatus.unauthenticated,
  });

  final AuthenticationStatus authenticationState;

  @override
  List<Object?> get props => [
        authenticationState,
      ];
}
