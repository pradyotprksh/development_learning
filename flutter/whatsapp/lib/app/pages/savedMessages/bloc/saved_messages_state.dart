import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class SavedMessagesState extends Equatable {
  const SavedMessagesState({
    this.pageState = PageState.idle,
    this.messages = const [],
  });

  SavedMessagesState copyWith({
    PageState? pageState,
    List<MessageUserSavedDetails?>? messages,
  }) =>
      SavedMessagesState(
        pageState: pageState ?? this.pageState,
        messages: messages ?? this.messages,
      );

  final PageState pageState;
  final List<MessageUserSavedDetails?> messages;

  @override
  List<Object?> get props => [
        pageState,
        messages,
      ];
}
