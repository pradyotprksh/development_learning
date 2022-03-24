import "package:stackexchange_api/core.dart";

/// A network module which will be used to handle the request.
///
/// And also be used to access the [Core] while making the request.
///
/// Also provides the main URI which will contains the base url.
abstract class INetworkingModule {
  /// Get the [Core] object.
  Core get coreClient;

  /// Provides the origin which will have the base URI
  Uri getOrigin();

  /// Provides a [IRequestHandler] which will be used to initiate the
  /// request.
  Future<IRequestHandler> handler();

  /// Register [core] before doing any request
  void register(Core core);
}
