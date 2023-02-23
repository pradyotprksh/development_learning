import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';

class PhoneCallView extends StatelessWidget {
  const PhoneCallView({
    super.key,
    required this.callDetails,
  });

  final CallDetailsArguments callDetails;

  @override
  Widget build(BuildContext context) => Stack(
        children: [
          if (callDetails.userDetails.length == 1)
            Center(
              child: UserImageWidget(
                profileImage: callDetails.userDetails[0]?.profileImage ?? '',
                userId: callDetails.userDetails[0]?.userId ?? '',
                enableAction: false,
                size: context.mediaQuery.size.width * 0.80,
                useAvatarAsProfile:
                    callDetails.userDetails[0]?.useAvatarAsProfile ?? false,
                avatarDetails: callDetails.userDetails[0]?.avatarDetails,
              ),
            ),
          if (callDetails.userDetails.length > 1)
            GridView.builder(
              padding: ThemeEdgeInsets.leftTopRight15Bottom150,
              itemCount: callDetails.userDetails.length,
              gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
                maxCrossAxisExtent: 200,
                crossAxisSpacing: 15,
                mainAxisSpacing: 15,
              ),
              shrinkWrap: true,
              itemBuilder: (_, index) => UserImageWidget(
                profileImage:
                    callDetails.userDetails[index]?.profileImage ?? '',
                userId: callDetails.userDetails[index]?.userId ?? '',
                enableAction: false,
                size: context.mediaQuery.size.width * 0.80,
                useAvatarAsProfile:
                    callDetails.userDetails[index]?.useAvatarAsProfile ?? false,
                avatarDetails: callDetails.userDetails[index]?.avatarDetails,
              ),
            ),
          Align(
            alignment: Alignment.bottomCenter,
            child: Card(
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
            ),
          ),
        ],
      );
}
