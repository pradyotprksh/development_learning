import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageDetailsState extends Equatable {
  const MessageDetailsState({
    this.pageState = PageState.idle,
    this.groupMessageDetails,
    this.usersDetails = const [],
  });

  MessageDetailsState copyWith({
    PageState? pageState,
    UsersGroupMessageDetails? groupMessageDetails,
    List<UserDetails>? usersDetails,
  }) =>
      MessageDetailsState(
        pageState: pageState ?? this.pageState,
        groupMessageDetails: groupMessageDetails ?? this.groupMessageDetails,
        usersDetails: usersDetails ?? this.usersDetails,
      );

  final PageState pageState;
  final UsersGroupMessageDetails? groupMessageDetails;
  final List<UserDetails> usersDetails;

  @override
  List<Object?> get props => [
        pageState,
        groupMessageDetails,
        usersDetails,
      ];
}
