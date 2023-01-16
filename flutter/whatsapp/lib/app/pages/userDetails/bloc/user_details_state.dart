import 'package:equatable/equatable.dart';

enum ImageUploadStatus {
  uploading,
  uploaded,
  error,
  none,
}

enum PageState {
  loading,
  error,
  idle,
  success,
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
    this.pageState = PageState.idle,
  });

  UserDetailsState copyWith({
    String? username,
    String? emailAddress,
    String? phoneNumber,
    String? profilePicImage,
    bool? isPhoneNumberAvailable,
    bool? isEmailAddressAvailable,
    ImageUploadStatus? imageUploadStatus,
    PageState? pageState,
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
        pageState: pageState ?? this.pageState,
      );

  final String username;
  final String emailAddress;
  final String phoneNumber;
  final String profilePicImage;
  final bool isPhoneNumberAvailable;
  final bool isEmailAddressAvailable;
  final ImageUploadStatus imageUploadStatus;
  final PageState pageState;

  @override
  List<Object?> get props => [
        username,
        emailAddress,
        phoneNumber,
        profilePicImage,
        isPhoneNumberAvailable,
        isEmailAddressAvailable,
        imageUploadStatus,
        pageState,
      ];
}
