import 'dart:async';

import 'package:firebase_storage/firebase_storage.dart';

abstract class FirebaseStorageService {
  final storage = FirebaseStorage.instance;

  Future<String> uploadFile(
    String path,
    String referenceName,
    Map<String, String> metaDataDetails,
  );

  Future<void> deleteFiles(List<String> storageReference);
}
