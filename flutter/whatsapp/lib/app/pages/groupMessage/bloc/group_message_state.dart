import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class GroupMessageState extends Equatable {
  const GroupMessageState({
    this.groupMessageDetails,
    this.isEmojiOptionVisible = false,
    this.messages = const [],
  });

  GroupMessageState copyWith({
    UsersGroupMessageDetails? groupMessageDetails,
    bool? isEmojiOptionVisible,
    List<SingleMessageDetails>? messages,
  }) =>
      GroupMessageState(
        groupMessageDetails: groupMessageDetails ?? this.groupMessageDetails,
        isEmojiOptionVisible: isEmojiOptionVisible ?? this.isEmojiOptionVisible,
        messages: messages ?? this.messages,
      );

  final UsersGroupMessageDetails? groupMessageDetails;
  final bool isEmojiOptionVisible;
  final List<SingleMessageDetails> messages;

  @override
  List<Object?> get props => [
        groupMessageDetails,
        isEmojiOptionVisible,
        messages,
      ];
}
