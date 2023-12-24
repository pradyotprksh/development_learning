import 'package:logger/logger.dart';

enum LogType {
  trace,
  debug,
  information,
  warning,
  error,
  fatal,
}

abstract class AppLogger {
  static final _logger = Logger();

  static void showLog({
    required String message,
    Object? error,
    StackTrace? stackTrace,
    LogType logType = LogType.debug,
    bool showLogs = true,
  }) {
    if (!showLogs) return;

    switch (logType) {
      case LogType.trace:
        _logger.t(
          message,
          time: DateTime.now(),
          error: error,
          stackTrace: stackTrace,
        );
        break;
      case LogType.debug:
        _logger.d(
          message,
          time: DateTime.now(),
          error: error,
          stackTrace: stackTrace,
        );
        break;
      case LogType.information:
        _logger.i(
          message,
          time: DateTime.now(),
          error: error,
          stackTrace: stackTrace,
        );
        break;
      case LogType.warning:
        _logger.w(
          message,
          time: DateTime.now(),
          error: error,
          stackTrace: stackTrace,
        );
        break;
      case LogType.error:
        _logger.e(
          message,
          time: DateTime.now(),
          error: error,
          stackTrace: stackTrace,
        );
        break;
      case LogType.fatal:
        _logger.f(
          message,
          time: DateTime.now(),
          error: error,
          stackTrace: stackTrace,
        );
        break;
    }
  }
}
