import 'package:firebase_auth/firebase_auth.dart';

abstract class FirebaseAuthService {
  User? getUserDetails();

  String? getUserId();

  bool isUserLoggedIn();
}
