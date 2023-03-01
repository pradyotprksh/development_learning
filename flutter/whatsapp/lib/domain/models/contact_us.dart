import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

class ContactUsDetails {
  ContactUsDetails({
    required this.description,
    required this.createdByUserId,
    this.createdOnTimeStamp,
    this.userDeviceDetails,
  });

  factory ContactUsDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    return ContactUsDetails(
      description: data?[FirestoreItemKey.description] as String,
      createdByUserId: data?[FirestoreItemKey.createdByUserId] as String,
      createdOnTimeStamp:
          data?[FirestoreItemKey.createdOnTimeStamp] as int? ?? 0,
    );
  }

  final String description;
  final String createdByUserId;
  final int? createdOnTimeStamp;
  final UserDeviceDetails? userDeviceDetails;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.description: description,
        FirestoreItemKey.createdByUserId: createdByUserId,
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
