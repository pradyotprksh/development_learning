import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class GroupMessageState extends Equatable {
  const GroupMessageState({
    this.groupMessageDetails,
  });

  GroupMessageState copyWith({
    GroupMessageDetails? groupMessageDetails,
  }) =>
      GroupMessageState(
        groupMessageDetails: groupMessageDetails ?? this.groupMessageDetails,
      );

  final GroupMessageDetails? groupMessageDetails;

  @override
  List<Object?> get props => [
        groupMessageDetails,
      ];
}
