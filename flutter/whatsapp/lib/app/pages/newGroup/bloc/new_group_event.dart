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
