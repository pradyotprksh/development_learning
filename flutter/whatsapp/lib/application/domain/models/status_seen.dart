import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/domain/domain.dart';

class StatusSeenDetails extends Equatable {
  const StatusSeenDetails({
    this.userDeviceDetails,
    required this.userId,
    required this.statusId,
    this.seenOnTimeStamp,
    this.size = 0,
  });

  factory StatusSeenDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails =
        data?[FirestoreItemKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    return StatusSeenDetails(
      userId: data?[FirestoreItemKey.userId] as String,
      statusId: data?[FirestoreItemKey.statusId] as String,
      seenOnTimeStamp: data?[FirestoreItemKey.seenOnTimeStamp] as int? ?? 0,
      userDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      size: (data?.getDocumentSize() ?? 0).toDouble(),
    );
  }

  final UserDeviceDetails? userDeviceDetails;
  final String userId;
  final String statusId;
  final int? seenOnTimeStamp;
  final double size;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        if (seenOnTimeStamp != null)
          FirestoreItemKey.seenOnTimeStamp: seenOnTimeStamp,
        FirestoreItemKey.userId: userId,
        FirestoreItemKey.statusId: statusId,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };

  @override
  List<Object?> get props => [
        userId,
        statusId,
        seenOnTimeStamp,
      ];

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
