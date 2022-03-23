import "package:stackexchange_api/core.dart";

/// The body deserializer which will be used to parse
/// the response body to the specified [T] class.
typedef BodyDeserializer<T> = T Function(dynamic body);

/// Provides a [Core] object which will be used to handle all the
/// core related work. Like initializing MQTT, making request, etc.
class Core {
  Core({
    required this.credentials,
    required this.networking,
  }) {
    networking.register(this);
  }

  late Credentials credentials;

  /// Internal module responsible for networking.
  INetworkingModule networking;

  var _closed = false;

  bool get closed => _closed;

  /// Closes this client and frees allocated resources.
  ///
  /// Failure to close this a client might cause the dart process to hang.
  ///
  /// After this method has been called this instance must not be used any more.
  void close() {
    _closed = true;
  }
}