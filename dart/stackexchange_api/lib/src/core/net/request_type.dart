/// Connect timeout interval for each request.
const _connectTimeoutRequestDefault = <RequestType, int>{
  RequestType.get: 10000,
  RequestType.post: 10000,
  RequestType.delete: 10000,
  RequestType.patch: 10000,
  RequestType.subscribe: 300000,
  RequestType.file: 30000,
};

/// Receive timeout interval for each request.
const _receiveTimeoutRequestDefault = <RequestType, int>{
  RequestType.get: 10000,
  RequestType.post: 10000,
  RequestType.delete: 10000,
  RequestType.patch: 10000,
  RequestType.subscribe: 300000,
  RequestType.file: 30000,
};

/// Send timeout interval for each request.
const _sendTimeoutRequestDefault = <RequestType, int>{
  RequestType.get: 10000,
  RequestType.post: 10000,
  RequestType.delete: 10000,
  RequestType.patch: 10000,
  RequestType.subscribe: 300000,
  RequestType.file: 30000,
};

/// A set of [RequestType] which will be used to make different calls
enum RequestType { get, post, patch, subscribe, delete, file }

/// An extension on [RequestType] to get the details related to the request
/// like the type and other time out parameters.
extension RequestTypeExtension on RequestType {
  static const methods = <RequestType, String>{
    RequestType.get: "GET",
    RequestType.post: "POST",
    RequestType.patch: "PATCH",
    RequestType.subscribe: "GET",
    RequestType.delete: "DELETE",
    RequestType.file: "POST",
  };

  String get method => methods[this]!;

  int get sendTimeout => _sendTimeoutRequestDefault[this]!;

  int get receiveTimeout => _receiveTimeoutRequestDefault[this]!;

  int get connectTimeout => _connectTimeoutRequestDefault[this]!;
}
