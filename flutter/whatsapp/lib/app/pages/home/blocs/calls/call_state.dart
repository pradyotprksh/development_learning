import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class CallState extends Equatable {
  const CallState({
    this.pageState = PageState.idle,
    this.userGroupCallDetails = const [],
  });

  CallState copyWith({
    PageState? pageState,
    List<UserGroupCallDetails>? userGroupCallDetails,
  }) =>
      CallState(
        pageState: pageState ?? this.pageState,
        userGroupCallDetails: userGroupCallDetails ?? this.userGroupCallDetails,
      );

  final PageState pageState;
  final List<UserGroupCallDetails> userGroupCallDetails;

  @override
  List<Object?> get props => [
        pageState,
        userGroupCallDetails,
      ];
}
