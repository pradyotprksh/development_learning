import 'package:encrypt/encrypt.dart';
import 'package:memory_cache/memory_cache.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/domain.dart';

abstract class EncryptorService {
  const EncryptorService();

  static String encryptData(dynamic data) {
    final key = MemoryCache.instance.read<String?>(FirestoreItemKey.pin);
    if (FirebaseRemoteConfigService.isEncryptionEnabled() &&
        key != null &&
        data != null) {
      try {
        final utf8Key = Key.fromUtf8(key);
        final iv = IV.fromLength(16);
        final encryptor = Encrypter(AES(utf8Key));
        final encrypted = encryptor.encrypt(data.toString(), iv: iv);
        return encrypted.base64;
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
        return data.toString();
      }
    } else {
      return data.toString();
    }
  }

  static T decryptData<T>(T data) {
    final key = MemoryCache.instance.read<String?>(FirestoreItemKey.pin);
    if (FirebaseRemoteConfigService.isEncryptionEnabled() &&
        key != null &&
        data != null) {
      try {
        final utf8Key = Key.fromUtf8(key);
        final iv = IV.fromLength(16);
        final decryptor = Encrypter(AES(utf8Key));
        final decrypted = decryptor.decrypt64(data.toString(), iv: iv);
        return decrypted as T;
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
        return data;
      }
    } else {
      return data;
    }
  }
}
