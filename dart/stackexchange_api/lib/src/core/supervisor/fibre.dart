import 'dart:async';

import 'package:stackexchange_api/core.dart';

final _logger = injectLogger('film_gyaan.core.supervisor.fiber');

typedef _FiberAction<T> = Future<T> Function();

/// Provides a Fibre object.
///
/// This will be used to start the request process and provides the response
/// from the [_completer].
///
/// Will also be used to see what if any error occurred.
///
/// Need to provide [Core] and the [action] which will be called through
/// [Fiber].
class Fiber<T> {
  int tries = 0;

  final _FiberAction<T> action;

  final _completer = Completer<T>();

  Fiber({
    required this.action,
  });

  Future<T> get future => _completer.future;

  /// This will initiate the request by calling [action]. And when the
  /// request is done the result will be saved int [_completer].
  ///
  /// [_completer] can also have the exceptions if occured.
  Future<void> run() async {
    tries += 1;

    try {
      var result = await action();

      _completer.complete(result);
    } catch (exception, stackTrace) {
      if (exception is Error) {
        _logger.fatal(
          '${Constants.fatalError} ($exception).',
        );
        return _completer.completeError(exception, stackTrace);
      }

      _logger.warning(
        Constants.fibreErrorOccurred,
      );

      return _completer.completeError(exception);
    }
  }
}
