import 'package:firebase_remote_config/firebase_remote_config.dart';
import 'package:whatsapp/core/core.dart';

abstract class FirebaseRemoteConfigService {
  static bool isApplicationDown() =>
      FirebaseRemoteConfig.instance.getBool(CoreConstants.isApplicationDown);

  static bool isEncryptionEnabled() =>
      FirebaseRemoteConfig.instance.getBool(CoreConstants.enableEncryption);

  static int imageCompressionValue() =>
      FirebaseRemoteConfig.instance.getInt(CoreConstants.imageCompressionValue);

  static int statusDeleteTimeValue() =>
      FirebaseRemoteConfig.instance.getInt(CoreConstants.statusDeleteTimeValue);
}
