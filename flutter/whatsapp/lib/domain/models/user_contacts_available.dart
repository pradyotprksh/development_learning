import 'package:whatsapp/domain/domain.dart';

class UserContactsAvailableDetails {
  UserContactsAvailableDetails(this.userDetails, this.contactsAvailableDetails);

  final UserDetails? userDetails;
  final ContactsAvailableDetails contactsAvailableDetails;
}
