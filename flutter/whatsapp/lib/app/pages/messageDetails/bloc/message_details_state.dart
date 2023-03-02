import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

class MessageDetailsState extends Equatable {
  const MessageDetailsState({
    this.pageState = PageState.idle,
  });

  MessageDetailsState copyWith({
    PageState? pageState,
  }) =>
      MessageDetailsState(
        pageState: pageState ?? this.pageState,
      );

  final PageState pageState;

  @override
  List<Object?> get props => [
        pageState,
      ];
}
