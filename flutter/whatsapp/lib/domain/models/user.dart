import 'dart:convert';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:encrypt/encrypt.dart';
import 'package:whatsapp/core/core.dart';

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
  static const buildNumber = 'buildNumber';
  static const userDeviceDetails = 'userDeviceDetails';
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
    required this.userId,
    required this.pin,
    this.allDetailsAvailable = false,
    this.userDeviceDetails,
  });

  factory UserDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();
    final documentId = snapshot.id;

    final pin = data?[UserDetailsKey.pin] as String? ?? IV.fromLength(8).base64;
    final userDetails = data?[documentId] as String? ?? '';
    final deviceDetails =
        data?[UserDetailsKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    final decryptedData = jsonDecode(
      EncryptorService.decryptData(
        userDetails,
        '$pin$pin$pin$pin',
      ),
    ) as Map<String, dynamic>?;

    return UserDetails(
      name: decryptedData?[UserDetailsKey.name] as String?,
      emailId: decryptedData?[UserDetailsKey.emailId] as String?,
      phoneNumber: decryptedData?[UserDetailsKey.phoneNumber] as String?,
      userId: decryptedData?[UserDetailsKey.userId] as String,
      pin: pin,
      allDetailsAvailable:
          decryptedData?[UserDetailsKey.allDetailsAvailable] as bool? ?? false,
      userDeviceDetails: UserDeviceDetails.fromMap(
        jsonDecode(jsonEncode(deviceDetails)) as Map<String, dynamic>?,
      ),
    );
  }

  final String? name;
  final String? emailId;
  final String? phoneNumber;
  final String userId;
  final String pin;
  final bool allDetailsAvailable;
  final UserDeviceDetails? userDeviceDetails;

  Map<String, dynamic> toFirestore() {
    final encryptedData = EncryptorService.encryptData(
      jsonEncode(
        <String, dynamic>{
          if (name != null) UserDetailsKey.name: name,
          if (emailId != null) UserDetailsKey.emailId: emailId,
          if (phoneNumber != null) UserDetailsKey.phoneNumber: phoneNumber,
          UserDetailsKey.userId: userId,
          UserDetailsKey.allDetailsAvailable: allDetailsAvailable,
        },
      ),
      '$pin$pin$pin$pin',
    );

    return <String, dynamic>{
      userId: encryptedData,
      UserDetailsKey.pin: pin,
      if (userDeviceDetails != null)
        UserDetailsKey.userDeviceDetails: userDeviceDetails!.toMap(),
    };
  }
}
