import 'package:equatable/equatable.dart';

abstract class UserDetailsEvent extends Equatable {
  const UserDetailsEvent();

  @override
  List<Object?> get props => [];
}

class FetchFirebaseUserDetails extends UserDetailsEvent {
  const FetchFirebaseUserDetails();
}

class UserDetailsFormEvent extends UserDetailsEvent {
  const UserDetailsFormEvent({
    this.userName,
    this.emailAddress,
    this.phoneNumber,
  });

  final String? userName;
  final String? emailAddress;
  final String? phoneNumber;

  @override
  List<Object?> get props => [
        userName,
        emailAddress,
        phoneNumber,
      ];
}

class UploadProfileImage extends UserDetailsEvent {
  const UploadProfileImage({
    required this.imagePath,
  });

  final String imagePath;

  @override
  List<Object?> get props => [imagePath];
}
