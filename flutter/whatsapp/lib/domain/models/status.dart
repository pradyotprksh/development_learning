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
    this.messageDetails,
    this.statusId = '',
    this.size = 0,
  });

  factory StatusDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails =
        data?[FirestoreItemKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    return StatusDetails(
      status: EncryptorService.decryptData(
          data?[FirestoreItemKey.status] as String),
      fontFamily: data?[FirestoreItemKey.fontFamily] as String,
      color: data?[FirestoreItemKey.color] as int,
      filePathUrl: EncryptorService.decryptData(
          data?[FirestoreItemKey.filePathUrl] as String?),
      firestoreFilePath: EncryptorService.decryptData(
          data?[FirestoreItemKey.firestoreFilePath] as String?),
      userId: data?[FirestoreItemKey.userId] as String,
      createdOnTimeStamp:
          data?[FirestoreItemKey.createdOnTimeStamp] as int? ?? 0,
      userReference:
          data?[FirestoreItemKey.userReference] as DocumentReference?,
      userDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      statusId: snapshot.id,
      isFileImage: data?[FirestoreItemKey.isFileImage] as bool?,
      size: (data?.getDocumentSize() ?? 0).toDouble(),
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
  final SingleMessageDetails? messageDetails;
  final double size;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.status: EncryptorService.encryptData(status),
        FirestoreItemKey.fontFamily: fontFamily,
        FirestoreItemKey.color: color,
        if (filePathUrl != null)
          FirestoreItemKey.filePathUrl:
              EncryptorService.encryptData(filePathUrl),
        if (firestoreFilePath != null)
          FirestoreItemKey.firestoreFilePath:
              EncryptorService.encryptData(firestoreFilePath),
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (userReference != null)
          FirestoreItemKey.userReference: userReference,
        FirestoreItemKey.userId: userId,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
        if (isFileImage != null) FirestoreItemKey.isFileImage: isFileImage,
        if (messageDetails != null)
          FirestoreItemKey.messageDetails: messageDetails!.toFirestore()
      };

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
