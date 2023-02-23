import 'package:firebase_auth/firebase_auth.dart';
import 'package:whatsapp/application/domain/domain.dart';

abstract class FirebaseAuthService {
  final auth = FirebaseAuth.instance;

  User? getUserDetails();

  String? getUserId();

  bool isUserLoggedIn();

  void updateUserDetails(UserDetails userDetails);

  Stream<User?> isUserAuthenticated();
}
