import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:stop_watch_timer/stop_watch_timer.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class PhoneCallView extends StatefulWidget {
  const PhoneCallView({
    super.key,
  });

  @override
  State<PhoneCallView> createState() => _PhoneCallViewState();
}

class _PhoneCallViewState extends State<PhoneCallView> {
  final StopWatchTimer _stopWatchTimer = StopWatchTimer();

  @override
  void dispose() async {
    super.dispose();
    await _stopWatchTimer.dispose(); // Need to call dispose function.
  }

  @override
  Widget build(BuildContext context) {
    final userDetails = context.routeSettings?.arguments as UserDetails?;
    context.read<PhoneCallBloc>().add(
          CallStartedEvent(userDetails?.userId ?? ''),
        );

    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(
        leading: const CloseButton(),
        backgroundColor: Colors.transparent,
        elevation: 0,
        title: BlocBuilder<PhoneCallBloc, PhoneCallState>(
          builder: (_, phoneState) {
            if (phoneState.callState == CallState.ongoing) {
              _stopWatchTimer.onStartTimer();

              return StreamBuilder<int>(
                stream: _stopWatchTimer.rawTime,
                builder: (_, timeData) {
                  final value = timeData.data;
                  final displayTime = StopWatchTimer.getDisplayTime(
                    value ?? 0,
                    milliSecond: false,
                  );

                  return Text(displayTime);
                },
              );
            } else {
              return Text(
                context.translator.ringing,
              );
            }
          },
        ),
      ),
      body: Column(
        mainAxisSize: MainAxisSize.max,
        children: [
          const Spacer(),
          Center(
            child: UserImageWidget(
              profileImage: userDetails?.profileImage ?? '',
              userId: userDetails?.userId ?? '',
              enableAction: false,
              size: context.mediaQuery.size.width * 0.80,
            ),
          ),
          const Spacer(),
          Card(
            child: Padding(
              padding: ThemeEdgeInsets.all10,
              child: BlocBuilder<PhoneCallBloc, PhoneCallState>(
                builder: (_, phoneCallState) => Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    IconButton(
                      onPressed: () {
                        context.read<PhoneCallBloc>().add(
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
                        context.read<PhoneCallBloc>().add(
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
                        context.read<PhoneCallBloc>().add(
                              CallEndedEvent(userDetails?.userId ?? ''),
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
          ),
        ],
      ),
    );
  }
}
