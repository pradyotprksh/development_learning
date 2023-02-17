import 'package:equatable/equatable.dart';

abstract class GroupMessageEvent extends Equatable {
  const GroupMessageEvent();

  @override
  List<Object?> get props => [];
}

class FetchGroupDetails extends GroupMessageEvent {
  const FetchGroupDetails(this.groupId);

  final String groupId;

  @override
  List<Object?> get props => [groupId];
}

class ToggleGroupEmojisOption extends GroupMessageEvent {
  const ToggleGroupEmojisOption({
    this.shouldShow,
  });

  final bool? shouldShow;
}

class GetGroupAllMessages extends GroupMessageEvent {
  const GetGroupAllMessages(this.messageId);

  final String messageId;
}

class AddGroupMessage extends GroupMessageEvent {
  const AddGroupMessage(this.message);

  final String message;
}

class UpdateUsersDetails extends GroupMessageEvent {
  const UpdateUsersDetails();
}

class SaveGroupMessageEvent extends GroupMessageEvent {
  const SaveGroupMessageEvent({
    required this.messageId,
    required this.sentByUserId,
    this.directMessageId,
    this.groupId,
  });

  final String messageId;
  final String sentByUserId;
  final String? directMessageId;
  final String? groupId;
}
