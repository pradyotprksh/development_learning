import 'package:equatable/equatable.dart';

abstract class SavedMessagesEvent extends Equatable {
  const SavedMessagesEvent();

  @override
  List<Object?> get props => [];
}

class FetchSavedMessages extends SavedMessagesEvent {
  const FetchSavedMessages();
}
