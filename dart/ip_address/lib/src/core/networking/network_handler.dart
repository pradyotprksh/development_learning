/// The network handler which will handle all the data related work.
abstract class NetworkHandler {
  /// Get IpAddress in text format.
  Future<String> getIpAddressText();

  /// Get IpAddress in map format.
  Future<Map<String, dynamic>> getIpAddressJson();
}
