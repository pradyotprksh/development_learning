import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class SingleMessageDetails {
  SingleMessageDetails({
    required this.message,
    required this.sentByUserId,
    required this.sentToUserId,
    this.filePathUrl,
    this.sentToUserReference,
    this.firestoreFilePath,
    this.sentByUserDeviceDetails,
    this.sentOnTimeStamp,
    this.isFileImage,
    this.messageId = '',
  });

  factory SingleMessageDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails = data?[FirestoreItemKey.sentByUserDeviceDetails]
            as Map<String, dynamic>? ??
        <String, dynamic>{};

    return SingleMessageDetails(
      message: EncryptorService.decryptData(
          data?[FirestoreItemKey.message] as String),
      sentByUserId: data?[FirestoreItemKey.sentByUserId] as String,
      sentToUserId: data?[FirestoreItemKey.sentToUserId] as String,
      filePathUrl: EncryptorService.decryptData(
          data?[FirestoreItemKey.filePathUrl] as String?),
      firestoreFilePath: EncryptorService.decryptData(
          data?[FirestoreItemKey.firestoreFilePath] as String?),
      sentOnTimeStamp: data?[FirestoreItemKey.sentOnTimeStamp] as int? ?? 0,
      sentToUserReference:
          data?[FirestoreItemKey.sentToUserReference] as DocumentReference?,
      sentByUserDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      messageId: snapshot.id,
      isFileImage: data?[FirestoreItemKey.isFileImage] as bool?,
    );
  }

  final String message;
  final String sentByUserId;
  final String sentToUserId;
  final String? filePathUrl;
  final String? firestoreFilePath;
  final int? sentOnTimeStamp;
  final UserDeviceDetails? sentByUserDeviceDetails;
  final String messageId;
  final bool? isFileImage;
  final DocumentReference? sentToUserReference;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.message: EncryptorService.encryptData(message),
        FirestoreItemKey.sentByUserId: sentByUserId,
        FirestoreItemKey.sentToUserId: sentToUserId,
        if (filePathUrl != null)
          FirestoreItemKey.filePathUrl:
              EncryptorService.encryptData(filePathUrl),
        if (firestoreFilePath != null)
          FirestoreItemKey.firestoreFilePath:
              EncryptorService.encryptData(firestoreFilePath),
        if (sentOnTimeStamp != null)
          FirestoreItemKey.sentOnTimeStamp: sentOnTimeStamp,
        if (sentByUserDeviceDetails != null)
          FirestoreItemKey.sentByUserDeviceDetails: sentByUserDeviceDetails,
        if (isFileImage != null) FirestoreItemKey.isFileImage: isFileImage,
        FirestoreItemKey.sentToUserReference: sentToUserReference,
      };
}
