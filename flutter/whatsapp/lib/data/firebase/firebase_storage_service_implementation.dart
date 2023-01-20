import 'dart:io';

import 'package:whatsapp/core/core.dart';

class FirebaseStorageServiceImplementation extends FirebaseStorageService {
  factory FirebaseStorageServiceImplementation() => _instance;

  FirebaseStorageServiceImplementation._privateConstructor();

  static final FirebaseStorageServiceImplementation _instance =
      FirebaseStorageServiceImplementation._privateConstructor();

  @override
  Future<String> uploadFile(String path, String referenceName) async {
    final storageReference = storage.ref().child(referenceName);
    await storageReference.putFile(File(path));
    return await storageReference.getDownloadURL();
  }
}
