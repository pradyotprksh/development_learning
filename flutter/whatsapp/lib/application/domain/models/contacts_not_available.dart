import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/domain/domain.dart';

class ContactsNotAvailableDetails extends Equatable {
  const ContactsNotAvailableDetails({
    this.displayName,
    this.phoneNumber,
    this.emailId,
    this.detailsFetchedOn,
    this.userDeviceDetails,
    this.contactId = '',
    this.size = 0,
  });

  factory ContactsNotAvailableDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    final deviceDetails =
        data?[FirestoreItemKey.userDeviceDetails] as Map<String, dynamic>? ??
            <String, dynamic>{};

    return ContactsNotAvailableDetails(
      displayName: data?[FirestoreItemKey.displayName] as String?,
      phoneNumber: data?[FirestoreItemKey.phoneNumber] as String?,
      emailId: data?[FirestoreItemKey.emailId] as String?,
      detailsFetchedOn: data?[FirestoreItemKey.detailsFetchedOn] as int?,
      userDeviceDetails: UserDeviceDetails.fromMap(deviceDetails),
      contactId: snapshot.id,
      size: (data?.getDocumentSize() ?? 0).toDouble(),
    );
  }

  bool shouldAdd() => phoneNumber != null || emailId != null;

  String getDocId() => '$displayName|$phoneNumber|$emailId';

  final String? displayName;
  final String? phoneNumber;
  final String? emailId;
  final int? detailsFetchedOn;
  final UserDeviceDetails? userDeviceDetails;
  final String contactId;
  final double size;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        if (displayName != null) FirestoreItemKey.displayName: displayName,
        if (phoneNumber != null) FirestoreItemKey.phoneNumber: phoneNumber,
        if (emailId != null) FirestoreItemKey.emailId: emailId,
        if (detailsFetchedOn != null)
          FirestoreItemKey.detailsFetchedOn: detailsFetchedOn,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
      };

  @override
  List<Object?> get props => [
        displayName,
        phoneNumber,
        emailId,
        detailsFetchedOn,
        userDeviceDetails,
        contactId,
      ];

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
