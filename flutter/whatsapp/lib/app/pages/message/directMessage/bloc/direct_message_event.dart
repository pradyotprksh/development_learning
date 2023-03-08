import 'package:equatable/equatable.dart';
import 'package:replay_bloc/replay_bloc.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class DirectMessageEvent extends ReplayEvent with EquatableMixin {
  const DirectMessageEvent();

  @override
  List<Object?> get props => [];
}

class FetchSelectedMessageDetails extends DirectMessageEvent {
  const FetchSelectedMessageDetails(this.messageId);

  final String messageId;
}

class FetchSelectedUserDetails extends DirectMessageEvent {
  const FetchSelectedUserDetails(this.userId);

  final String userId;
}

class GetMessageDetails extends DirectMessageEvent {
  const GetMessageDetails();
}

class GetMessages extends DirectMessageEvent {
  const GetMessages();
}

class CreateDirectMessage extends DirectMessageEvent {
  const CreateDirectMessage(this.firstMessage);

  final String firstMessage;
}

class ToggleEmojisOption extends DirectMessageEvent {
  const ToggleEmojisOption({
    this.shouldShow,
  });

  final bool? shouldShow;
}

class GetAllMessages extends DirectMessageEvent {
  const GetAllMessages(this.messageId);

  final String messageId;
}

class AddMessage extends DirectMessageEvent {
  const AddMessage(this.message);

  final String message;
}

class SaveDirectMessageEvent extends DirectMessageEvent {
  const SaveDirectMessageEvent({
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

class DirectMessageAttachmentSelectedEvent extends DirectMessageEvent {
  const DirectMessageAttachmentSelectedEvent(this.fileInformation);

  final List<FileInformationDetails> fileInformation;
}
