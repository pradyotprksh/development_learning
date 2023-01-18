import 'package:firebase_remote_config/firebase_remote_config.dart';

abstract class FirebaseRemoteConfigService {
  final remoteConfig = FirebaseRemoteConfig.instance;

  bool isApplicationDown();

  bool isEncryptionEnabled();
}
