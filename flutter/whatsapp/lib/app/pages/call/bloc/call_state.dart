import 'package:equatable/equatable.dart';

enum CurrentCallState {
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

class CallState extends Equatable {
  const CallState({
    this.callState = CurrentCallState.idle,
    this.speakerState = SpeakerState.notOnSpeaker,
    this.muteState = MuteState.unMute,
    this.documentIds = const [],
  });

  CallState copyWith({
    CurrentCallState? callState,
    SpeakerState? speakerState,
    MuteState? muteState,
    List<String>? documentIds,
  }) =>
      CallState(
        callState: callState ?? this.callState,
        speakerState: speakerState ?? this.speakerState,
        muteState: muteState ?? this.muteState,
        documentIds: documentIds ?? this.documentIds,
      );

  final CurrentCallState callState;
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
