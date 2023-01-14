import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';

class UserDetails {
  UserDetails({
    this.name,
    this.emailId,
    this.phoneNumber,
    this.userId,
    this.allDetailsAvailable = false,
  });

  factory UserDetails.fromFirestore(
    DocumentSnapshot<Map<String, dynamic>> snapshot,
    SnapshotOptions? options,
  ) {
    UtilsLogger.debugLog(options.toString());
    final data = snapshot.data();
    return UserDetails(
      name: data?['name'] as String?,
      emailId: data?['emailId'] as String?,
      phoneNumber: data?['phoneNumber'] as String?,
      userId: data?['userId'] as String?,
      allDetailsAvailable: data?['allDetailsAvailable'] as bool? ?? false,
    );
  }

  final String? name;
  final String? emailId;
  final String? phoneNumber;
  final String? userId;
  final bool allDetailsAvailable;

  Map<String, dynamic> toFirestore() => <String, dynamic>{
        if (name != null) 'name': name,
        if (emailId != null) 'emailId': emailId,
        if (phoneNumber != null) 'phoneNumber': phoneNumber,
        if (userId != null) 'userId': userId,
        'allDetailsAvailable': allDetailsAvailable,
      };
}
