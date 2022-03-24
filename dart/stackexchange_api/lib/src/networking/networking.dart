import 'package:stackexchange_api/core.dart';

/// Provides a [NetworkingModule] to the [Core].
///
/// This extends the [INetworkingModule] and implements
/// all its methods.
///
/// Contains the main URI.
///
/// And also provides the [RequestHandler] which will start the request and
/// provides the response.
class NetworkingModule extends INetworkingModule {
  static int _requestCounter = 0;

  late Core _core;

  @override
  Core get coreClient => _core;

  @override
  Uri getOrigin() => Uri(
        scheme: 'https',
        host: 'api.stackexchange.com',
        queryParameters: <String, dynamic>{
          if (_core.credentials.key != null) 'key': _core.credentials.key,
        },
      );

  @override
  Future<IRequestHandler> handler() async {
    var requestId = _requestCounter++;
    return RequestHandler(this, requestId);
  }

  @override
  void register(Core core) {
    _core = core;
  }
}
