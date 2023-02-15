import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageCopyForwardSavedDetails {
  MessageCopyForwardSavedDetails({
    required this.userId,
    required this.messageId,
    required this.isCopied,
    required this.isForwardOptionSelected,
    required this.isSaved,
    this.createdOnTimeStamp,
    this.userDeviceDetails,
  });

  factory MessageCopyForwardSavedDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails = data?[FirestoreItemKey.createdByUserDeviceDetails]
            as Map<String, dynamic>? ??
        <String, dynamic>{};

    return MessageCopyForwardSavedDetails(
      userId: data?[FirestoreItemKey.userId] as String,
      messageId: data?[FirestoreItemKey.messageId] as String,
      isCopied: data?[FirestoreItemKey.isCopied] as bool? ?? false,
      isSaved: data?[FirestoreItemKey.isSaved] as bool? ?? false,
      isForwardOptionSelected:
          data?[FirestoreItemKey.isForwardOptionSelected] as bool? ?? false,
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
  final bool isSaved;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.userId: userId,
        FirestoreItemKey.messageId: messageId,
        FirestoreItemKey.isCopied: isCopied,
        FirestoreItemKey.isSaved: isSaved,
        FirestoreItemKey.isForwardOptionSelected: isForwardOptionSelected,
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };
}
