import 'package:equatable/equatable.dart';

abstract class ProfileEvent extends Equatable {
  const ProfileEvent();

  @override
  List<Object?> get props => [];
}

class UpdateUserProfileImage extends ProfileEvent {
  const UpdateUserProfileImage(this.path);

  final String path;

  @override
  List<Object?> get props => [path];
}

class UpdateUserName extends ProfileEvent {
  const UpdateUserName(this.username);

  final String username;

  @override
  List<Object?> get props => [username];
}

class EnableDisableUserNameForm extends ProfileEvent {
  const EnableDisableUserNameForm();
}

class UpdateCurrentMood extends ProfileEvent {
  const UpdateCurrentMood(this.mood);

  final String mood;
}

class UseAvatarAsProfileImage extends ProfileEvent {
  const UseAvatarAsProfileImage();
}
