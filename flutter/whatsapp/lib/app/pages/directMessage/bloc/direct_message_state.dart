import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class DirectMessageState extends Equatable {
  const DirectMessageState({
    this.userDetails,
    this.directMessageDetails,
    this.pageState = PageState.idle,
    this.isEmojiOptionVisible = false,
    this.messages = const [],
  });

  DirectMessageState copyWith({
    UserDetails? userDetails,
    PageState? pageState,
    DirectMessageDetails? directMessageDetails,
    bool? isEmojiOptionVisible,
    List<SingleMessageDetails>? messages,
  }) =>
      DirectMessageState(
        userDetails: userDetails ?? this.userDetails,
        pageState: pageState ?? this.pageState,
        directMessageDetails: directMessageDetails ?? this.directMessageDetails,
        isEmojiOptionVisible: isEmojiOptionVisible ?? this.isEmojiOptionVisible,
        messages: messages ?? this.messages,
      );

  final UserDetails? userDetails;
  final DirectMessageDetails? directMessageDetails;
  final PageState pageState;
  final bool isEmojiOptionVisible;
  final List<SingleMessageDetails> messages;

  @override
  List<Object?> get props => [
        userDetails,
        pageState,
        directMessageDetails,
        isEmojiOptionVisible,
        messages,
      ];
}
