import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/domain/domain.dart';

class UserContactsAvailableDetails extends Equatable {
  const UserContactsAvailableDetails(
    this.userDetails,
    this.contactsAvailableDetails,
    this.isSelected,
  );

  UserContactsAvailableDetails updateSelectedValue() =>
      UserContactsAvailableDetails(
        userDetails,
        contactsAvailableDetails,
        !isSelected,
      );

  final UserDetails? userDetails;
  final ContactsAvailableDetails contactsAvailableDetails;
  final bool isSelected;

  @override
  List<Object?> get props => [
        userDetails,
        contactsAvailableDetails,
        isSelected,
      ];
}
