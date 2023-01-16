import 'package:encrypt/encrypt.dart';
import 'package:whatsapp/app/app.dart';

abstract class EncryptorService {
  const EncryptorService();

  static String encryptData(String data, String key) {
    if (AppDetails.enableEncryption) {
      final utf8Key = Key.fromUtf8(key);
      final iv = IV.fromLength(16);
      final encryptor = Encrypter(AES(utf8Key));
      final encrypted = encryptor.encrypt(data.toString(), iv: iv);
      return encrypted.base64;
    } else {
      return data;
    }
  }

  static String decryptData(String data, String key) {
    if (AppDetails.enableEncryption) {
      final utf8Key = Key.fromUtf8(key);
      final iv = IV.fromLength(16);
      final decryptor = Encrypter(AES(utf8Key));
      final decrypted = decryptor.decrypt64(data.toString(), iv: iv);
      return decrypted;
    } else {
      return data;
    }
  }
}
