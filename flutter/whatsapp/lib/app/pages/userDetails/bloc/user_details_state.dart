import 'package:equatable/equatable.dart';

enum ImageUploadStatus {
  uploading,
  uploaded,
  error,
  none,
}

class UserDetailsState extends Equatable {
  const UserDetailsState({
    this.username = '',
    this.emailAddress = '',
    this.phoneNumber = '',
    this.profilePicImage = '',
    this.isPhoneNumberAvailable = true,
    this.isEmailAddressAvailable = true,
    this.imageUploadStatus = ImageUploadStatus.none,
  });

  UserDetailsState copyWith({
    String? username,
    String? emailAddress,
    String? phoneNumber,
    String? profilePicImage,
    bool? isPhoneNumberAvailable,
    bool? isEmailAddressAvailable,
    ImageUploadStatus? imageUploadStatus,
  }) =>
      UserDetailsState(
        username: username ?? this.username,
        emailAddress: emailAddress ?? this.emailAddress,
        phoneNumber: phoneNumber ?? this.phoneNumber,
        profilePicImage: profilePicImage ?? this.profilePicImage,
        isPhoneNumberAvailable:
            isPhoneNumberAvailable ?? this.isPhoneNumberAvailable,
        isEmailAddressAvailable:
            isEmailAddressAvailable ?? this.isEmailAddressAvailable,
        imageUploadStatus: imageUploadStatus ?? this.imageUploadStatus,
      );

  final String username;
  final String emailAddress;
  final String phoneNumber;
  final String profilePicImage;
  final bool isPhoneNumberAvailable;
  final bool isEmailAddressAvailable;
  final ImageUploadStatus imageUploadStatus;

  @override
  List<Object?> get props => [
        username,
        emailAddress,
        phoneNumber,
        profilePicImage,
        isPhoneNumberAvailable,
        isEmailAddressAvailable,
        imageUploadStatus,
      ];
}
