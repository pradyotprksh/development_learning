import 'package:contacts_service/contacts_service.dart';
import 'package:equatable/equatable.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:whatsapp/application/app/app.dart';

abstract class SelectContactEvent extends Equatable {
  const SelectContactEvent();

  @override
  List<Object?> get props => [];
}

class ContactPermissionStatus extends SelectContactEvent {
  const ContactPermissionStatus(this.permissionStatus);

  final PermissionStatus permissionStatus;

  @override
  List<Object?> get props => [permissionStatus];
}

class LocalContactsDetails extends SelectContactEvent {
  const LocalContactsDetails(this.localContacts);

  final List<Contact> localContacts;

  @override
  List<Object?> get props => [localContacts];
}

class UpdatePageStateEvent extends SelectContactEvent {
  const UpdatePageStateEvent({
    this.pageState = PageState.idle,
  });

  final PageState pageState;

  @override
  List<Object?> get props => [pageState];
}

class GetAvailableContacts extends SelectContactEvent {
  const GetAvailableContacts();
}

class GetNotAvailableContacts extends SelectContactEvent {
  const GetNotAvailableContacts();
}

class RefreshContacts extends SelectContactEvent {
  const RefreshContacts(this.localContacts);

  final List<Contact> localContacts;

  @override
  List<Object?> get props => [localContacts];
}
