import 'package:get_ip_address/get_ip_address.dart';
import 'package:http/http.dart' as http;

/// A device handler which will handle all the remote related work like
/// saving or getting the data.
class RemoteHandler extends NetworkHandler {
  @override
  Future<Map<String, dynamic>> getIpAddressJson() {
    throw IpAddressException('Not able to find the Ip Address.');
  }

  @override
  Future<String> getIpAddressText() async {
    try {
      var response = await http.get(
        Uri.parse(
          'https://api64.ipify.org',
        ),
      );
      if (response.statusCode == 200) {
        return response.body;
      } else {
        throw IpAddressException('Not able to find the Ip Address.');
      }
    } catch (_) {
      throw IpAddressException('Not able to find the Ip Address.');
    }
  }
}
