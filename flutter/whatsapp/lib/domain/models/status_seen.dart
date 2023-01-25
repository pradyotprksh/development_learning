import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusSeenDetails extends Equatable {
  const StatusSeenDetails({
    this.userDeviceDetails,
    required this.userId,
    required this.statusId,
    this.seenOnTimeStamp,
  });

  factory StatusSeenDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails =
        data?[UserDetailsKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    return StatusSeenDetails(
      userId: data?[UserDetailsKey.userId] as String,
      statusId: data?[StatusKey.statusId] as String,
      seenOnTimeStamp: data?[StatusKey.seenOnTimeStamp] as int? ?? 0,
      userDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
    );
  }

  final UserDeviceDetails? userDeviceDetails;
  final String userId;
  final String statusId;
  final int? seenOnTimeStamp;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        if (seenOnTimeStamp != null) StatusKey.seenOnTimeStamp: seenOnTimeStamp,
        UserDetailsKey.userId: userId,
        StatusKey.statusId: statusId,
        if (userDeviceDetails != null)
          UserDetailsKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };

  @override
  List<Object?> get props => [
        userId,
        statusId,
        seenOnTimeStamp,
      ];
}
