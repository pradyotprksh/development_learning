import 'dart:async';
import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:stackexchange_api/core.dart';

final _logger = injectLogger('film_gyaan.networking.request_handler');

/// Provides the [RequestHandler].
///
/// [_module] and [_id] is taken as a parameter.
///
/// [_module] provides the initial details of the request.
///
/// And [_id] is basically the count of the request which is made till the
/// current time.
class RequestHandler extends IRequestHandler {
  RequestHandler(
    this._module,
    this._id,
  );

  final INetworkingModule _module;

  final int _id;

  final http.Client _httpClient = http.Client();

  final _cancel = Completer<Exception>();
  Timer? _sendTimeoutTimer;

  bool _isReleased = false;
  void Function(dynamic)? _abortRequest;

  Future<Exception> get cancelReason => _cancel.future;

  @override
  bool get isCancelled => _cancel.isCompleted;

  bool get isDone => _cancel.isCompleted;

  @override
  void cancel([dynamic reason]) {
    if (!isDone) {
      _logger.warning(
        '($_id) Request has been cancelled (reason: ${reason.runtimeType}).',
      );

      _cancel.complete(RequestCancelException(reason));

      if (_abortRequest != null) {
        _abortRequest!(
          RequestCancelException(
            reason,
          ),
        );
      }
    }
  }

  @override
  Future<IResponse> response(Request request) async {
    if (_module.coreClient.closed) {
      throw MethodDisabledException(
        Constants.clientIsClosed,
      );
    }

    _logger.info(
      '($_id) Preparing request.',
    );

    var headers = <String, String>{};

    if (request.headers != null) {
      headers.addAll(request.headers!);
    }

    var uri = prepareUri(_module.getOrigin(), request.uri ?? Uri());

    if (request.body != null) {
      headers.addAll(
        {
          'Content-Type': 'application/json; charset=utf-8',
        },
      );
    }

    _logger.info(
      '($_id) Starting request to "$uri"...',
    );

    try {
      if (isCancelled) {
        throw await cancelReason;
      }

      final httpRequest = _prepareRequest(
        request.body,
        http.Request(
          request.type.method,
          uri,
        ),
        headers,
      );

      http.StreamedResponse httpResponse;

      _sendTimeoutTimer = Timer(
        Duration(
          milliseconds: request.type.sendTimeout,
        ),
        () {
          if (!isDone) {
            _cancel.complete(
              RequestTimeoutException(),
            );
            if (_abortRequest != null) {
              _abortRequest!(
                RequestTimeoutException(),
              );
            }
          }
        },
      );

      httpResponse = await _httpClient.send(httpRequest);
      var response = Response(
        httpResponse,
        await httpResponse.stream.bytesToString(),
      );

      if (httpResponse.statusCode < 200 || httpResponse.statusCode > 299) {
        throw RequestFailureException(response);
      }

      _logger.shout(
        '($_id) Request succeed!',
      );

      return response;
    } on RequestCancelException {
      rethrow;
    } on RequestFailureException {
      rethrow;
    } on RequestTimeoutException {
      rethrow;
    } catch (e) {
      _logger.fatal(
        '($_id) Request failed (${e.runtimeType}) ($e)',
      );
      throw RequestOtherException(e);
    } finally {
      if (!_isReleased) {
        _isReleased = true;
        _httpClient.close();
        _sendTimeoutTimer?.cancel();
        _logger.info(
          '($_id) Resource released...',
        );
      }
    }
  }

  /// Create a copy of the request
  http.Request _copyRequest(http.Request httpRequest) {
    final request = http.Request(httpRequest.method, httpRequest.url);
    request.headers.addAll(httpRequest.headers);
    request
      ..maxRedirects = httpRequest.maxRedirects
      ..followRedirects = httpRequest.followRedirects
      ..persistentConnection = httpRequest.persistentConnection
      ..encoding = httpRequest.encoding
      ..bodyBytes = httpRequest.bodyBytes;
    return request;
  }

  /// Prepare the request by getting the headers and the request.
  ///
  /// Returns [http.Request].
  http.Request _prepareRequest(
    dynamic jsonBody,
    http.Request httpRequest,
    Map<String, String> headers,
  ) {
    final request = _copyRequest(httpRequest);

    if (jsonBody != null) {
      request.body = jsonEncode(jsonBody);
    }

    request.headers.addAll(
      headers,
    );

    return request;
  }
}
