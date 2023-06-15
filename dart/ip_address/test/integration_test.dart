import 'package:get_ip_address/get_ip_address.dart';
import 'package:test/test.dart';

void main() {
  IpAddress? ipStringAddress;
  IpAddress? ipMapAddress;

  group('Check for Ip Address', () {
    setUp(() {
      ipStringAddress = IpAddress();
      ipMapAddress = IpAddress(type: RequestType.json);
    });

    test('String Ip Address', () async {
      dynamic data = await ipStringAddress!.getIpAddress();
      expect(true, data is String);
    });

    test('Map Ip Address', () async {
      dynamic data = await ipMapAddress!.getIpAddress();
      expect(true, data is Map);
    });
  });
}
