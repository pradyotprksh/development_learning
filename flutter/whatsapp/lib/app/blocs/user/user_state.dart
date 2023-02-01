import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class UserState extends Equatable {
  const UserState({
    this.userDetails,
    this.imageUploadStatus = ImageUploadStatus.none,
    this.pageState = PageState.idle,
  });

  UserState copyWith({
    UserDetails? userDetails,
    ImageUploadStatus? imageUploadStatus,
    PageState? pageState,
  }) =>
      UserState(
        userDetails: userDetails ?? this.userDetails,
        imageUploadStatus: imageUploadStatus ?? this.imageUploadStatus,
        pageState: pageState ?? this.pageState,
      );

  final UserDetails? userDetails;
  final ImageUploadStatus imageUploadStatus;
  final PageState pageState;

  @override
  List<Object?> get props => [userDetails];
}

class FetchingUserDetails extends UserState {
  const FetchingUserDetails() : super(userDetails: null);
}

class UserDataNotAvailable extends UserState {
  const UserDataNotAvailable() : super(userDetails: null);
}

class UserDetailsAvailable extends UserState {
  const UserDetailsAvailable(UserDetails userDetails)
      : super(
          userDetails: userDetails,
        );
}
