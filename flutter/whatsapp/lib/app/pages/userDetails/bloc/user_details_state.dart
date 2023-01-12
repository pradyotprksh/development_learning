import 'package:equatable/equatable.dart';

class UserDetailsState extends Equatable {
  const UserDetailsState({
    this.username = '',
    this.emailAddress = '',
    this.phoneNumber = '',
    this.isPhoneNumberAvailable = true,
    this.isEmailAddressAvailable = true,
  });

  final String username;
  final String emailAddress;
  final String phoneNumber;
  final bool isPhoneNumberAvailable;
  final bool isEmailAddressAvailable;

  @override
  List<Object?> get props => [
        username,
        emailAddress,
        phoneNumber,
        isPhoneNumberAvailable,
        isEmailAddressAvailable,
      ];
}
