import 'package:device_info_plus/device_info_plus.dart';
import 'package:network_info_plus/network_info_plus.dart';
import 'package:package_info_plus/package_info_plus.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class DeviceDetailsImplementation extends DeviceDetails {
  @override
  Future<UserDeviceDetails> getDeviceDetails() async {
    final deviceInfo = DeviceInfoPlugin();
    final info = NetworkInfo();
    final packageInfo = await PackageInfo.fromPlatform();

    final deviceDetails = await deviceInfo.deviceInfo;
    final ipAddress = await info.getWifiIP();
    final appName = packageInfo.appName;
    final packageName = packageInfo.packageName;
    final version = packageInfo.version;
    final buildNumber = packageInfo.buildNumber;

    return UserDeviceDetails(
      deviceInfo: deviceDetails.data,
      ipAddress: ipAddress,
      appName: appName,
      packageName: packageName,
      version: version,
      buildNumber: buildNumber,
    );
  }
}
