import 'package:contacts_service/contacts_service.dart';
import 'package:equatable/equatable.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class SelectContactState extends Equatable {
  const SelectContactState({
    this.permissionStatus,
    this.localContacts = const [],
    this.existingAccount = const [],
    this.nonExistingAccount = const [],
    this.pageState = PageState.idle,
    this.checkForContacts = true,
  });

  SelectContactState copyWith({
    PermissionStatus? permissionStatus,
    List<Contact>? localContacts,
    List<UserDetails>? existingAccount,
    List<Contact>? nonExistingAccount,
    PageState? pageState,
    bool? checkForContacts,
  }) =>
      SelectContactState(
        permissionStatus: permissionStatus ?? this.permissionStatus,
        localContacts: localContacts ?? this.localContacts,
        pageState: pageState ?? this.pageState,
        existingAccount: existingAccount ?? this.existingAccount,
        nonExistingAccount: nonExistingAccount ?? this.nonExistingAccount,
        checkForContacts: checkForContacts ?? this.checkForContacts,
      );

  final PermissionStatus? permissionStatus;
  final List<Contact> localContacts;
  final PageState pageState;
  final List<UserDetails> existingAccount;
  final List<Contact> nonExistingAccount;
  final bool checkForContacts;

  @override
  List<Object?> get props => [
        permissionStatus,
        localContacts,
        pageState,
        existingAccount,
        nonExistingAccount,
      ];
}
