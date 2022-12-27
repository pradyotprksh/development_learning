import 'package:equatable/equatable.dart';

abstract class UserState extends Equatable {
  const UserState();

  @override
  List<Object?> get props => [];
}

class FetchingUserDetails extends UserState {
  const FetchingUserDetails();
}

class UserDataNotAvailable extends UserState {
  const UserDataNotAvailable();
}

class UserDetailsAvailable extends UserState {
  const UserDetailsAvailable();
}
