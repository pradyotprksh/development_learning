import 'package:equatable/equatable.dart';
import 'package:get/get_rx/get_rx.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class UserState extends Equatable {
  const UserState(
    this.userDetails,
  );

  final Rx<UserDetails?>? userDetails;

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
  const UserDetailsAvailable(Rx<UserDetails?> super.userDetails);
}
