import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class ChatState extends Equatable {
  const ChatState({
    this.messageListWithUserDetails = const [],
    this.pageState = PageState.idle,
  });

  ChatState copyWith({
    PageState? pageState,
    List<MessagesListUserDetails>? messageListWithUserDetails,
  }) =>
      ChatState(
        pageState: pageState ?? this.pageState,
        messageListWithUserDetails:
            messageListWithUserDetails ?? this.messageListWithUserDetails,
      );

  final List<MessagesListUserDetails> messageListWithUserDetails;
  final PageState pageState;

  @override
  List<Object?> get props => [
        pageState,
        messageListWithUserDetails,
      ];
}
