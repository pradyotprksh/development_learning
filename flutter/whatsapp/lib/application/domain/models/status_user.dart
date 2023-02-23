import 'dart:async';

import 'package:whatsapp/application/domain/domain.dart';

class UserWithSingleStatusDetails {
  UserWithSingleStatusDetails(
    this.userId,
    this.statusDetails,
    this.userDetails,
  );

  final String userId;
  final List<StatusDetails> statusDetails;
  final Stream<UserDetails?> userDetails;
}
