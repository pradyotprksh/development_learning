import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/domain/domain.dart';

class UsersGroupMessageDetails extends Equatable {
  const UsersGroupMessageDetails(this.usersDetails, this.groupMessageDetails);

  final Stream<List<UserDetails>> usersDetails;
  final GroupMessageDetails? groupMessageDetails;

  @override
  List<Object?> get props => [usersDetails, groupMessageDetails];
}
