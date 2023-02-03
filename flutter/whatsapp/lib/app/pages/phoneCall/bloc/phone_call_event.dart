import 'package:equatable/equatable.dart';

abstract class PhoneCallEvent extends Equatable {
  const PhoneCallEvent();

  @override
  List<Object?> get props => [];
}

class CallStartedEvent extends PhoneCallEvent {
  const CallStartedEvent(this.userId);

  final String userId;
}

class CallEndedEvent extends PhoneCallEvent {
  const CallEndedEvent(this.userId);

  final String userId;
}

class ToggleSpeakerEvent extends PhoneCallEvent {
  const ToggleSpeakerEvent();
}

class ToggleMuteEvent extends PhoneCallEvent {
  const ToggleMuteEvent();
}
