import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageState extends Equatable {
  const MessageState({
    this.userDetails,
    this.directMessageDetails,
    this.pageState = PageState.idle,
  });

  MessageState copyWith({
    UserDetails? userDetails,
    PageState? pageState,
    DirectMessageDetails? directMessageDetails,
  }) =>
      MessageState(
        userDetails: userDetails ?? this.userDetails,
        pageState: pageState ?? this.pageState,
        directMessageDetails: directMessageDetails ?? this.directMessageDetails,
      );

  final UserDetails? userDetails;
  final DirectMessageDetails? directMessageDetails;
  final PageState pageState;

  @override
  List<Object?> get props => [
        userDetails,
        pageState,
        directMessageDetails,
      ];
}
