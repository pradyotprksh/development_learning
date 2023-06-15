import 'package:get_ip_address/get_ip_address.dart';

void main() async {
  try {
    /// Initialize Ip Address
    var ipAddress = IpAddress(type: RequestType.json);

    /// Get the IpAddress based on requestType.
    dynamic data = await ipAddress.getIpAddress();
    print(data.toString());
  } on IpAddressException catch (exception) {
    /// Handle the exception.
    print(exception.message);
  }
}
