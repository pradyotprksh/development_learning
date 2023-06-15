/// An exception class which will help in handling the exception if occurred.
class IpAddressException implements Exception {
  IpAddressException(this.message) {
    stackTrace = StackTrace.current;
  }

  String message;
  StackTrace? stackTrace;

  @override
  String toString() => '$message';
}
