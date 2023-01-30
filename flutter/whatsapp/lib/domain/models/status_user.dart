import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

class UserWithSingleStatusDetails {
  UserWithSingleStatusDetails(
    this.userId,
    this.statusDetails,
    this.userDetails,
  );

  final String userId;
  final List<StatusDetails> statusDetails;
  final Stream<DocumentSnapshot<UserDetails>> userDetails;
}
