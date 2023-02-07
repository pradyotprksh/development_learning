import 'package:equatable/equatable.dart';

abstract class ChatEvent extends Equatable {
  const ChatEvent();

  @override
  List<Object?> get props => [];
}

class GetDirectMessagesList extends ChatEvent {
  const GetDirectMessagesList();
}

class GetGroupMessagesList extends ChatEvent {
  const GetGroupMessagesList();
}
