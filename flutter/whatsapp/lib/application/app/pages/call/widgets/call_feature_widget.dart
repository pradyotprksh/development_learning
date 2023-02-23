import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';

class CallFeatureWidget extends StatelessWidget {
  const CallFeatureWidget({super.key});

  @override
  Widget build(BuildContext context) => Card(
        child: Padding(
          padding: ThemeEdgeInsets.all10,
          child: BlocBuilder<CallBloc, CallState>(
            builder: (_, phoneCallState) => Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                IconButton(
                  onPressed: () {
                    context.read<CallBloc>().add(
                          const ToggleSpeakerEvent(),
                        );
                  },
                  icon: Icon(
                    phoneCallState.speakerState == SpeakerState.notOnSpeaker
                        ? Icons.volume_up
                        : Icons.volume_off,
                    size: 40,
                  ),
                  padding: ThemeEdgeInsets.all15,
                ),
                IconButton(
                  onPressed: () {
                    context.read<CallBloc>().add(
                          const ToggleMuteEvent(),
                        );
                  },
                  icon: Icon(
                    phoneCallState.muteState == MuteState.mute
                        ? Icons.mic_off
                        : Icons.mic,
                    size: 40,
                  ),
                  padding: ThemeEdgeInsets.all15,
                ),
                IconButton(
                  onPressed: () {
                    context.read<CallBloc>().add(
                          const CallEndedEvent(),
                        );
                    context.navigator.pop();
                  },
                  color: Colors.red,
                  icon: const Icon(
                    Icons.call_end,
                    size: 40,
                  ),
                  padding: ThemeEdgeInsets.all15,
                ),
              ],
            ),
          ),
        ),
      );
}
