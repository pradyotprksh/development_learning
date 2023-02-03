import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class PhoneCallBloc extends Bloc<PhoneCallEvent, PhoneCallState> {
  PhoneCallBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._deviceDetails,
  ) : super(const PhoneCallState()) {
    on<CallStartedEvent>(_callStartedEvent);
    on<CallEndedEvent>(_callEndedEvent);
    on<ToggleSpeakerEvent>(_callSpeakerEvent);
    on<ToggleMuteEvent>(_callMuteEvent);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final DeviceDetails _deviceDetails;

  void _callStartedEvent(
    CallStartedEvent event,
    Emitter<PhoneCallState> emit,
  ) {
    emit(state.copyWith(callState: CallState.ongoing));
  }

  void _callEndedEvent(
    CallEndedEvent event,
    Emitter<PhoneCallState> emit,
  ) {
    emit(state.copyWith(callState: CallState.idle));
  }

  void _callSpeakerEvent(
    ToggleSpeakerEvent event,
    Emitter<PhoneCallState> emit,
  ) {
    emit(
      state.copyWith(
        speakerState: state.speakerState == SpeakerState.notOnSpeaker
            ? SpeakerState.onSpeaker
            : SpeakerState.notOnSpeaker,
      ),
    );
  }

  void _callMuteEvent(
    ToggleMuteEvent event,
    Emitter<PhoneCallState> emit,
  ) {
    emit(
      state.copyWith(
        muteState: state.muteState == MuteState.unMute
            ? MuteState.mute
            : MuteState.unMute,
      ),
    );
  }
}
