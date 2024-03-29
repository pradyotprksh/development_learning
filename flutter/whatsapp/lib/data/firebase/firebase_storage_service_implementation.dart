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
    final metaData = await storageReference.getMetadata();
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.file,
        ListenersType.write,
        (metaData.size ?? 0).toDouble(),
      ),
    );
    return await storageReference.getDownloadURL();
  }

  @override
  Future<void> deleteFiles(List<String> storageReference) async {
    for (final reference in storageReference) {
      await storage.ref(reference).delete();
    }
  }
}
