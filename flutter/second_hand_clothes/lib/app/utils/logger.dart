import 'package:logger/logger.dart';

abstract class UtilLogger {
  static void log(
    dynamic message, [
    dynamic error,
    StackTrace? stackTrace,
  ]) {
    if (error != null) {
      Logger().log(Level.debug, message, error, stackTrace);
    } else {
      Logger().log(Level.error, message, error, stackTrace);
    }
  }
}
