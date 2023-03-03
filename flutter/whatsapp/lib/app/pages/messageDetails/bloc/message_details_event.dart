import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

abstract class MessageDetailsEvent extends Equatable {
  const MessageDetailsEvent();

  @override
  List<Object?> get props => [];
}

class FetchDetails extends MessageDetailsEvent {
  const FetchDetails(this.messageRouteDetails);

  final MessageRouteDetails messageRouteDetails;
}

class GetDirectMessageDetails extends MessageDetailsEvent {
  const GetDirectMessageDetails(this.directMessageId);

  final String directMessageId;
}

class GetGroupMessageDetails extends MessageDetailsEvent {
  const GetGroupMessageDetails(this.groupMessageId);

  final String groupMessageId;
}

class FetchUsersDetails extends MessageDetailsEvent {
  const FetchUsersDetails();
}

class GetGroupMessageAttachments extends MessageDetailsEvent {
  const GetGroupMessageAttachments(this.groupMessageId);

  final String groupMessageId;
}

class GetDirectMessageAttachments extends MessageDetailsEvent {
  const GetDirectMessageAttachments(this.directMessageId);

  final String directMessageId;
}
