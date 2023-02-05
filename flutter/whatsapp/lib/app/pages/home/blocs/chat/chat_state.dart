import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class ChatState extends Equatable {
  const ChatState({
    this.directMessageListWithUserDetails = const [],
    this.pageState = PageState.idle,
  });

  ChatState copyWith({
    PageState? pageState,
    List<DirectMessagesListUserDetails>? directMessageListWithUserDetails,
  }) =>
      ChatState(
        pageState: pageState ?? this.pageState,
        directMessageListWithUserDetails: directMessageListWithUserDetails ??
            this.directMessageListWithUserDetails,
      );

  final List<DirectMessagesListUserDetails> directMessageListWithUserDetails;
  final PageState pageState;

  @override
  List<Object?> get props => [
        pageState,
        directMessageListWithUserDetails,
      ];
}
