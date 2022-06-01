import 'package:firebase_auth/firebase_auth.dart';
import 'package:second_hand_clothes/data/data.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A repository class for firebase auth, this will implement the firebase
/// auth service of the data layer.
class RepositoriesDataFirebaseAuth extends ServicesDataFirebaseAuth {
  factory RepositoriesDataFirebaseAuth() => _instance;

  RepositoriesDataFirebaseAuth._privateConstructor();

  static final RepositoriesDataFirebaseAuth _instance =
      RepositoriesDataFirebaseAuth._privateConstructor();

  // TODO: Mapping of the data layer results should be in domain layer. Data layer should only return the value it got from remote.
  @override
  Future<UserDetails?> authenticateUser({
    required String email,
    required String password,
    required AuthType authType,
  }) async {
    final User? user;
    switch (authType) {
      case AuthType.login:
        final userCredentials =
            await FirebaseAuth.instance.signInWithEmailAndPassword(
          email: email,
          password: password,
        );
        user = userCredentials.user;
        break;
      case AuthType.register:
        final userCredentials =
            await FirebaseAuth.instance.createUserWithEmailAndPassword(
          email: email,
          password: password,
        );
        user = userCredentials.user;
        break;
    }
    return UserDetails(
      emailId: user?.email,
      displayName: user?.displayName,
      uid: user?.uid,
      profilePic: user?.photoURL,
    );
  }

  @override
  bool isUserLoggedIn() => FirebaseAuth.instance.currentUser != null;

  @override
  void dispose() {}
}
