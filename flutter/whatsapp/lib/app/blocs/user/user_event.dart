import 'package:equatable/equatable.dart';

abstract class UserEvent extends Equatable {
  const UserEvent();

  @override
  List<Object?> get props => [];
}

class FetchUserDetails extends UserEvent {
  const FetchUserDetails();
}

class SaveAvatar extends UserEvent {
  const SaveAvatar(this.avatarDetails);

  final Map<String, dynamic> avatarDetails;
}
