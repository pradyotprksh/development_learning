import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/domain.dart';

class DirectMessageDetails extends Equatable {
  const DirectMessageDetails({
    required this.users,
    required this.createdByUserId,
    this.lastMessage,
    this.messageId = '',
    this.createdOnTimeStamp,
    this.createdByUserDeviceDetails,
    this.lastMessageOnTimeStamp,
    this.lastMessageByUserId,
    this.size = 0,
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
      lastMessageOnTimeStamp:
          data?[FirestoreItemKey.lastMessageOnTimeStamp] as int?,
      lastMessageByUserId:
          data?[FirestoreItemKey.lastMessageByUserId] as String?,
      createdOnTimeStamp:
          data?[FirestoreItemKey.createdOnTimeStamp] as int? ?? 0,
      createdByUserDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      messageId: snapshot.id,
      lastMessage: EncryptorService.decryptData(
          data?[FirestoreItemKey.lastMessage] as String?),
      size: (data?.getDocumentSize() ?? 0).toDouble(),
    );
  }

  final List<String> users;
  final String createdByUserId;
  final int? createdOnTimeStamp;
  final UserDeviceDetails? createdByUserDeviceDetails;
  final String? lastMessage;
  final int? lastMessageOnTimeStamp;
  final String? lastMessageByUserId;
  final String messageId;
  final double size;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.users: users,
        if (lastMessage != null)
          FirestoreItemKey.lastMessage:
              EncryptorService.encryptData(lastMessage),
        FirestoreItemKey.createdByUserId: createdByUserId,
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (lastMessageOnTimeStamp != null)
          FirestoreItemKey.lastMessageOnTimeStamp: lastMessageOnTimeStamp,
        if (lastMessageByUserId != null)
          FirestoreItemKey.lastMessageByUserId: lastMessageByUserId,
        if (createdByUserDeviceDetails != null)
          FirestoreItemKey.createdByUserDeviceDetails:
              createdByUserDeviceDetails!.toMap(),
      };

  @override
  List<Object?> get props => [
        users,
        createdOnTimeStamp,
        createdByUserId,
        createdByUserDeviceDetails,
        lastMessage,
        lastMessageOnTimeStamp,
        lastMessageByUserId,
        messageId,
      ];

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
