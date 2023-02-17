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
    this.attachments = const [],
  });

  DirectMessageState copyWith({
    UserDetails? userDetails,
    PageState? pageState,
    DirectMessageDetails? directMessageDetails,
    bool? isEmojiOptionVisible,
    List<SingleMessageDetails>? messages,
    List<FileInformation>? attachments,
  }) =>
      DirectMessageState(
        userDetails: userDetails ?? this.userDetails,
        pageState: pageState ?? this.pageState,
        directMessageDetails: directMessageDetails ?? this.directMessageDetails,
        isEmojiOptionVisible: isEmojiOptionVisible ?? this.isEmojiOptionVisible,
        messages: messages ?? this.messages,
        attachments: attachments ?? this.attachments,
      );

  final UserDetails? userDetails;
  final DirectMessageDetails? directMessageDetails;
  final PageState pageState;
  final bool isEmojiOptionVisible;
  final List<SingleMessageDetails> messages;
  final List<FileInformation> attachments;

  @override
  List<Object?> get props => [
        userDetails,
        pageState,
        directMessageDetails,
        isEmojiOptionVisible,
        messages,
        attachments,
      ];
}
