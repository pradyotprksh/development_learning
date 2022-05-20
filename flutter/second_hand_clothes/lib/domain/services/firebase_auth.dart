import 'package:second_hand_clothes/domain/domain.dart';

/// A service class for firebase authentication, which will be used
/// to do all the firebase operation related to authentication.
abstract class ServicesFirebaseAuth {
  const ServicesFirebaseAuth();

  /// Authenticate user and get the details from firebase.
  ///
  /// [email] = Email address of the user
  ///
  /// [password] = Password for the account
  ///
  /// [authType] = Type of authentication, register or login
  Future<UserDetails?> authenticateUser({
    required String email,
    required String password,
    required AuthType authType,
  });
}
