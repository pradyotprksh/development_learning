import 'dart:async';

import 'package:whatsapp/domain/domain.dart';

class UserWithSingleStatusDetails {
  UserWithSingleStatusDetails(
    this.userId,
    this.statusDetails,
    this.userDetails,
  );

  final String userId;
  final List<StatusDetails> statusDetails;
  final StreamController<UserDetails?> userDetails;
}
