import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

class ScreenshotDetails {
  ScreenshotDetails({
    required this.userId,
    this.createdOnTimeStamp,
    this.userDeviceDetails,
    this.route,
    this.arguments,
  });

  factory ScreenshotDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? _,
  ) {
    final data = snapshot.data();

    return ScreenshotDetails(
      userId: data?[FirestoreItemKey.userId] as String,
      route: data?[FirestoreItemKey.route] as String?,
      arguments: data?[FirestoreItemKey.arguments] as String?,
      createdOnTimeStamp:
          data?[FirestoreItemKey.createdOnTimeStamp] as int? ?? 0,
    );
  }

  final String userId;
  final int? createdOnTimeStamp;
  final UserDeviceDetails? userDeviceDetails;
  final String? route;
  final Object? arguments;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        FirestoreItemKey.userId: userId,
        if (createdOnTimeStamp != null)
          FirestoreItemKey.createdOnTimeStamp: createdOnTimeStamp,
        if (userDeviceDetails != null)
          FirestoreItemKey.userDeviceDetails: userDeviceDetails!.toMap(),
        if (route != null) FirestoreItemKey.route: route,
        if (arguments != null) FirestoreItemKey.arguments: arguments.toString(),
      };

  double get calculateSize => toFirestore().getDocumentSize().toDouble();
}
