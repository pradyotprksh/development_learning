import 'dart:convert';

import 'package:cloud_firestore/cloud_firestore.dart';

abstract class UserDetailsKey {
  static const pin = 'pin';
  static const name = 'name';
  static const emailId = 'emailId';
  static const phoneNumber = 'phoneNumber';
  static const userId = 'userId';
  static const allDetailsAvailable = 'allDetailsAvailable';
  static const deviceInfo = 'deviceInfo';
  static const ipAddress = 'ipAddress';
  static const appName = 'appName';
  static const packageName = 'packageName';
  static const version = 'version';
  static const createdOnTimeStamp = 'createdOnTimeStamp';
  static const buildNumber = 'buildNumber';
  static const userDeviceDetails = 'userDeviceDetails';
  static const profileImage = 'profileImage';
  static const updatedOnTimeStamp = 'updatedOnTimeStamp';
}

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

class UserDetails {
  UserDetails({
    this.name,
    this.emailId,
    this.phoneNumber,
    this.profileImage,
    required this.userId,
    required this.pin,
    this.allDetailsAvailable = false,
    this.userDeviceDetails,
    this.createdOnTimeStamp,
    this.updatedOnTimeStamp,
  });

  factory UserDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails =
        data?[UserDetailsKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    return UserDetails(
      name: data?[UserDetailsKey.name] as String?,
      emailId: data?[UserDetailsKey.emailId] as String?,
      phoneNumber: data?[UserDetailsKey.phoneNumber] as String?,
      profileImage: data?[UserDetailsKey.profileImage] as String?,
      userId: data?[UserDetailsKey.userId] as String,
      pin: data?[UserDetailsKey.pin] as String,
      createdOnTimeStamp: data?[UserDetailsKey.createdOnTimeStamp] as int? ?? 0,
      updatedOnTimeStamp: data?[UserDetailsKey.updatedOnTimeStamp] as int? ?? 0,
      allDetailsAvailable:
          data?[UserDetailsKey.allDetailsAvailable] as bool? ?? false,
      userDeviceDetails: UserDeviceDetails.fromMap(
        jsonDecode(jsonEncode(deviceDetails)) as Map<String, dynamic>?,
      ),
    );
  }

  final String? name;
  final String? emailId;
  final String? phoneNumber;
  final String? profileImage;
  final String userId;
  final String pin;
  final bool allDetailsAvailable;
  final int? createdOnTimeStamp;
  final int? updatedOnTimeStamp;
  final UserDeviceDetails? userDeviceDetails;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        if (name != null) UserDetailsKey.name: name,
        if (emailId != null) UserDetailsKey.emailId: emailId,
        if (phoneNumber != null) UserDetailsKey.phoneNumber: phoneNumber,
        if (profileImage != null) UserDetailsKey.profileImage: profileImage,
        if (createdOnTimeStamp != null)
          UserDetailsKey.createdOnTimeStamp: createdOnTimeStamp,
        if (updatedOnTimeStamp != null)
          UserDetailsKey.updatedOnTimeStamp: updatedOnTimeStamp,
        UserDetailsKey.userId: userId,
        UserDetailsKey.allDetailsAvailable: allDetailsAvailable,
        UserDetailsKey.pin: pin,
        if (userDeviceDetails != null)
          UserDetailsKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };
}
