import 'dart:convert';

import 'package:flublog/constants.dart';
import 'package:flublog/domain/domain.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:hive/hive.dart';
import 'package:path_provider/path_provider.dart' as path_provider;

/// An implementation class for [DBService]
class DBServiceImplementation extends DBService {
  @override
  Future<void> initializeService({bool isTest = false}) async {
    if (isTest) {
      if (!Hive.isBoxOpen(Constants.appName)) {
        Hive.init('MYPATH');
        await Hive.openBox<dynamic>(Constants.appName);
      }
    } else {
      var directory = await path_provider.getApplicationDocumentsDirectory();
      Hive.init(directory.path);
      const secureStorage = FlutterSecureStorage();
      var containsEncryptionKey = await secureStorage.containsKey(key: 'key');
      if (!containsEncryptionKey) {
        var key = Hive.generateSecureKey();
        await secureStorage.write(key: 'key', value: base64UrlEncode(key));
      }
      var key = await secureStorage.read(key: 'key') ?? '';
      var encryptionKey = base64Url.decode(key);
      await Hive.openBox<dynamic>(Constants.appName,
          encryptionCipher: HiveAesCipher(encryptionKey));
    }
  }

  @override
  Box getStorage({String storageName = Constants.appName}) =>
      Hive.box<dynamic>(storageName);
}
