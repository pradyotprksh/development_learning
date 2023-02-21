import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:stop_watch_timer/stop_watch_timer.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class CallView extends StatefulWidget {
  const CallView({super.key});

  @override
  State<CallView> createState() => _CallViewState();
}

class _CallViewState extends State<CallView> {
  final StopWatchTimer _stopWatchTimer = StopWatchTimer();

  @override
  void dispose() async {
    super.dispose();
    await _stopWatchTimer.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final callDetails =
        context.routeSettings?.arguments as CallDetailsArguments?;
    if (callDetails != null) {
      context.read<CallBloc>().add(
            CallStartedEvent(callDetails),
          );
    } else {
      context.navigator.pop();
    }

    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(
        leading: const CloseButton(),
        backgroundColor: Colors.transparent,
        elevation: 0,
        title: BlocBuilder<CallBloc, CallState>(
          builder: (_, phoneState) {
            if (phoneState.callState == CurrentCallState.ongoing) {
              _stopWatchTimer.onStartTimer();

              return StreamBuilder<int>(
                stream: _stopWatchTimer.rawTime,
                builder: (_, timeData) {
                  final value = timeData.data;
                  final displayTime = StopWatchTimer.getDisplayTime(
                    value ?? 0,
                    milliSecond: false,
                  );

                  if (callDetails != null) {
                    final seconds = StopWatchTimer.getRawSecond(value ?? 0);
                    if (callDetails.isPhoneCall) {
                      NetworkListeners.phoneCallSizeStream.add(
                        AppConstants.oneSecPhoneCallSizeInBytes * seconds,
                      );
                    } else if (callDetails.isVideoCall) {
                      NetworkListeners.videoCallSizeStream.add(
                        AppConstants.oneSecVideoCallSizeInBytes * seconds,
                      );
                    }
                  }

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
      body: callDetails != null
          ? callDetails.isPhoneCall
              ? PhoneCallView(callDetails: callDetails)
              : VideoCallView(callDetails: callDetails)
          : ThemeSizedBox.shrink,
    );
  }
}
