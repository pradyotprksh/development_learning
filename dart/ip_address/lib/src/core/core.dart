import 'package:get_ip_address/get_ip_address.dart';
import 'package:get_ip_address/src/core/net/net.dart';
import 'package:get_ip_address/src/core/networking/network_request.dart';

class Core {
  /// The main ipAddress constructor which will be called to get the ipAddress.
  ///
  /// [requestType] : A request type which will change the response
  /// type.
  Core({
    required this.requestType,
  }) {
    _networkRequest = NetworkRequest();
  }

  /// Current version of this library.
  static String version = '0.0.5';

  /// The type of request.
  RequestType requestType;

  /// A network request handler which will help in finding the iP Address.
  late NetworkRequest _networkRequest;

  /// Get Ip address of the system based on [requestType].
  Future<dynamic> getIpAddress() async {
    switch (requestType) {
      case RequestType.text:
        return await _networkRequest.getIpAddressText();
      case RequestType.json:
        return await _networkRequest.getIpAddressJson();
      default:
        return '0.0.0.0';
    }
  }
}
