import 'package:equatable/equatable.dart';

abstract class UserEvent extends Equatable {
  const UserEvent();

  @override
  List<Object?> get props => [];
}

class FetchUserDetails extends UserEvent {
  const FetchUserDetails();
}

class UpdateUserProfileImage extends UserEvent {
  const UpdateUserProfileImage(this.path);

  final String path;

  @override
  List<Object?> get props => [path];
}
