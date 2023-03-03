import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageDetailsState extends Equatable {
  const MessageDetailsState({
    this.pageState = PageState.idle,
    this.groupMessageDetails,
    this.directMessageDetails,
    this.usersDetails = const [],
    this.attachments = const [],
  });

  MessageDetailsState copyWith({
    PageState? pageState,
    UsersGroupMessageDetails? groupMessageDetails,
    DirectMessageDetails? directMessageDetails,
    List<UserDetails>? usersDetails,
    List<FileInformationDetails>? attachments,
  }) =>
      MessageDetailsState(
        pageState: pageState ?? this.pageState,
        groupMessageDetails: groupMessageDetails ?? this.groupMessageDetails,
        directMessageDetails: directMessageDetails ?? this.directMessageDetails,
        usersDetails: usersDetails ?? this.usersDetails,
        attachments: attachments ?? this.attachments,
      );

  final PageState pageState;
  final UsersGroupMessageDetails? groupMessageDetails;
  final DirectMessageDetails? directMessageDetails;
  final List<UserDetails> usersDetails;
  final List<FileInformationDetails> attachments;

  @override
  List<Object?> get props => [
        pageState,
        groupMessageDetails,
        directMessageDetails,
        usersDetails,
        attachments,
      ];
}
