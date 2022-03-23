import "package:stackexchange_api/core.dart";

/// When the request was canceled due to some error.
class RequestCancelException extends StackExchangeApiException {
  RequestCancelException([this.additionalData])
      : super(
          Constants.requestCancelled,
        );

  final dynamic additionalData;
}

/// When unable to complete the request
class RequestFailureException extends StackExchangeApiException {
  RequestFailureException(this.response)
      : super(
          Constants.requestFailure,
        );

  final IResponse response;
}

/// When some other exception has occurred which is unknown.
class RequestOtherException extends StackExchangeApiException {
  RequestOtherException([this.additionalData])
      : super(
          "${Constants.requestFailed} ($additionalData)",
        );

  final dynamic additionalData;
}

/// When the request timeout exception has occurred
class RequestTimeoutException extends StackExchangeApiException {
  RequestTimeoutException([this.additionalData])
      : super(
          Constants.requestTimeout,
        );

  final dynamic additionalData;
}
