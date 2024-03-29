import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:equatable/equatable.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class UserDetails extends Equatable {
  const UserDetails({
    required this.userId,
    required this.pin,
    this.name,
    this.emailId,
    this.phoneNumber,
    this.profileImage,
    this.firestoreFilePath,
    this.userDeviceDetails,
    this.createdOnTimeStamp,
    this.lastPinConfirmationTimeStamp,
    this.updatedOnTimeStamp,
    this.currentMood,
    this.avatarDetails,
    this.allDetailsAvailable = false,
    this.isEmailVerified = false,
    this.isPhoneNumberVerified = false,
    this.isOnline = true,
    this.useAvatarAsProfile = false,
    this.size = 0,
  });

  factory UserDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

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
      pin: data?[FirestoreItemKey.pin] as String,
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
      useAvatarAsProfile:
          data?[FirestoreItemKey.useAvatarAsProfile] as bool? ?? false,
      isPhoneNumberVerified:
          data?[FirestoreItemKey.isPhoneNumberVerified] as bool? ?? false,
      isOnline: data?[FirestoreItemKey.isOnline] as bool? ?? false,
      currentMood: data?[FirestoreItemKey.currentMood] as String?,
      avatarDetails: data?[FirestoreItemKey.avatarDetails] as String?,
      size: (data?.getDocumentSize() ?? 0).toDouble(),
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
  final String? avatarDetails;
  final bool useAvatarAsProfile;
  final double size;

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
        FirestoreItemKey.useAvatarAsProfile: useAvatarAsProfile,
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
        useAvatarAsProfile,
        avatarDetails,
      ];

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
