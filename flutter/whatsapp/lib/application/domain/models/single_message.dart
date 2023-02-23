import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/domain.dart';

class SingleMessageDetails extends Equatable {
  const SingleMessageDetails({
    required this.message,
    required this.sentByUserId,
    this.sentToUserId,
    this.filePathUrl,
    this.sentToUserReference,
    this.firestoreFilePath,
    this.sentByUserDeviceDetails,
    this.sentOnTimeStamp,
    this.isFileImage,
    this.attachments,
    this.messageId = '',
    this.isSystemMessage = false,
    this.size = 0,
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
      sentToUserId: data?[FirestoreItemKey.sentToUserId] as String?,
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
      isSystemMessage: data?[FirestoreItemKey.isSystemMessage] as bool,
      attachments: (data?[FirestoreItemKey.attachments] as List<dynamic>?)
          ?.map(
            (dynamic e) => FileInformationDetails.fromMap(
              e as Map<String, dynamic>,
            ),
          )
          .toList(),
      size: (data?.getDocumentSize() ?? 0).toDouble(),
    );
  }

  final String message;
  final String sentByUserId;
  final String? sentToUserId;
  final String? filePathUrl;
  final String? firestoreFilePath;
  final int? sentOnTimeStamp;
  final UserDeviceDetails? sentByUserDeviceDetails;
  final String messageId;
  final bool? isFileImage;
  final DocumentReference? sentToUserReference;
  final bool isSystemMessage;
  final List<FileInformationDetails>? attachments;
  final double size;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.message: EncryptorService.encryptData(message),
        FirestoreItemKey.sentByUserId: sentByUserId,
        if (sentToUserId != null) FirestoreItemKey.sentToUserId: sentToUserId,
        FirestoreItemKey.isSystemMessage: isSystemMessage,
        if (filePathUrl != null)
          FirestoreItemKey.filePathUrl:
              EncryptorService.encryptData(filePathUrl),
        if (firestoreFilePath != null)
          FirestoreItemKey.firestoreFilePath:
              EncryptorService.encryptData(firestoreFilePath),
        if (sentOnTimeStamp != null)
          FirestoreItemKey.sentOnTimeStamp: sentOnTimeStamp,
        if (sentByUserDeviceDetails != null)
          FirestoreItemKey.sentByUserDeviceDetails:
              sentByUserDeviceDetails!.toMap(),
        if (isFileImage != null) FirestoreItemKey.isFileImage: isFileImage,
        if (sentToUserReference != null)
          FirestoreItemKey.sentToUserReference: sentToUserReference,
        if (attachments != null)
          FirestoreItemKey.attachments:
              attachments?.map((e) => e.toMap()).toList(),
      };

  @override
  List<Object?> get props => [
        message,
        sentByUserId,
        sentToUserId,
        filePathUrl,
        firestoreFilePath,
        sentOnTimeStamp,
        sentByUserDeviceDetails,
        messageId,
        isFileImage,
        sentToUserReference,
        isSystemMessage,
      ];

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
