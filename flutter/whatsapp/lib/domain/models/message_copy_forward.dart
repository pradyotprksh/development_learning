import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageCopyForwardDetails {
  MessageCopyForwardDetails({
    required this.userId,
    required this.messageId,
    required this.isCopied,
    required this.isForwardOptionSelected,
    this.createdOnTimeStamp,
    this.userDeviceDetails,
  });

  factory MessageCopyForwardDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails = data?[FirestoreItemKey.createdByUserDeviceDetails]
            as Map<String, dynamic>? ??
        <String, dynamic>{};

    return MessageCopyForwardDetails(
      userId: data?[FirestoreItemKey.userId] as String,
      messageId: data?[FirestoreItemKey.messageId] as String,
      isCopied: data?[FirestoreItemKey.isCopied] as bool,
      isForwardOptionSelected:
          data?[FirestoreItemKey.isForwardOptionSelected] as bool,
      createdOnTimeStamp:
          data?[FirestoreItemKey.createdOnTimeStamp] as int? ?? 0,
      userDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
    );
  }

  final String userId;
  final int? createdOnTimeStamp;
  final UserDeviceDetails? userDeviceDetails;
  final String messageId;
  final bool isCopied;
  final bool isForwardOptionSelected;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.userId: userId,
        FirestoreItemKey.messageId: messageId,
        FirestoreItemKey.isCopied: isCopied,
        FirestoreItemKey.isForwardOptionSelected: isForwardOptionSelected,
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };
}
