import 'package:firebase_auth/firebase_auth.dart';
import 'package:whatsapp/core/core.dart';

class FirebaseAuthServiceImplementation extends FirebaseAuthService {
  @override
  User? getUserDetails() => FirebaseAuth.instance.currentUser;

  @override
  String? getUserId() => getUserDetails()?.uid;

  @override
  bool isUserLoggedIn() => getUserDetails() != null;
}
