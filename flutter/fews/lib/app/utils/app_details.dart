import 'dart:io';

import 'package:flutter/foundation.dart' as foundation;
import 'package:flutter/services.dart';

abstract class AppDetails {
  static const bool isDebugMode = foundation.kDebugMode;

  static const bool isProfileMode = foundation.kProfileMode;

  static const bool isReleaseMode = foundation.kReleaseMode;

  static bool isAndroid = Platform.isAndroid;

  static bool isIos = Platform.isIOS;

  static bool isWeb = foundation.kIsWeb;

  static bool isPhone = !isWeb && (isAndroid || isIos);

  static void setApplicationOrientation() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);
  }
}
