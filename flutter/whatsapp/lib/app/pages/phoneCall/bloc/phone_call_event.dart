import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class PhoneCallEvent extends Equatable {
  const PhoneCallEvent();

  @override
  List<Object?> get props => [];
}

class CallStartedEvent extends PhoneCallEvent {
  const CallStartedEvent(this.userDetails);

  final List<UserDetails?> userDetails;
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
