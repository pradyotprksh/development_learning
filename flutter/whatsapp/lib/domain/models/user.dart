import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:memory_cache/memory_cache.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class UserDetails {
  UserDetails({
    this.name,
    this.emailId,
    this.phoneNumber,
    this.profileImage,
    this.firestoreFilePath,
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

    final pin = data?[UserDetailsKey.pin] as String;
    MemoryCache.instance.create(UserDetailsKey.pin, pin * 4);

    final deviceDetails =
        data?[UserDetailsKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    return UserDetails(
      name: EncryptorService.decryptData(data?[UserDetailsKey.name] as String?),
      emailId: EncryptorService.decryptData(
          data?[UserDetailsKey.emailId] as String?),
      phoneNumber: EncryptorService.decryptData(
          data?[UserDetailsKey.phoneNumber] as String?),
      profileImage: EncryptorService.decryptData(
          data?[UserDetailsKey.profileImage] as String?),
      firestoreFilePath: EncryptorService.decryptData(
          data?[StatusKey.firestoreFilePath] as String?),
      userId: data?[UserDetailsKey.userId] as String,
      pin: pin,
      createdOnTimeStamp: data?[UserDetailsKey.createdOnTimeStamp] as int? ?? 0,
      updatedOnTimeStamp: data?[UserDetailsKey.updatedOnTimeStamp] as int? ?? 0,
      allDetailsAvailable:
          data?[UserDetailsKey.allDetailsAvailable] as bool? ?? false,
      userDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
    );
  }

  final String? name;
  final String? emailId;
  final String? phoneNumber;
  final String? profileImage;
  final String? firestoreFilePath;
  final String userId;
  final String pin;
  final bool allDetailsAvailable;
  final int? createdOnTimeStamp;
  final int? updatedOnTimeStamp;
  final UserDeviceDetails? userDeviceDetails;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        if (name != null)
          UserDetailsKey.name: EncryptorService.encryptData(name),
        if (emailId != null)
          UserDetailsKey.emailId: EncryptorService.encryptData(emailId),
        if (phoneNumber != null)
          UserDetailsKey.phoneNumber: EncryptorService.encryptData(phoneNumber),
        if (profileImage != null)
          UserDetailsKey.profileImage:
              EncryptorService.encryptData(profileImage),
        if (firestoreFilePath != null)
          StatusKey.firestoreFilePath:
              EncryptorService.encryptData(firestoreFilePath),
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
