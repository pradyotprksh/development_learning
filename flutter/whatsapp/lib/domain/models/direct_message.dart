import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class DirectMessageDetails {
  DirectMessageDetails({
    required this.users,
    required this.createdByUserId,
    this.lastMessage,
    this.messageId = '',
    this.createdOnTimeStamp,
    this.createdByUserDeviceDetails,
  });

  factory DirectMessageDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails = data?[FirestoreItemKey.createdByUserDeviceDetails]
            as Map<String, dynamic>? ??
        <String, dynamic>{};

    return DirectMessageDetails(
      users: (data?[FirestoreItemKey.users] as List<dynamic>)
          .map((dynamic e) => e.toString())
          .toList(),
      createdByUserId: data?[FirestoreItemKey.createdByUserId] as String,
      createdOnTimeStamp:
          data?[FirestoreItemKey.createdOnTimeStamp] as int? ?? 0,
      createdByUserDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      messageId: snapshot.id,
      lastMessage: EncryptorService.decryptData(
          data?[FirestoreItemKey.lastMessage] as String?),
    );
  }

  final List<String> users;
  final String createdByUserId;
  final int? createdOnTimeStamp;
  final UserDeviceDetails? createdByUserDeviceDetails;
  final String? lastMessage;
  final String messageId;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.users: users,
        if (lastMessage != null)
          FirestoreItemKey.lastMessage:
              EncryptorService.encryptData(lastMessage),
        FirestoreItemKey.createdByUserId: createdByUserId,
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (createdByUserDeviceDetails != null)
          FirestoreItemKey.createdByUserDeviceDetails:
              createdByUserDeviceDetails!.toMap(),
      };
}