import 'package:firebase_auth/firebase_auth.dart';
import 'package:second_hand_clothes/data/data.dart';
import 'package:second_hand_clothes/domain/utils/enums.dart';

class RepositoriesDataFirebaseAuth extends ServicesDataFirebaseAuth {
  const RepositoriesDataFirebaseAuth();

  @override
  Future<User?> authenticateUser({
    required String email,
    required String password,
    required AuthType authType,
  }) {
    throw UnimplementedError();
  }
}
