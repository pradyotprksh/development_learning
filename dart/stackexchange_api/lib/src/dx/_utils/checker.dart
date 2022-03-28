import 'package:stackexchange_api/core.dart';

/// A checker which will check for the [value] and see if the output which is
/// required is there or not.
///
/// Checker only gives a message rather than an exception like [Ensure].
class Checker {
  Checker(
    this.value,
    this._logger,
  );

  dynamic value;

  final LazyLogger _logger;

  /// If the [value] is true then show a warning.
  void isTrueWarning(String message) {
    if (value is bool) {
      if (value == true) {
        _logger.warning(message);
      }
    }
  }
}
