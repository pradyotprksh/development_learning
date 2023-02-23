import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/domain.dart';

class GroupMessageDetails extends Equatable {
  const GroupMessageDetails({
    required this.users,
    required this.name,
    required this.createdByUserId,
    this.lastMessage,
    this.createdOnTimeStamp,
    this.createdByUserDeviceDetails,
    this.lastMessageOnTimeStamp,
    this.lastMessageByUserId,
    this.profileImage,
    this.firestoreFilePath,
    this.groupId = '',
    this.size = 0,
  });

  factory GroupMessageDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails = data?[FirestoreItemKey.createdByUserDeviceDetails]
            as Map<String, dynamic>? ??
        <String, dynamic>{};

    return GroupMessageDetails(
      users: (data?[FirestoreItemKey.users] as List<dynamic>)
          .map((dynamic e) => e.toString())
          .toList(),
      createdByUserId: data?[FirestoreItemKey.createdByUserId] as String,
      name: data?[FirestoreItemKey.name] as String,
      lastMessageOnTimeStamp:
          data?[FirestoreItemKey.lastMessageOnTimeStamp] as int?,
      lastMessageByUserId:
          data?[FirestoreItemKey.lastMessageByUserId] as String?,
      profileImage: data?[FirestoreItemKey.profileImage] as String?,
      firestoreFilePath: data?[FirestoreItemKey.firestoreFilePath] as String?,
      createdOnTimeStamp:
          data?[FirestoreItemKey.createdOnTimeStamp] as int? ?? 0,
      createdByUserDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      groupId: snapshot.id,
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
  final String groupId;
  final String? profileImage;
  final String? firestoreFilePath;
  final String name;
  final double size;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.users: users,
        FirestoreItemKey.name: name,
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
        if (profileImage != null) FirestoreItemKey.profileImage: profileImage,
        if (firestoreFilePath != null)
          FirestoreItemKey.firestoreFilePath: firestoreFilePath,
        if (createdByUserDeviceDetails != null)
          FirestoreItemKey.createdByUserDeviceDetails:
              createdByUserDeviceDetails!.toMap(),
      };

  @override
  List<Object?> get props => [
        users,
        name,
        createdOnTimeStamp,
        createdByUserId,
        createdByUserDeviceDetails,
        lastMessage,
        lastMessageOnTimeStamp,
        lastMessageByUserId,
        groupId,
        profileImage,
        firestoreFilePath,
      ];

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
