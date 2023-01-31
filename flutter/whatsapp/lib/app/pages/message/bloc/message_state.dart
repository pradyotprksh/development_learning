import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageState extends Equatable {
  const MessageState({
    this.userDetails,
    this.pageState = PageState.idle,
  });

  MessageState copyWith({
    UserDetails? userDetails,
    PageState? pageState,
  }) =>
      MessageState(
        userDetails: userDetails ?? this.userDetails,
        pageState: pageState ?? this.pageState,
      );

  final UserDetails? userDetails;
  final PageState pageState;

  @override
  List<Object?> get props => [
        userDetails,
        pageState,
      ];
}
