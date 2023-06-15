import 'package:get_ip_address/src/core.dart';

class IpAddress extends Core {
  /// The main ipAddress constructor which will be called to get the ipAddress.
  ///
  /// [type] : An optional request type which will change the response
  /// type based on the [type] given by the user.
  /// Default value is [RequestType.text].
  IpAddress({
    this.type = RequestType.text,
  }) : super(requestType: type);

  /// Current version of this library.
  static String version = Core.version;

  /// The type of request.
  RequestType type;

  /// Get ip address depending on the [requestType].
  Future<dynamic> getIp() => super.getIpAddress();
}
