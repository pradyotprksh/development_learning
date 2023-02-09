import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

abstract class PhoneCallEvent extends Equatable {
  const PhoneCallEvent();

  @override
  List<Object?> get props => [];
}

class CallStartedEvent extends PhoneCallEvent {
  const CallStartedEvent(this.callDetailsArguments);

  final CallDetailsArguments callDetailsArguments;
}

class CallEndedEvent extends PhoneCallEvent {
  const CallEndedEvent();
}

class ToggleSpeakerEvent extends PhoneCallEvent {
  const ToggleSpeakerEvent();
}

class ToggleMuteEvent extends PhoneCallEvent {
  const ToggleMuteEvent();
}
