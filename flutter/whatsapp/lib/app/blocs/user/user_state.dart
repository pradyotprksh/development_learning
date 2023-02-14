import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class UserState extends Equatable {
  const UserState({
    required this.userDetails,
  });

  final UserDetails? userDetails;

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

class AskForPinConfirmation extends UserState {
  const AskForPinConfirmation(UserDetails userDetails)
      : super(
          userDetails: userDetails,
        );
}
