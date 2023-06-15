import 'package:get_ip_address/get_ip_address.dart';

/// Network requester which will get the data from the remote.
class NetworkRequest extends NetworkHandler {
  NetworkRequest() {
    _remoteHandler = RemoteHandler();
  }

  /// A remote handler which will handle the remote calls.
  late RemoteHandler _remoteHandler;

  @override
  Future<Map<String, dynamic>> getIpAddressJson() async {
    try {
      var data = await _remoteHandler.getIpAddressText();
      return <String, dynamic>{'ip': data};
    } catch (_) {
      throw IpAddressException('Not able to find the Ip Address.');
    }
  }

  @override
  Future<String> getIpAddressText() async {
    try {
      return await _remoteHandler.getIpAddressText();
    } catch (_) {
      throw IpAddressException('Not able to find the Ip Address.');
    }
  }
}
