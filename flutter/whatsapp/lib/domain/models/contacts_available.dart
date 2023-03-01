import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

class ContactsAvailableDetails {
  ContactsAvailableDetails({
    required this.userId,
    this.userReference,
    this.detailsFetchedOn,
    this.userDeviceDetails,
    this.contactId = '',
    this.size = 0,
  });

  factory ContactsAvailableDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    return ContactsAvailableDetails(
      userId: data?[FirestoreItemKey.userId] as String,
      detailsFetchedOn: data?[FirestoreItemKey.detailsFetchedOn] as int?,
      userReference:
          data?[FirestoreItemKey.userReference] as DocumentReference?,
      contactId: snapshot.id,
      size: (data?.getDocumentSize() ?? 0).toDouble(),
    );
  }

  ContactsAvailableDetails updateDocumentReference(
    DocumentReference userReference,
  ) =>
      ContactsAvailableDetails(
        userId: userId,
        userReference: userReference,
        detailsFetchedOn: detailsFetchedOn,
        userDeviceDetails: userDeviceDetails,
        contactId: contactId,
      );

  final DocumentReference? userReference;
  final int? detailsFetchedOn;
  final UserDeviceDetails? userDeviceDetails;
  final String contactId;
  final String userId;
  final double size;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        if (userReference != null)
          FirestoreItemKey.userReference: userReference,
        if (detailsFetchedOn != null)
          FirestoreItemKey.detailsFetchedOn: detailsFetchedOn,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
        FirestoreItemKey.userId: userId,
      };

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
