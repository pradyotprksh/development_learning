import "package:stackexchange_api/core.dart";

/// An exception thrown by the Stack Exchange Api SDK.
class StackExchangeApiException implements Exception {
  StackExchangeApiException(
    this.message, [
    StackTrace? stackTrace,
  ]) : stackTrace = stackTrace ?? StackTrace.current;

  final String message;

  final StackTrace stackTrace;

  /// Get the complete exception details
  String exceptionDetails() => "$runtimeType: $message\n$stackTrace";

  @override
  String toString() => message;
}

/// An exception thrown when a feature is not available for particular user.
class ForbiddenException extends StackExchangeApiException {
  ForbiddenException(this.service)
      : super(
          Constants.forbidden,
        );

  final String service;
}

/// An exception thrown when some argument is invalid.
///
/// This may be due to:
/// - an invalid subscribe key.
/// - missing or invalid token
/// - other invalid request data.
class InvalidArgumentsException extends StackExchangeApiException {
  InvalidArgumentsException([String message = Constants.invalidArgument])
      : super(
          message,
        );
}

/// An exception thrown when a disabled API has been requested.
class MethodDisabledException extends StackExchangeApiException {
  MethodDisabledException(String message)
      : super(
          message,
        );
}

/// An exception thrown when something unexpected happens in the SDK.
class UnknownException extends StackExchangeApiException {
  UnknownException()
      : super(
          Constants.unknownError,
        );
}
