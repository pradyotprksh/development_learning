import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class CallsView extends StatefulWidget {
  const CallsView({super.key});

  @override
  State<CallsView> createState() => _CallsViewState();
}

class _CallsViewState extends State<CallsView>
    with AutomaticKeepAliveClientMixin {
  @override
  Widget build(BuildContext context) {
    super.build(context);
    return Scaffold(
      floatingActionButton: FloatingActionButton(
        onPressed: () {},
        heroTag: Icons.add_call.toString(),
        child: const Icon(Icons.add_call),
      ),
      body: BlocBuilder<CallsBloc, CallsState>(
        builder: (_, callState) => ListView(
          children: [
            if (callState.userGroupCallDetails.isNotEmpty)
              Padding(
                padding: ThemeEdgeInsets.all15,
                child: Text(
                  context.translator.recent,
                ),
              ),
            ListView.builder(
              itemCount: callState.userGroupCallDetails.length,
              primary: false,
              shrinkWrap: true,
              padding: ThemeEdgeInsets.zero,
              itemBuilder: (_, index) {
                final userGroupCallDetail =
                    callState.userGroupCallDetails[index];
                final callDetails = userGroupCallDetail.callDetails;
                final currentUserId =
                    context.read<UserBloc>().state.userDetails?.userId;

                final widget = callDetails.isGroupCall
                    ? StreamBuilder<UsersGroupMessageDetails?>(
                        stream: userGroupCallDetail.groupMessageDetails,
                        builder: (_, snapshot) {
                          final groupWithUsers = snapshot.data;
                          final groupDetails =
                              groupWithUsers?.groupMessageDetails;
                          if (groupDetails != null) {
                            return ListTile(
                              onTap: () {},
                              leading: GroupImageWidget(
                                profileImage: groupDetails.profileImage ?? '',
                                groupId: groupDetails.groupId,
                                size: 40,
                                enableAction: true,
                              ),
                              title: Text(
                                groupDetails.name,
                              ),
                              subtitle: Row(
                                children: [
                                  Icon(
                                    callDetails.startedByUserId == currentUserId
                                        ? Icons.call_made
                                        : Icons.call_received,
                                    color: Colors.green,
                                    size: 15,
                                  ),
                                  ThemeSizedBox.width10,
                                  Flexible(
                                    child: Text(
                                      AppUtilsMethods.timeAgo(
                                        callDetails.createdOnTimeStamp,
                                        context,
                                      ),
                                    ),
                                  ),
                                ],
                              ),
                              trailing: IconButton(
                                onPressed: () async {
                                  if (groupWithUsers != null) {
                                    final navigator = context.navigator;
                                    final currentUserId = context
                                        .read<UserBloc>()
                                        .state
                                        .userDetails
                                        ?.userId;
                                    final users =
                                        await groupWithUsers.usersDetails.first;
                                    users.removeWhere((element) =>
                                        element.userId == currentUserId);
                                    await navigator.pushNamed(
                                      Routes.call,
                                      arguments: CallDetailsArguments(
                                        userDetails: users,
                                        isPhoneCall: callDetails.isPhoneCall,
                                        isVideoCall: callDetails.isVideoCall,
                                        isGroupCall: true,
                                      ),
                                    );
                                  }
                                },
                                icon: Icon(
                                  callDetails.isVideoCall
                                      ? Icons.videocam
                                      : Icons.call,
                                  color: context.themeData.iconTheme.color,
                                ),
                              ),
                            );
                          } else {
                            return ThemeSizedBox.shrink;
                          }
                        })
                    : StreamBuilder<List<UserDetails?>?>(
                        stream: userGroupCallDetail.userDetails,
                        builder: (_, snapshot) {
                          final otherUserDetails = snapshot.data?.firstWhere(
                            (element) => element?.userId != currentUserId,
                          );

                          if (otherUserDetails != null) {
                            return ListTile(
                              onTap: () {},
                              leading: UserImageWidget(
                                profileImage:
                                    otherUserDetails.profileImage ?? '',
                                userId: otherUserDetails.userId,
                                currentMood: otherUserDetails.currentMood,
                                isOnline: otherUserDetails.isOnline,
                                size: 40,
                                useAvatarAsProfile:
                                    otherUserDetails.useAvatarAsProfile,
                                avatarDetails: otherUserDetails.avatarDetails,
                                enableAction: true,
                              ),
                              title: Text(
                                otherUserDetails.name ?? '',
                              ),
                              subtitle: Row(
                                children: [
                                  Icon(
                                    callDetails.startedByUserId == currentUserId
                                        ? Icons.call_made
                                        : Icons.call_received,
                                    color: Colors.green,
                                    size: 15,
                                  ),
                                  ThemeSizedBox.width10,
                                  Flexible(
                                    child: Text(
                                      AppUtilsMethods.timeAgo(
                                        callDetails.createdOnTimeStamp,
                                        context,
                                      ),
                                    ),
                                  ),
                                ],
                              ),
                              trailing: IconButton(
                                onPressed: () {
                                  context.navigator.pushNamed(
                                    Routes.call,
                                    arguments: CallDetailsArguments(
                                      userDetails: [
                                        otherUserDetails,
                                      ],
                                      isPhoneCall: callDetails.isPhoneCall,
                                      isVideoCall: callDetails.isVideoCall,
                                      isGroupCall: false,
                                    ),
                                  );
                                },
                                icon: Icon(
                                  callDetails.isVideoCall
                                      ? Icons.videocam
                                      : Icons.call,
                                  color: context.themeData.iconTheme.color,
                                ),
                              ),
                            );
                          } else {
                            return ThemeSizedBox.shrink;
                          }
                        },
                      );

                return widget;
              },
            ),
            ThemeSizedBox.height80,
          ],
        ),
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}
