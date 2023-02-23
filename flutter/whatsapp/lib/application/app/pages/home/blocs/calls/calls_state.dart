import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/domain/domain.dart';

class CallsState extends Equatable {
  const CallsState({
    this.pageState = PageState.idle,
    this.userGroupCallDetails = const [],
  });

  CallsState copyWith({
    PageState? pageState,
    List<UserGroupCallDetails>? userGroupCallDetails,
  }) =>
      CallsState(
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
