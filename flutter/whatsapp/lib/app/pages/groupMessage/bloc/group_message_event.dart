import 'package:equatable/equatable.dart';

abstract class GroupMessageEvent extends Equatable {
  @override
  List<Object?> get props => [];
}

class FetchGroupDetails extends GroupMessageEvent {
  FetchGroupDetails(this.groupId);

  final String groupId;

  @override
  List<Object?> get props => [groupId];
}
