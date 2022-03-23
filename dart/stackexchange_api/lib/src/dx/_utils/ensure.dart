import "package:stackexchange_api/core.dart";

/// A set of messages which will be shown when any condition
/// was not meant.
final _invariantMessages = {
  "is-not-true": (String that, _) => that,
  "not-null": (String that, _) => "$that cannot be null",
  "not-empty": (String that, _) => "$that cannot be empty",
  "default": (_, __) => "invariant has been broken",
  "is-equal": (String that, List<String> what) =>
      "$that has to equal ${what[0]}",
  "invalid-type": (String that, List<String> what) =>
      '$that must be either ${what.join(' or ')}.',
  "not-together": (String _this, List<String> _that) =>
      '$_this and ${_that.join(',')} can not be provided together. Make separate call!',
};

/// Provides [Ensure] object which will check all the parameters if they are
/// valid or not.
///
/// [value] contains the value which will be checked.
class Ensure {
  Ensure(this.value);

  dynamic value;

  /// Check if [value] is empty or not.
  ///
  /// If empty then it returns [InvariantException].
  void isNotEmpty([String? what]) {
    if (value != null && value is List && value.length != 0) {
      return;
    }

    if (value != null && value is String && value.length != 0) {
      return;
    }

    throw InvariantException("not-empty", what);
  }

  /// Check if [value] is true or not.
  ///
  /// If not then it returns [InvariantException].
  void isTrue([String? what]) {
    if (value is bool) {
      if (value as bool) {
        return;
      }
    }

    throw InvariantException("is-not-true", what);
  }
}

/// Exception thrown when one of the invariants of a method
/// is broken.
class InvariantException extends StackExchangeApiException {
  InvariantException(
    String messageId, [
    String? what,
    List<String> args = const [],
  ]) : super(
          (_invariantMessages[messageId] ?? _invariantMessages["default"])!(
            what!,
            args,
          ),
        );
}
