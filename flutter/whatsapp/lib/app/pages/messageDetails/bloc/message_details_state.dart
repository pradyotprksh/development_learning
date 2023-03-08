import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageDetailsState extends Equatable {
  const MessageDetailsState({
    this.pageState = PageState.idle,
    this.groupMessageDetails,
    this.directMessageDetails,
    this.usersDetails = const [],
    this.media = const [],
  });

  MessageDetailsState copyWith({
    PageState? pageState,
    UsersGroupMessageDetails? groupMessageDetails,
    DirectMessageDetails? directMessageDetails,
    List<UserDetails>? usersDetails,
    List<SingleMessageDetails>? media,
  }) =>
      MessageDetailsState(
        pageState: pageState ?? this.pageState,
        groupMessageDetails: groupMessageDetails ?? this.groupMessageDetails,
        directMessageDetails: directMessageDetails ?? this.directMessageDetails,
        usersDetails: usersDetails ?? this.usersDetails,
        media: media ?? this.media,
      );

  final PageState pageState;
  final UsersGroupMessageDetails? groupMessageDetails;
  final DirectMessageDetails? directMessageDetails;
  final List<UserDetails> usersDetails;
  final List<SingleMessageDetails> media;

  @override
  List<Object?> get props => [
        pageState,
        groupMessageDetails,
        directMessageDetails,
        usersDetails,
        media,
      ];
}
