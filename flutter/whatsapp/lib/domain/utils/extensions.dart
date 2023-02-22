import 'dart:convert';
import 'dart:typed_data';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';

extension MapExtension on Map<String, dynamic> {
  int getDocumentSize() {
    try {
      var withoutDocumentReference = <String, dynamic>{};
      var withDocumentReference = <String, dynamic>{};

      forEach(
        (key, dynamic value) {
          if (value is DocumentReference) {
            withDocumentReference[key] = value;
          } else {
            withoutDocumentReference[key] = value;
          }
        },
      );

      final jsonString = json.encode(withoutDocumentReference);
      final sizeInBytes = utf8.encode(jsonString).length;
      var documentReferenceSize = 0;
      if (withDocumentReference.isNotEmpty) {
        withDocumentReference.forEach(
          (key, dynamic value) {
            if (value is DocumentReference) {
              documentReferenceSize += key.length + 1;
              documentReferenceSize += value.getDocumentReferenceSize();
            }
          },
        );
      }
      return sizeInBytes + documentReferenceSize;
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return 0;
    }
  }
}

extension DocumentReferenceExtension on DocumentReference {
  int getDocumentReferenceSize() {
    final collectionPath = parent.path;
    final documentId = id;

    final collectionPathBytes = Uint8List.fromList(collectionPath.codeUnits);
    final documentIdBytes = Uint8List.fromList(documentId.codeUnits);

    return collectionPathBytes.lengthInBytes + documentIdBytes.lengthInBytes;
  }
}
