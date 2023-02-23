import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/domain/domain.dart';

class ChatState extends Equatable {
  const ChatState({
    this.directMessageListWithUserDetails = const [],
    this.groupMessages = const [],
    this.pageState = PageState.idle,
  });

  ChatState copyWith({
    PageState? pageState,
    List<DirectMessagesListUserDetails>? directMessageListWithUserDetails,
    List<GroupMessageDetails>? groupMessages,
  }) =>
      ChatState(
        pageState: pageState ?? this.pageState,
        directMessageListWithUserDetails: directMessageListWithUserDetails ??
            this.directMessageListWithUserDetails,
        groupMessages: groupMessages ?? this.groupMessages,
      );

  final List<DirectMessagesListUserDetails> directMessageListWithUserDetails;
  final List<GroupMessageDetails> groupMessages;
  final PageState pageState;

  @override
  List<Object?> get props => [
        pageState,
        directMessageListWithUserDetails,
        groupMessages,
      ];
}
