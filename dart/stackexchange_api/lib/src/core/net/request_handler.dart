import 'package:stackexchange_api/core.dart';

/// A class which will be used to start the request.
///
/// Also provides the response back.
///
/// [prepareUri] will be used to make the final uri
/// with the base and the request.
abstract class IRequestHandler {
  /// If the request has been canceled.
  bool get isCancelled;

  /// If the request is cancel then this will used to set the reason.
  void cancel([dynamic reason]);

  /// Prepare the final URI for making the request.
  ///
  /// [baseUri] : The URI which will be the base to which the other
  /// URIs will connect to.
  ///
  /// [requestUri] : Contains all the extra parameters like the endpoints,
  /// queries and so one which will be added to [baseUri] to make the
  /// final request.
  Uri prepareUri(Uri baseUri, Uri requestUri) => baseUri.replace(
        scheme: requestUri.hasScheme ? requestUri.scheme : baseUri.scheme,
        host: requestUri.host != '' ? requestUri.host : null,
        path: requestUri.path != '' ? requestUri.path : null,
        queryParameters: (baseUri.queryParameters.isNotEmpty ||
                requestUri.queryParameters.isNotEmpty)
            ? <String, dynamic>{
                if (baseUri.queryParameters.isNotEmpty)
                  ...baseUri.queryParameters,
                if (requestUri.queryParameters.isNotEmpty)
                  ...requestUri.queryParameters
              }
            : null,
      );

  /// Starts the request and provides a [IResponse].
  Future<IResponse> response(Request request);
}
