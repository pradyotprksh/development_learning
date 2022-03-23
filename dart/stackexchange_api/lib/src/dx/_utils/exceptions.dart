import "package:stackexchange_api/core.dart";

/// Returns the [StackExchangeApiException] if any error occurs.
StackExchangeApiException getExceptionFromAny(dynamic error) {
  if (error is DefaultResult) {
    return getExceptionFromDefaultResult(error);
  }

  if (error is List) {
    if (error.isEmpty) {
      return UnknownException();
    } else if (error.length == 3) {
      return StackExchangeApiException(error[1] as String);
    }
  }

  return StackExchangeApiException("unknown exception: $error");
}

/// Returns the [StackExchangeApiException] by checking the [DefaultResult].
///
/// Like if any error happens due to invalid input to the request.
StackExchangeApiException getExceptionFromDefaultResult(DefaultResult result) {
  if (result.status == 400 && result.message == "Invalid Arguments") {
    return InvalidArgumentsException();
  }

  if (result.status == 403 &&
      result.message!.startsWith("Use of the history Delete API")) {
    return MethodDisabledException(result.message!);
  }

  if (result.status == 403 && result.message == "Forbidden") {
    return ForbiddenException(result.message!);
  }

  if (result.message?.isNotEmpty ?? false) {
    return StackExchangeApiException("Error: ${result.message}");
  }

  return StackExchangeApiException(Constants.unknownError);
}
