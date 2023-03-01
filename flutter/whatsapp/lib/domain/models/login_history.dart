import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

class LoginHistoryDetails {
  LoginHistoryDetails({
    required this.userId,
    this.createdOnTimeStamp,
    this.userDeviceDetails,
  });

  factory LoginHistoryDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    return LoginHistoryDetails(
      userId: data?[FirestoreItemKey.userId] as String,
      createdOnTimeStamp:
          data?[FirestoreItemKey.createdOnTimeStamp] as int? ?? 0,
    );
  }

  final String userId;
  final int? createdOnTimeStamp;
  final UserDeviceDetails? userDeviceDetails;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.userId: userId,
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
