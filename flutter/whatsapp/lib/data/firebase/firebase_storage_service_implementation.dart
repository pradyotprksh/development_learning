import 'dart:io';

import 'package:firebase_storage/firebase_storage.dart';
import 'package:whatsapp/core/core.dart';

class FirebaseStorageServiceImplementation extends FirebaseStorageService {
  factory FirebaseStorageServiceImplementation() => _instance;

  FirebaseStorageServiceImplementation._privateConstructor();

  static final FirebaseStorageServiceImplementation _instance =
      FirebaseStorageServiceImplementation._privateConstructor();

  @override
  Future<String> uploadFile(
    String path,
    String referenceName,
    Map<String, String> metaDataDetails,
  ) async {
    final storageReference = storage.ref().child(referenceName);
    await storageReference.putFile(
      File(path),
      SettableMetadata(
        customMetadata: metaDataDetails,
      ),
    );
    return await storageReference.getDownloadURL();
  }
}
