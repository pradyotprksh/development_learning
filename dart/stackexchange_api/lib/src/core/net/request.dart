import "package:stackexchange_api/core.dart";

/// Provides a [Request] which will contain the details for the request
/// which needs to be done.
///
/// [uri] : the other path like the endpoint
/// [headers] : headers which needs to be added to the default header of the
/// request
///
/// [body] : If any body is required to be added to the request.
///
/// [type] : Contains what is the type of the request from the [RequestType]
/// list.
class Request {
  RequestType type;

  Uri? uri;

  Map<String, String>? headers;
  Object? body;

  /// A GET [Request] which will make the request.
  Request.get({
    this.uri,
    this.headers,
    this.body,
  }) : type = RequestType.get;

  @override
  String toString() => "Request { [$type] $uri }";
}
