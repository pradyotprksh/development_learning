import 'package:equatable/equatable.dart';

abstract class ContactUsEvent extends Equatable {
  const ContactUsEvent();

  @override
  List<Object?> get props => [];
}

class ToggleDeviceInformation extends ContactUsEvent {
  const ToggleDeviceInformation();
}

class CreateIssue extends ContactUsEvent {
  const CreateIssue(this.description);

  final String description;
}
