import 'package:equatable/equatable.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/domain/domain.dart';

class SelectContactState extends Equatable {
  const SelectContactState({
    this.permissionStatus,
    this.existingAccount = const [],
    this.nonExistingAccount = const [],
    this.pageState = PageState.idle,
    this.checkForContacts = true,
  });

  SelectContactState copyWith({
    PermissionStatus? permissionStatus,
    List<UserContactsAvailableDetails>? existingAccount,
    List<ContactsNotAvailableDetails>? nonExistingAccount,
    PageState? pageState,
    bool? checkForContacts,
  }) =>
      SelectContactState(
        permissionStatus: permissionStatus ?? this.permissionStatus,
        pageState: pageState ?? this.pageState,
        existingAccount: existingAccount ?? this.existingAccount,
        nonExistingAccount: nonExistingAccount ?? this.nonExistingAccount,
        checkForContacts: checkForContacts ?? this.checkForContacts,
      );

  final PermissionStatus? permissionStatus;
  final PageState pageState;
  final List<UserContactsAvailableDetails> existingAccount;
  final List<ContactsNotAvailableDetails> nonExistingAccount;
  final bool checkForContacts;

  @override
  List<Object?> get props => [
        permissionStatus,
        pageState,
        existingAccount,
        nonExistingAccount,
      ];
}
