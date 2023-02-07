import 'package:equatable/equatable.dart';

abstract class NewGroupEvent extends Equatable {
  const NewGroupEvent();

  @override
  List<Object?> get props => [];
}

class FetchAccounts extends NewGroupEvent {
  const FetchAccounts();
}

class ToggleUserSelection extends NewGroupEvent {
  const ToggleUserSelection(this.userId);

  final String userId;
}

class CreateGroupEvent extends NewGroupEvent {
  const CreateGroupEvent(
    this.groupName,
    this.groupImagePath,
    this.firstMessage,
  );

  final String groupName;
  final String groupImagePath;
  final String firstMessage;
}
