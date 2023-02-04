import 'package:equatable/equatable.dart';

enum CallState {
  idle,
  ringing,
  ongoing,
  missed,
  canceled,
  busy,
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
    this.documentIds = const [],
  });

  PhoneCallState copyWith({
    CallState? callState,
    SpeakerState? speakerState,
    MuteState? muteState,
    List<String>? documentIds,
  }) =>
      PhoneCallState(
        callState: callState ?? this.callState,
        speakerState: speakerState ?? this.speakerState,
        muteState: muteState ?? this.muteState,
        documentIds: documentIds ?? this.documentIds,
      );

  final CallState callState;
  final SpeakerState speakerState;
  final MuteState muteState;
  final List<String> documentIds;

  @override
  List<Object?> get props => [
        callState,
        speakerState,
        muteState,
      ];
}
