/// Connect timeout interval for each request.
const _connectTimeoutRequestDefault = <RequestType, int>{
  RequestType.get: 10000,
  RequestType.post: 10000,
};

/// Receive timeout interval for each request.
const _receiveTimeoutRequestDefault = <RequestType, int>{
  RequestType.get: 10000,
  RequestType.post: 10000,
};

/// Send timeout interval for each request.
const _sendTimeoutRequestDefault = <RequestType, int>{
  RequestType.get: 10000,
  RequestType.post: 10000,
};

/// A set of [RequestType] which will be used to make different calls
enum RequestType { get, post }

/// An extension on [RequestType] to get the details related to the request
/// like the type and other time out parameters.
extension RequestTypeExtension on RequestType {
  static const methods = <RequestType, String>{
    RequestType.get: 'GET',
    RequestType.post: 'POST',
  };

  int get connectTimeout => _connectTimeoutRequestDefault[this]!;

  String get method => methods[this]!;

  int get receiveTimeout => _receiveTimeoutRequestDefault[this]!;

  int get sendTimeout => _sendTimeoutRequestDefault[this]!;
}
