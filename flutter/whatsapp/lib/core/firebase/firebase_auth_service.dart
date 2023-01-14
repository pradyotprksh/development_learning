import 'package:firebase_auth/firebase_auth.dart';

abstract class FirebaseAuthService {
  final auth = FirebaseAuth.instance;

  User? getUserDetails();

  String? getUserId();

  bool isUserLoggedIn();
}
