import 'dart:async';

import 'package:whatsapp/domain/domain.dart';

class UserContactsAvailableDetails {
  UserContactsAvailableDetails(this.userDetails, this.contactsAvailableDetails);

  final StreamController<UserDetails?> userDetails;
  final ContactsAvailableDetails contactsAvailableDetails;
}
