import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class NewGroupState extends Equatable {
  const NewGroupState({
    this.pageState = PageState.idle,
    this.existingAccount = const [],
    this.selectedUserDetails = const [],
  });

  final PageState pageState;
  final List<UserContactsAvailableDetails> existingAccount;
  final List<UserDetails> selectedUserDetails;

  NewGroupState copyWith({
    PageState? pageState,
    List<UserContactsAvailableDetails>? existingAccount,
    List<UserDetails>? selectedUserDetails,
  }) =>
      NewGroupState(
        pageState: pageState ?? this.pageState,
        existingAccount: existingAccount ?? this.existingAccount,
        selectedUserDetails: selectedUserDetails ?? this.selectedUserDetails,
      );

  @override
  List<Object?> get props => [
        pageState,
        existingAccount,
        selectedUserDetails,
      ];
}
