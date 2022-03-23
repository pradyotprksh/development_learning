/// A set of constants which will be used in the SDK.
abstract class Constants {
  static const invalidArgument = """Invalid Arguments. This may be due to:
  - an invalid subscribe key,
  - missing or invalid token,
  - invalid characters,
  - other invalid request data.""";
  static const unknownError = "An unknown error has occurred";
  static const requestTimeout = "request timed out";
  static const requestFailed = "request failed";
  static const requestCancelled = "request cancelled";
  static const requestFailure = "request returned non-success status code";
  static const forbidden = "Forbidden";
  static const clientIsClosed =
      "Cannot execute request because StackExchangeAPI SDK has already been "
      "closed.";
  static const fatalError = "Fatal error has occurred while running a fiber";
  static const fibreErrorOccurred =
      "An exception has occurred while running a fiber.";
}
