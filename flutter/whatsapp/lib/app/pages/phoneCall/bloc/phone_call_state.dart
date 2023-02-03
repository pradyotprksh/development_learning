import 'package:equatable/equatable.dart';

enum CallState {
  idle,
  ringing,
  ongoing,
}

enum SpeakerState {
  onSpeaker,
  notOnSpeaker,
}

enum MuteState {
  mute,
  unMute,
}

class PhoneCallState extends Equatable {
  const PhoneCallState({
    this.callState = CallState.idle,
    this.speakerState = SpeakerState.notOnSpeaker,
    this.muteState = MuteState.unMute,
  });

  PhoneCallState copyWith({
    CallState? callState,
    SpeakerState? speakerState,
    MuteState? muteState,
  }) =>
      PhoneCallState(
        callState: callState ?? this.callState,
        speakerState: speakerState ?? this.speakerState,
        muteState: muteState ?? this.muteState,
      );

  final CallState callState;
  final SpeakerState speakerState;
  final MuteState muteState;

  @override
  List<Object?> get props => [
        callState,
        speakerState,
        muteState,
      ];
}
