import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

class SavedMessageDetails {
  SavedMessageDetails({
    required this.messageSentByUserId,
    required this.messageId,
    this.savedOnTimeStamp,
    this.userDeviceDetails,
  });

  factory SavedMessageDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails =
        data?[FirestoreItemKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    return SavedMessageDetails(
      messageSentByUserId:
          data?[FirestoreItemKey.messageSentByUserId] as String,
      messageId: data?[FirestoreItemKey.messageId] as String,
      savedOnTimeStamp: data?[FirestoreItemKey.savedOnTimeStamp] as int? ?? 0,
      userDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
    );
  }

  final String messageSentByUserId;
  final String messageId;
  final int? savedOnTimeStamp;
  final UserDeviceDetails? userDeviceDetails;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.messageSentByUserId: messageSentByUserId,
        FirestoreItemKey.messageId: messageId,
        if (savedOnTimeStamp != null)
          FirestoreItemKey.savedOnTimeStamp: savedOnTimeStamp,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };
}
