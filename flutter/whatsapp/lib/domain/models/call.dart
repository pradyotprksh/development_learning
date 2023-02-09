import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class CallDetails extends Equatable {
  const CallDetails({
    required this.startedByUserId,
    required this.usersId,
    required this.isPhoneCall,
    required this.isGroupCall,
    required this.isVideoCall,
    this.createdOnTimeStamp,
    this.groupId,
    this.startedByUserDeviceDetails,
    this.callId = '',
  });

  factory CallDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails = data?[FirestoreItemKey.startedByUserDeviceDetails]
            as Map<String, dynamic>? ??
        <String, dynamic>{};

    return CallDetails(
      startedByUserId: data?[FirestoreItemKey.startedByUserId] as String,
      usersId: (data?[FirestoreItemKey.usersId] as List<dynamic>)
          .map((dynamic e) => e.toString())
          .toList(),
      isPhoneCall: data?[FirestoreItemKey.isPhoneCall] as bool,
      isGroupCall: data?[FirestoreItemKey.isGroupCall] as bool,
      isVideoCall: data?[FirestoreItemKey.isVideoCall] as bool,
      createdOnTimeStamp: data?[FirestoreItemKey.createdOnTimeStamp] as int?,
      groupId: data?[FirestoreItemKey.groupId] as String?,
      startedByUserDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      callId: snapshot.id,
    );
  }

  final String startedByUserId;
  final List<String> usersId;
  final bool isPhoneCall;
  final bool isVideoCall;
  final bool isGroupCall;
  final int? createdOnTimeStamp;
  final String? groupId;
  final UserDeviceDetails? startedByUserDeviceDetails;
  final String callId;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.startedByUserId: startedByUserId,
        FirestoreItemKey.usersId: usersId,
        FirestoreItemKey.isPhoneCall: isPhoneCall,
        FirestoreItemKey.isGroupCall: isGroupCall,
        FirestoreItemKey.isVideoCall: isVideoCall,
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (groupId != null) FirestoreItemKey.groupId: groupId,
        if (startedByUserDeviceDetails != null)
          FirestoreItemKey.startedByUserDeviceDetails:
              startedByUserDeviceDetails!.toMap(),
      };

  @override
  List<Object?> get props => [
        startedByUserId,
        usersId,
        isPhoneCall,
        isVideoCall,
        isGroupCall,
        createdOnTimeStamp,
        groupId,
        startedByUserDeviceDetails,
        callId,
      ];
}
