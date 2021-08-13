import 'package:logger/logger.dart';

/// A utility class which will be used to make calls for common methods.
abstract class Utility {
  /// Print an information log
  static void printILog(String message) {
    Logger().i(message);
  }

  /// Print a debug log
  static void printDLog(String message) {
    Logger().d(message);
  }

  /// Print a warning log
  static void printWLog(String message) {
    Logger().w(message);
  }

  /// Print a WTF log
  static void printWTFLog(String message) {
    Logger().wtf(message);
  }
}
