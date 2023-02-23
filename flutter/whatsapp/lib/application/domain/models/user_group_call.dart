import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/domain/domain.dart';

class UserGroupCallDetails extends Equatable {
  const UserGroupCallDetails(
    this.callDetails,
    this.groupMessageDetails,
    this.userDetails,
  );

  final CallDetails callDetails;
  final Stream<UsersGroupMessageDetails?>? groupMessageDetails;
  final Stream<List<UserDetails>?>? userDetails;

  @override
  List<Object?> get props => [
        callDetails,
        groupMessageDetails,
        userDetails,
      ];
}
