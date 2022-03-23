import "package:stackexchange_api/core.dart";

/// Returns the [StackExchangeApiException] if any error occurs.
StackExchangeApiException getExceptionFromAny(dynamic error) {
  if (error is DefaultResult) {
    return getExceptionFromDefaultResult(error);
  }

  return StackExchangeApiException("unknown exception: $error");
}

/// Returns the [StackExchangeApiException] by checking the [DefaultResult].
///
/// Like if any error happens due to invalid input to the request.
StackExchangeApiException getExceptionFromDefaultResult(DefaultResult result) {
  switch (result.status) {
    case 400:
      return InvalidArgumentsException(
        result.message ?? Constants.badParameter,
      );
    case 401:
      return SomethingMissingException(
        result.message ?? Constants.accessTokenRequired,
      );
    case 402:
      return AccessTokenException(
        result.message ?? Constants.invalidAccessToken,
      );
    case 403:
      return SomethingMissingException(
        result.message ?? Constants.accessDenied,
      );
    case 404:
      return SomethingMissingException(
        result.message ?? Constants.noMethod,
      );
    case 405:
      return SomethingMissingException(
        result.message ?? Constants.keyRequired,
      );
    case 406:
      return AccessTokenException(
        result.message ?? Constants.accessTokenCompromised,
      );
    case 407:
      return SomethingMissingException(
        result.message ?? Constants.writeFailed,
      );
    case 409:
      return SomethingMissingException(
        result.message ?? Constants.duplicateRequest,
      );
    case 500:
      return ServerException(
        result.message ?? Constants.internalError,
      );
    case 502:
      return ServerException(
        result.message ?? Constants.throttleViolation,
      );
    case 503:
      return ServerException(
        result.message ?? Constants.temporarilyUnavailable,
      );
  }
  if (result.message?.isNotEmpty ?? false) {
    return StackExchangeApiException(
      "Error: ${result.message}",
    );
  }
  return StackExchangeApiException(
    Constants.unknownError,
  );
}
