import 'package:firebase_auth/firebase_auth.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/data/data.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A firebase repository which is the child of [ServicesFirebaseAuth] and
/// implements all its methods.
///
/// This will keep the implementation away from other layers.
class RepositoriesFirebaseAuth extends ServicesFirebaseAuth {
  factory RepositoriesFirebaseAuth() => _instance;

  RepositoriesFirebaseAuth._privateConstructor()
      : _dataFirebaseAuth = RepositoriesDataFirebaseAuth();

  static final RepositoriesFirebaseAuth _instance =
      RepositoriesFirebaseAuth._privateConstructor();

  final ServicesDataFirebaseAuth _dataFirebaseAuth;

  @override
  Future<UserDetails?> authenticateUser({
    required String email,
    required String password,
    required AuthType authType,
  }) async {
    try {
      return await _dataFirebaseAuth.authenticateUser(
        email: email,
        password: password,
        authType: authType,
      );
    } catch (exception) {
      UtilsLogger().log('authenticateUser', error: exception);
      if (exception is FirebaseAuthException) {
        throw AuthenticationException(message: exception.message);
      } else {
        throw const AuthenticationException();
      }
    }
  }

  @override
  bool isUserLoggedIn() => _dataFirebaseAuth.isUserLoggedIn();

  @override
  void dispose() {
    _dataFirebaseAuth.dispose();
  }
}
