import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class UserState extends Equatable {
  const UserState(
    this.userDetails,
  );

  final UserDetails? userDetails;

  @override
  List<Object?> get props => [userDetails];
}

class FetchingUserDetails extends UserState {
  const FetchingUserDetails() : super(null);
}

class UserDataNotAvailable extends UserState {
  const UserDataNotAvailable() : super(null);
}

class UserDetailsAvailable extends UserState {
  const UserDetailsAvailable(super.userDetails);
}
