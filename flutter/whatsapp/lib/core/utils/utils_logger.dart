import 'package:logger/logger.dart';

abstract class UtilsLogger {
  static void debugLog(dynamic message) {
    Logger().log(Level.debug, message);
  }

  static void errorLog(dynamic exception) {
    Logger().log(Level.error, exception);
  }
}
