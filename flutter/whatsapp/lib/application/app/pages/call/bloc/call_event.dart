import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/app/app.dart';

abstract class CallEvent extends Equatable {
  const CallEvent();

  @override
  List<Object?> get props => [];
}

class CallStartedEvent extends CallEvent {
  const CallStartedEvent(this.callDetailsArguments);

  final CallDetailsArguments callDetailsArguments;
}

class CallEndedEvent extends CallEvent {
  const CallEndedEvent();
}

class ToggleSpeakerEvent extends CallEvent {
  const ToggleSpeakerEvent();
}

class ToggleMuteEvent extends CallEvent {
  const ToggleMuteEvent();
}
