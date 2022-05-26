import 'package:equatable/equatable.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A authenticate modal class which will hold the details of the authorization
/// entered by the user.
class Authenticate extends Equatable {
  /// [emailId] = Email address of the user
  ///
  /// [password] = Password of the account
  ///
  /// [authType] = Type of authentication.
  const Authenticate({
    required this.emailId,
    required this.password,
    required this.authType,
  });

  final String emailId;
  final String password;
  final AuthType authType;

  @override
  List<Object?> get props => [emailId, password, authType];
}