import 'package:flutter/foundation.dart' as foundation;
import 'package:flutter/services.dart';

abstract class AppDetails {
  static const bool isDebugMode = foundation.kDebugMode;

  static const bool useMockResponses = true;

  static const bool isProfileMode = foundation.kProfileMode;

  static const bool isReleaseMode = foundation.kReleaseMode;

  static void setApplicationOrientation() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);
  }
}