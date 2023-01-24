import 'package:whatsapp/domain/domain.dart';

class UserDeviceDetails {
  UserDeviceDetails({
    this.deviceInfo,
    this.ipAddress,
    this.appName,
    this.packageName,
    this.version,
    this.buildNumber,
  });

  factory UserDeviceDetails.fromMap(Map<String, dynamic>? json) =>
      UserDeviceDetails(
        version: json?[UserDetailsKey.version] as String?,
        buildNumber: json?[UserDetailsKey.buildNumber] as String?,
      );

  final Map<String, dynamic>? deviceInfo;
  final String? ipAddress;
  final String? appName;
  final String? packageName;
  final String? version;
  final String? buildNumber;

  Map<String, dynamic> toMap() => <String, dynamic>{
        if (deviceInfo != null) UserDetailsKey.deviceInfo: deviceInfo,
        if (ipAddress != null) UserDetailsKey.ipAddress: ipAddress,
        if (appName != null) UserDetailsKey.appName: appName,
        if (packageName != null) UserDetailsKey.packageName: packageName,
        if (version != null) UserDetailsKey.version: version,
        if (buildNumber != null) UserDetailsKey.buildNumber: buildNumber,
      };
}
