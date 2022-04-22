import 'package:logger/logger.dart';

/// A singleton logger class for the app level module of the project.
///
/// Any log which is needed on the console in the app level will be made
/// from here. So that we have a single place to handle all the messages/
/// errors/stacktrace to be shown.
///
/// The debug and production mode should be handle carefully, because it's not
/// okay to console log a sensitive information like user details, api key,
/// etc.
class UtilsLogger {
  factory UtilsLogger() => _instance;

  UtilsLogger._privateConstructor();

  static final UtilsLogger _instance = UtilsLogger._privateConstructor();

  /// Log a [message], [error] and [stackTrace] if available.
  void log(
    dynamic message, [
    dynamic error,
    StackTrace? stackTrace,
  ]) {
    Logger().log(
      (error == null) ? Level.debug : Level.error,
      message,
      error,
      stackTrace,
    );
  }
}
