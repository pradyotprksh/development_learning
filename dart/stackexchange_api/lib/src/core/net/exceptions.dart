import "package:stackexchange_api/core.dart";

/// When the request was canceled due to some error.
class RequestCancelException extends StackExchangeApiException {
  final dynamic additionalData;

  RequestCancelException([this.additionalData])
      : super(
          Constants.requestCancelled,
        );
}

/// When unable to complete the request
class RequestFailureException extends StackExchangeApiException {
  final IResponse response;

  RequestFailureException(this.response)
      : super(
          Constants.requestFailure,
        );
}

/// When some other exception has occurred which is unknown.
class RequestOtherException extends StackExchangeApiException {
  final dynamic additionalData;

  RequestOtherException([this.additionalData])
      : super(
          "${Constants.requestFailed} ($additionalData)",
        );
}

/// When the request timeout exception has occurred
class RequestTimeoutException extends StackExchangeApiException {
  final dynamic additionalData;

  RequestTimeoutException([this.additionalData])
      : super(
          Constants.requestTimeout,
        );
}
