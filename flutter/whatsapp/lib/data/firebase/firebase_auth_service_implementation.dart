import 'package:firebase_auth/firebase_auth.dart';
import 'package:whatsapp/core/core.dart';

class FirebaseAuthServiceImplementation extends FirebaseAuthService {
  factory FirebaseAuthServiceImplementation() => _instance;

  FirebaseAuthServiceImplementation._privateConstructor();

  static final FirebaseAuthServiceImplementation _instance =
      FirebaseAuthServiceImplementation._privateConstructor();

  @override
  User? getUserDetails() => auth.currentUser;

  @override
  String? getUserId() => getUserDetails()?.uid;

  @override
  bool isUserLoggedIn() => getUserDetails() != null;
}
