import 'package:whatsapp/core/core.dart';

class FirebaseRemoteConfigServiceImplementation
    extends FirebaseRemoteConfigService {
  factory FirebaseRemoteConfigServiceImplementation() => _instance;

  FirebaseRemoteConfigServiceImplementation._privateConstructor();

  static final FirebaseRemoteConfigServiceImplementation _instance =
      FirebaseRemoteConfigServiceImplementation._privateConstructor();

  @override
  bool isApplicationDown() =>
      remoteConfig.getBool(CoreConstants.isApplicationDown);

  @override
  bool isEncryptionEnabled() =>
      remoteConfig.getBool(CoreConstants.enableEncryption);
}
