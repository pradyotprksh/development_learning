import 'package:equatable/equatable.dart';

class MessageRouteDetails extends Equatable {
  const MessageRouteDetails({
    this.directMessageId,
    this.groupId,
  });

  final String? directMessageId;
  final String? groupId;

  bool get isDirectMessage => directMessageId != null;

  bool get isGroup => groupId != null;

  @override
  List<Object?> get props => [directMessageId, groupId];
}
