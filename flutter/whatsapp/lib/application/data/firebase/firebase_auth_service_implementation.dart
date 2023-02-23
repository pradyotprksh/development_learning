import 'package:firebase_auth/firebase_auth.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/models/user.dart';

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

  @override
  void updateUserDetails(UserDetails? userDetails) {
    final currentUser = getUserDetails();
    if (currentUser != null) {
      final emailId = userDetails?.emailId;
      final profileImage = userDetails?.profileImage;
      final name = userDetails?.name;
      if (emailId != null) {
        currentUser.updateEmail(emailId);
      }
      if (profileImage != null) {
        currentUser.updatePhotoURL(profileImage);
      }
      if (name != null) {
        currentUser.updateDisplayName(name);
      }
    }
  }

  @override
  Stream<User?> isUserAuthenticated() => auth.authStateChanges();
}
