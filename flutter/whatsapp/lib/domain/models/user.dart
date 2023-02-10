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
    this.isEmailVerified = false,
    this.isPhoneNumberVerified = false,
    this.isOnline = true,
  });

  factory UserDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final pin = data?[FirestoreItemKey.pin] as String;
    MemoryCache.instance.create(FirestoreItemKey.pin, pin * 4);

    final deviceDetails =
        data?[FirestoreItemKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    return UserDetails(
      name:
          EncryptorService.decryptData(data?[FirestoreItemKey.name] as String?),
      emailId: EncryptorService.decryptData(
          data?[FirestoreItemKey.emailId] as String?),
      phoneNumber: EncryptorService.decryptData(
          data?[FirestoreItemKey.phoneNumber] as String?),
      profileImage: EncryptorService.decryptData(
          data?[FirestoreItemKey.profileImage] as String?),
      firestoreFilePath: EncryptorService.decryptData(
          data?[FirestoreItemKey.firestoreFilePath] as String?),
      userId: data?[FirestoreItemKey.userId] as String,
      pin: pin,
      createdOnTimeStamp:
          data?[FirestoreItemKey.createdOnTimeStamp] as int? ?? 0,
      updatedOnTimeStamp:
          data?[FirestoreItemKey.updatedOnTimeStamp] as int? ?? 0,
      allDetailsAvailable:
          data?[FirestoreItemKey.allDetailsAvailable] as bool? ?? false,
      isEmailVerified:
          data?[FirestoreItemKey.isEmailVerified] as bool? ?? false,
      isPhoneNumberVerified:
          data?[FirestoreItemKey.isPhoneNumberVerified] as bool? ?? false,
      isOnline: data?[FirestoreItemKey.isOnline] as bool? ?? false,
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
  final bool isEmailVerified;
  final bool isPhoneNumberVerified;
  final bool isOnline;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        if (name != null)
          FirestoreItemKey.name: EncryptorService.encryptData(name),
        if (emailId != null)
          FirestoreItemKey.emailId: EncryptorService.encryptData(emailId),
        if (phoneNumber != null)
          FirestoreItemKey.phoneNumber:
              EncryptorService.encryptData(phoneNumber),
        if (profileImage != null)
          FirestoreItemKey.profileImage:
              EncryptorService.encryptData(profileImage),
        if (firestoreFilePath != null)
          FirestoreItemKey.firestoreFilePath:
              EncryptorService.encryptData(firestoreFilePath),
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (updatedOnTimeStamp != null)
          FirestoreItemKey.updatedOnTimeStamp: updatedOnTimeStamp,
        FirestoreItemKey.userId: userId,
        FirestoreItemKey.allDetailsAvailable: allDetailsAvailable,
        FirestoreItemKey.isEmailVerified: isEmailVerified,
        FirestoreItemKey.isPhoneNumberVerified: isPhoneNumberVerified,
        FirestoreItemKey.isOnline: isOnline,
        FirestoreItemKey.pin: pin,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };
}
