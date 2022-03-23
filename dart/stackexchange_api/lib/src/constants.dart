/// A set of constants which will be used in the SDK.
abstract class Constants {
  static const badParameter = "An invalid parameter was passed, this includes "
      "even \"high level\" parameters like key or site.";
  static const accessTokenRequired = "A method that requires an access token"
      " was called without one.";
  static const invalidAccessToken = "An invalid access token was passed"
      " to a method.";
  static const accessDenied = "A method which requires certain permissions "
      "was called with an access token that lacks those permissions.";
  static const noMethod = "An attempt was made to call a method that does not "
      "exist. Note, calling methods that expect numeric ids (like "
      "/users/{ids}) with non-numeric ids can also result in this error.";
  static const keyRequired = "A method was called in a manner that requires "
      "an application key (generally, with an access token), but no"
      " key was passed.";
  static const accessTokenCompromised = "An access token is no longer "
      "believed to be secure, normally because it was used on a non-HTTPS "
      "call. The access token will be invalidated if this error is"
      " returned.";
  static const writeFailed = "A write operation was rejected.";
  static const duplicateRequest = "A request identified by a "
      "request_id has already been run.";
  static const internalError = "An unexpected error occurred in the API "
      "and has been logged.";
  static const throttleViolation = "An application has violated part of the "
      "rate limiting contract, so the request was terminated.";
  static const temporarilyUnavailable = "Some or all of the API is "
      "unavailable. Applications should backoff on requests to "
      "the method invoked.";
  static const unknownError = "An unknown error has occurred";
  static const requestTimeout = "request timed out";
  static const requestFailed = "request failed";
  static const requestCancelled = "request cancelled";
  static const requestFailure = "request returned non-success status code";
  static const clientIsClosed =
      "Cannot execute request because StackExchangeAPI SDK has already been "
      "closed.";
  static const fatalError = "Fatal error has occurred while running a fiber";
  static const fibreErrorOccurred =
      "An exception has occurred while running a fiber.";
}
