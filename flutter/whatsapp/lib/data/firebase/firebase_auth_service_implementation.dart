import 'package:firebase_auth/firebase_auth.dart';
import 'package:whatsapp/core/core.dart';

class FirebaseAuthServiceImplementation extends FirebaseAuthService {
  final _auth = FirebaseAuth.instance;

  @override
  User? getUserDetails() => _auth.currentUser;

  @override
  String? getUserId() => getUserDetails()?.uid;

  @override
  bool isUserLoggedIn() => getUserDetails() != null;
}
