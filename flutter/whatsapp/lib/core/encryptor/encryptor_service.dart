import 'dart:convert' show utf8, base64;

import 'package:whatsapp/core/core.dart';

abstract class EncryptorService {
  const EncryptorService();

  static String encryptData(dynamic data) {
    if (FirebaseRemoteConfigService.isEncryptionEnabled() && data != null) {
      try {
        final encoded = base64.encode(utf8.encode(data.toString()));
        return encoded;
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
        return data.toString();
      }
    } else {
      return data.toString();
    }
  }

  static T decryptData<T>(T data) {
    if (FirebaseRemoteConfigService.isEncryptionEnabled() && data != null) {
      try {
        final decoded = utf8.decode(base64.decode(data.toString()));
        return decoded as T;
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
        return data;
      }
    } else {
      return data;
    }
  }
}
