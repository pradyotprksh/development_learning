import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusDetails {
  StatusDetails({
    required this.status,
    required this.fontFamily,
    required this.color,
    required this.userId,
    this.filePathUrl,
    this.userReference,
    this.firestoreFilePath,
    this.userDeviceDetails,
    this.createdOnTimeStamp,
    this.isFileImage,
    this.statusId = '',
  });

  factory StatusDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails =
        data?[UserDetailsKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    return StatusDetails(
      status: EncryptorService.decryptData(data?[StatusKey.status] as String),
      fontFamily: data?[StatusKey.fontFamily] as String,
      color: data?[StatusKey.color] as int,
      filePathUrl:
          EncryptorService.decryptData(data?[StatusKey.filePathUrl] as String?),
      firestoreFilePath: EncryptorService.decryptData(
          data?[StatusKey.firestoreFilePath] as String?),
      userId: data?[UserDetailsKey.userId] as String,
      createdOnTimeStamp: data?[UserDetailsKey.createdOnTimeStamp] as int? ?? 0,
      userReference: data?[UserDetailsKey.userReference] as DocumentReference?,
      userDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      statusId: snapshot.id,
      isFileImage: data?[StatusKey.isFileImage] as bool?,
    );
  }

  final String status;
  final String fontFamily;
  final int color;
  final String userId;
  final String? filePathUrl;
  final String? firestoreFilePath;
  final int? createdOnTimeStamp;
  final UserDeviceDetails? userDeviceDetails;
  final String statusId;
  final bool? isFileImage;
  final DocumentReference? userReference;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        StatusKey.status: EncryptorService.encryptData(status),
        StatusKey.fontFamily: fontFamily,
        StatusKey.color: color,
        if (filePathUrl != null)
          StatusKey.filePathUrl: EncryptorService.encryptData(filePathUrl),
        if (firestoreFilePath != null)
          StatusKey.firestoreFilePath:
              EncryptorService.encryptData(firestoreFilePath),
        if (createdOnTimeStamp != null)
          UserDetailsKey.createdOnTimeStamp: createdOnTimeStamp,
        if (userReference != null) UserDetailsKey.userReference: userReference,
        UserDetailsKey.userId: userId,
        if (userDeviceDetails != null)
          UserDetailsKey.userDeviceDetails: userDeviceDetails!.toMap(),
        if (isFileImage != null) StatusKey.isFileImage: isFileImage,
      };
}
