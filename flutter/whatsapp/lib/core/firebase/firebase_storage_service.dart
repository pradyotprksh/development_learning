import 'package:firebase_storage/firebase_storage.dart';

abstract class FirebaseStorageService {
  final storage = FirebaseStorage.instance;

  Future<String> uploadImage(String path, String referenceName);
}
