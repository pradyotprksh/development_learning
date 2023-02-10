import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class CallBloc extends Bloc<CallEvent, CallState> {
  CallBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._deviceDetails,
  ) : super(const CallState()) {
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
    Emitter<CallState> emit,
  ) async {
    emit(state.copyWith(callState: CurrentCallState.ongoing));
    final currentUserId = _firebaseAuthService.getUserId();
    final otherUsersId =
        event.callDetailsArguments.userDetails.map((e) => e?.userId).toList();
    if (currentUserId != null && otherUsersId.isNotEmpty) {
      var usersId = <String>[];
      for (var otherUserId in otherUsersId) {
        if (otherUserId != null) {
          usersId.add(otherUserId);
        }
      }
      usersId.add(currentUserId);
      final deviceDetails = await _deviceDetails.getDeviceDetails();
      await _firebaseFirestoreService.createCall(
        CallDetails(
          startedByUserId: currentUserId,
          usersId: usersId,
          isPhoneCall: event.callDetailsArguments.isPhoneCall,
          isGroupCall: event.callDetailsArguments.isGroupCall,
          isVideoCall: event.callDetailsArguments.isVideoCall,
          groupId: event.callDetailsArguments.groupId,
          createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
          startedByUserDeviceDetails: deviceDetails,
        ),
      );
    }
  }

  void _callEndedEvent(
    CallEndedEvent event,
    Emitter<CallState> emit,
  ) {
    emit(state.copyWith(callState: CurrentCallState.idle));
  }

  void _callSpeakerEvent(
    ToggleSpeakerEvent event,
    Emitter<CallState> emit,
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
    Emitter<CallState> emit,
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
