import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

class ProfileState extends Equatable {
  const ProfileState({
    this.imageUploadStatus = ImageUploadStatus.none,
    this.pageState = PageState.idle,
    this.enableUsernameEdit = false,
  });

  ProfileState copyWith({
    ImageUploadStatus? imageUploadStatus,
    PageState? pageState,
    bool? enableUsernameEdit,
  }) =>
      ProfileState(
        imageUploadStatus: imageUploadStatus ?? this.imageUploadStatus,
        pageState: pageState ?? this.pageState,
        enableUsernameEdit: enableUsernameEdit ?? this.enableUsernameEdit,
      );

  final ImageUploadStatus imageUploadStatus;
  final PageState pageState;
  final bool enableUsernameEdit;

  @override
  List<Object?> get props => [
        imageUploadStatus,
        pageState,
        enableUsernameEdit,
      ];
}
