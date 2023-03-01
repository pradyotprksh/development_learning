import 'package:whatsapp/domain/domain.dart';

class UserDeviceDetails {
  UserDeviceDetails({
    this.deviceInfo,
    this.ipAddress,
    this.appName,
    this.packageName,
    this.version,
    this.buildNumber,
    this.installer,
    this.installerName,
  });

  final Map<String, dynamic>? deviceInfo;
  final String? ipAddress;
  final String? appName;
  final String? packageName;
  final String? version;
  final String? buildNumber;
  final String? installer;
  final String? installerName;

  Map<String, dynamic> toMap() => <String, dynamic>{
        if (deviceInfo != null) FirestoreItemKey.deviceInfo: deviceInfo,
        if (ipAddress != null) FirestoreItemKey.ipAddress: ipAddress,
        if (appName != null) FirestoreItemKey.appName: appName,
        if (packageName != null) FirestoreItemKey.packageName: packageName,
        if (version != null) FirestoreItemKey.version: version,
        if (buildNumber != null) FirestoreItemKey.buildNumber: buildNumber,
        if (installer != null) FirestoreItemKey.installer: installer,
        if (installerName != null)
          FirestoreItemKey.installerName: installerName,
      };

  Map<String, String> toStringMap() => <String, String>{
        if (ipAddress != null) FirestoreItemKey.ipAddress: ipAddress!,
        if (appName != null) FirestoreItemKey.appName: appName!,
        if (packageName != null) FirestoreItemKey.packageName: packageName!,
        if (version != null) FirestoreItemKey.version: version!,
        if (buildNumber != null) FirestoreItemKey.buildNumber: buildNumber!,
        if (installer != null) FirestoreItemKey.installer: installer!,
        if (installerName != null)
          FirestoreItemKey.installerName: installerName!,
      };
}
