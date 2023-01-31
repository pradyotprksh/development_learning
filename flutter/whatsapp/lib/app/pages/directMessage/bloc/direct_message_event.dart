import 'package:equatable/equatable.dart';
import 'package:replay_bloc/replay_bloc.dart';

abstract class DirectMessageEvent extends ReplayEvent with EquatableMixin {
  const DirectMessageEvent();

  @override
  List<Object?> get props => [];
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
