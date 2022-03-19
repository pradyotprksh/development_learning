import 'package:flutter/foundation.dart' as foundation;
import 'package:flutter/services.dart';

/// An common application details class, which will hold all the details
/// related to the application like debug mode, version, etc.
abstract class ApplicationDetails {
  /// Is the application currently running on debug mode?
  ///
  /// Return true/false
  static bool isDebugMode = foundation.kDebugMode;

  /// Is the application currently running on profile mode?
  ///
  /// Return true/false
  static bool isProfileMode = foundation.kProfileMode;

  /// Set the current orientation of the application, either in landscape
  /// or in portrait.
  static void setApplicationOrientation() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);
  }
}