import 'package:firebase_auth/firebase_auth.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/data/data.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A firebase repository which is the child of [ServicesFirebaseAuth] and
/// implements all its methods.
///
/// This will keep the implementation away from other layers.
class RepositoriesFirebaseAuth extends ServicesFirebaseAuth {
  RepositoriesFirebaseAuth()
      : _dataFirebaseAuth = const RepositoriesDataFirebaseAuth();

  final ServicesDataFirebaseAuth _dataFirebaseAuth;

  @override
  Future<User?> authenticateUser({
    required String email,
    required String password,
    required AuthType authType,
  }) async {
    try {
      return _dataFirebaseAuth.authenticateUser(
        email: email,
        password: password,
        authType: authType,
      );
    } catch (exception) {
      UtilsLogger().log('authenticateUser', exception);
      if (exception is FirebaseException) {
        throw AuthenticationException(message: exception.message);
      } else {
        throw const AuthenticationException();
      }
    }
  }
}
