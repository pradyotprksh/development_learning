import 'dart:convert';

import 'package:whatsapp/core/core.dart';

extension MapExtension on Map<String, dynamic> {
  int getDocumentSize() {
    try {
      final jsonString = json.encode(this);
      final sizeInBytes = utf8.encode(jsonString).length;
      return sizeInBytes;
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return 0;
    }
  }
}
