import 'package:logger/logger.dart';

abstract class UtilsLogger {
  static void debugLog(String message) {
    Logger().log(Level.debug, message);
  }
}
