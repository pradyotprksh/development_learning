import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:equatable/equatable.dart';
import 'package:memory_cache/memory_cache.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class UserDetails extends Equatable {
  const UserDetails({
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
    this.lastPinConfirmationTimeStamp,
    this.updatedOnTimeStamp,
    this.currentMood,
    this.avatarDetails,
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
      lastPinConfirmationTimeStamp:
          data?[FirestoreItemKey.lastPinConfirmationTimeStamp] as int? ?? 0,
      allDetailsAvailable:
          data?[FirestoreItemKey.allDetailsAvailable] as bool? ?? false,
      isEmailVerified:
          data?[FirestoreItemKey.isEmailVerified] as bool? ?? false,
      isPhoneNumberVerified:
          data?[FirestoreItemKey.isPhoneNumberVerified] as bool? ?? false,
      isOnline: data?[FirestoreItemKey.isOnline] as bool? ?? false,
      currentMood: data?[FirestoreItemKey.currentMood] as String?,
      userDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      avatarDetails:
          data?[FirestoreItemKey.avatarDetails] as Map<String, dynamic>?,
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
  final String? currentMood;
  final int? lastPinConfirmationTimeStamp;
  final Map<String, dynamic>? avatarDetails;

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
        if (lastPinConfirmationTimeStamp != null)
          FirestoreItemKey.lastPinConfirmationTimeStamp:
              lastPinConfirmationTimeStamp,
        if (currentMood != null) FirestoreItemKey.currentMood: currentMood,
        FirestoreItemKey.userId: userId,
        FirestoreItemKey.allDetailsAvailable: allDetailsAvailable,
        FirestoreItemKey.isEmailVerified: isEmailVerified,
        FirestoreItemKey.isPhoneNumberVerified: isPhoneNumberVerified,
        FirestoreItemKey.isOnline: isOnline,
        FirestoreItemKey.pin: pin,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
        if (avatarDetails != null)
          FirestoreItemKey.avatarDetails: avatarDetails,
      };

  @override
  List<Object?> get props => [
        name,
        emailId,
        phoneNumber,
        profileImage,
        firestoreFilePath,
        userId,
        pin,
        allDetailsAvailable,
        lastPinConfirmationTimeStamp,
        createdOnTimeStamp,
        updatedOnTimeStamp,
        userDeviceDetails,
        isEmailVerified,
        isPhoneNumberVerified,
        isOnline,
        currentMood,
      ];
}
