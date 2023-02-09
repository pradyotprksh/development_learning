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
    await _stopWatchTimer.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final userDetails =
        context.routeSettings?.arguments as List<UserDetails?>? ?? [];
    context.read<PhoneCallBloc>().add(
          CallStartedEvent(userDetails),
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
      body: Stack(
        children: [
          if (userDetails.length == 1)
            Center(
              child: UserImageWidget(
                profileImage: userDetails[0]?.profileImage ?? '',
                userId: userDetails[0]?.userId ?? '',
                enableAction: false,
                size: context.mediaQuery.size.width * 0.80,
              ),
            ),
          if (userDetails.length > 1)
            GridView.builder(
              padding: ThemeEdgeInsets.leftTopRight15Bottom150,
              itemCount: userDetails.length,
              gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
                maxCrossAxisExtent: 200,
                crossAxisSpacing: 15,
                mainAxisSpacing: 15,
              ),
              shrinkWrap: true,
              itemBuilder: (_, index) => UserImageWidget(
                profileImage: userDetails[index]?.profileImage ?? '',
                userId: userDetails[index]?.userId ?? '',
                enableAction: false,
                size: context.mediaQuery.size.width * 0.80,
              ),
            ),
          Align(
            alignment: Alignment.bottomCenter,
            child: Card(
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
                          phoneCallState.speakerState ==
                                  SpeakerState.notOnSpeaker
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
            ),
          ),
        ],
      ),
    );
  }
}
