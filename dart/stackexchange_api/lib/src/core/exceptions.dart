import 'package:stackexchange_api/core.dart';

/// An exception which will be thrown when there is an access token issue.
class AccessTokenException extends StackExchangeApiException {
  AccessTokenException(String message)
      : super(
          message,
        );
}

/// An exception thrown when some argument is invalid.
class InvalidArgumentsException extends StackExchangeApiException {
  InvalidArgumentsException(String message)
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

/// An exception which is thrown when something is wrong from the server
/// end
class ServerException extends StackExchangeApiException {
  ServerException(String message)
      : super(
          message,
        );
}

/// An exception is thrown when some mismatch happen during the request.
class SomethingMissingException extends StackExchangeApiException {
  SomethingMissingException(String message)
      : super(
          message,
        );
}

/// An exception thrown by the Stack Exchange Api SDK.
class StackExchangeApiException implements Exception {
  final String message;

  final StackTrace stackTrace;

  StackExchangeApiException(
    this.message, [
    StackTrace? stackTrace,
  ]) : stackTrace = stackTrace ?? StackTrace.current;

  /// Get the complete exception details
  String exceptionDetails() => '$runtimeType: $message\n$stackTrace';

  @override
  String toString() => message;
}

/// An exception thrown when something unexpected happens in the SDK.
class UnknownException extends StackExchangeApiException {
  UnknownException()
      : super(
          Constants.unknownError,
        );
}
