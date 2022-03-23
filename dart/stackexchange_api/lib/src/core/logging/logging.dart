import "dart:async";

import "package:logger/logger.dart";

const _stackExchangeApiLoggerModuleKey = #stackexchangeApi.logging;

/// Get a logger from the provider.
///
/// [id] is an arbitrary parameter, and different Logger implementations will handle it differently.
///
/// If there is no provider, returned logger will be a [Log].
LazyLogger injectLogger(String id) => LazyLogger(
      id,
      () =>
          Zone.current[_stackExchangeApiLoggerModuleKey] as ILogger? ?? Log(id),
    );

/// Provides a [logger] to the code inside [body].
Future<R> provideLogger<R>(ILogger logger, Future<R> Function() body) async {
  var result = await runZoned<Future<R>>(
    body,
    zoneValues: {
      _stackExchangeApiLoggerModuleKey: logger,
    },
  );

  return result;
}

/// The abstract class which will be used to make the calls for printing any
/// messages on the console.
abstract class ILogger {
  void fatal(dynamic message) => log(Level.fatal, message);

  ILogger get(String id);

  void info(dynamic message) => log(Level.info, message);

  void log(int level, dynamic message);

  void severe(dynamic message) => log(Level.severe, message);

  void shout(dynamic message) => log(Level.shout, message);

  void silly(dynamic message) => log(Level.silly, message);

  void verbose(dynamic message) => log(Level.verbose, message);

  void warning(dynamic message) => log(Level.warning, message);
}

/// The main class which will initiate the log calls.
///
/// [_id] : Will be used to distinguish between loggers.
/// [_obtainLogger] : Will be used to get the logger based on [_id].
class LazyLogger implements ILogger {
  LazyLogger(
    this._id,
    this._obtainLogger,
  );

  final String _id;

  final ILogger Function() _obtainLogger;

  ILogger get logger => _obtainLogger().get(_id);

  @override
  void fatal(dynamic message) => logger.fatal(message);

  @override
  ILogger get(String id) => logger.get(id);

  @override
  void info(dynamic message) => logger.info(message);

  @override
  void log(int level, dynamic message) => logger.log(level, message);

  @override
  void severe(dynamic message) => logger.severe(message);

  @override
  void shout(dynamic message) => logger.shout(message);

  @override
  void silly(dynamic message) => logger.silly(message);

  @override
  void verbose(dynamic message) => logger.verbose(message);

  @override
  void warning(dynamic message) => logger.warning(message);
}

/// Represents the level of a log record.
///
/// Those are not strict.
/// How (and if) they work depends on the implementation of the logger.
abstract class Level {
  /// Intended to disable logging at all.
  static const int off = 0;

  /// Intended for an extra debug information. Only use when debugging.
  static const int shout = 10;

  /// Intended for fatal errors that end in application crash or exit.
  static const int fatal = 20;

  /// Intended for severe exceptions that need to be resolved.
  static const int severe = 40;

  /// Intended for warnings.
  static const int warning = 80;

  /// Intended for informational messages.
  static const int info = 160;

  /// Intended for the verbose mode.
  static const int verbose = 320;

  /// Intended for the super verbose mode.
  static const int silly = 640;

  /// Intended to enable all logging.
  static const int all = 10000;

  static const Map<int, String> levels = {
    0: "off",
    10: "shout",
    20: "fatal",
    40: "severe",
    80: "warning",
    160: "info",
    320: "verbose",
    640: "silly",
    10000: "all"
  };

  static String getName(int level) => levels.entries
      .map((entry) => MapEntry((entry.key - level).abs(), entry.value))
      .reduce((current, next) => current.key > next.key ? next : current)
      .value;
}

/// Inner logger class which will be used for printing the logs.
///
/// [_id] : Identifier for the logger to check from where the logs
/// are printing;
class Log extends ILogger {
  Log(this._id);

  final String _id;

  @override
  Log get(String id) => this;

  @override
  void log(int level, dynamic message) {
    switch (level) {
      case Level.off:
      case Level.severe:
      case Level.fatal:
        Logger().e("$_id $message");
        break;
      case Level.info:
        Logger().i("$_id $message");
        break;
      case Level.silly:
        Logger().wtf("$_id $message");
        break;
      case Level.verbose:
        Logger().v("$_id $message");
        break;
      case Level.warning:
        Logger().w("$_id $message");
        break;
      case Level.shout:
      case Level.all:
        Logger().d("$_id $message");
        break;
    }
  }
}
