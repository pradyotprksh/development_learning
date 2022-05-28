import 'package:flutter/foundation.dart' as foundation;
import 'package:flutter/services.dart';

/// An application details class which contains common details related to the
/// application like current mode, version, orientation, etc.
class UtilsAppDetails {
  factory UtilsAppDetails() => _instance;

  UtilsAppDetails._privateConstructor();

  static final UtilsAppDetails _instance =
      UtilsAppDetails._privateConstructor();

  /// Is the application currently running on debug mode?
  ///
  /// Return true/false
  final bool isDebugMode = foundation.kDebugMode;

  /// If want to use mock responses then value of this should be true.
  ///
  /// Keep in mind not all responses can be mocked, some will required
  /// remote connectivity.
  final bool useMockResponses = true;

  /// Is the application currently running on profile mode?
  ///
  /// Return true/false
  final bool isProfileMode = foundation.kProfileMode;

  /// Is the application currently running on release mode?
  ///
  /// Return true/false
  final bool isReleaseMode = foundation.kReleaseMode;

  /// Set the current orientation of the application, either in landscape
  /// or in portrait.
  void setApplicationOrientation() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);
  }
}
