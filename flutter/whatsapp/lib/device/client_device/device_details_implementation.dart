import 'package:device_info_plus/device_info_plus.dart';
import 'package:get_ip_address/get_ip_address.dart';
import 'package:installer_info/installer_info.dart';
import 'package:network_info_plus/network_info_plus.dart';
import 'package:package_info_plus/package_info_plus.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class DeviceDetailsImplementation extends DeviceDetails {
  @override
  Future<UserDeviceDetails> getDeviceDetails() async {
    final deviceInfo = DeviceInfoPlugin();
    final info = NetworkInfo();
    final getIpAddress = IpAddress(type: RequestType.text);
    final packageInfo = await PackageInfo.fromPlatform();
    final installerInfo = await getInstallerInfo();

    final deviceDetails = await deviceInfo.deviceInfo;
    final ipAddress = AppDetails.isWeb
        ? await getIpAddress.getIp() as String
        : await info.getWifiIP();
    final appName = packageInfo.appName;
    final packageName = packageInfo.packageName;
    final version = packageInfo.version;
    final buildNumber = packageInfo.buildNumber;

    Map<String, dynamic>? webDeviceDetails;
    if (AppDetails.isWeb) {
      webDeviceDetails = deviceDetails.data;
      webDeviceDetails[AppConstants.browserName] =
          (webDeviceDetails[AppConstants.browserName] as BrowserName).name;
    }

    return UserDeviceDetails(
      deviceInfo: webDeviceDetails ?? deviceDetails.data,
      ipAddress: ipAddress,
      appName: appName,
      packageName: packageName,
      version: version,
      buildNumber: buildNumber,
      installer: installerInfo?.installer?.name,
      installerName: installerInfo?.installerName,
    );
  }
}
