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
      body: BlocBuilder<CallBloc, CallState>(
        builder: (_, callState) => ListView(
          children: [
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

                final leading = callDetails.isGroupCall
                    ? StreamBuilder<UsersGroupMessageDetails?>(
                        stream: userGroupCallDetail.groupMessageDetails,
                        builder: (_, snapshot) => GroupImageWidget(
                          profileImage: snapshot
                                  .data?.groupMessageDetails?.profileImage ??
                              '',
                          groupId:
                              snapshot.data?.groupMessageDetails?.groupId ?? '',
                          size: 40,
                        ),
                      )
                    : StreamBuilder<List<UserDetails?>?>(
                        stream: userGroupCallDetail.userDetails,
                        builder: (_, snapshot) {
                          final otherUserDetails = snapshot.data?.firstWhere(
                            (element) => element?.userId != currentUserId,
                          );

                          if (otherUserDetails != null) {
                            return UserImageWidget(
                              profileImage: otherUserDetails.profileImage ?? '',
                              userId: otherUserDetails.userId,
                              size: 40,
                            );
                          } else {
                            return ThemeSizedBox.shrink;
                          }
                        },
                      );

                final title = callDetails.isGroupCall
                    ? StreamBuilder<UsersGroupMessageDetails?>(
                        stream: userGroupCallDetail.groupMessageDetails,
                        builder: (_, snapshot) => Text(
                          snapshot.data?.groupMessageDetails?.name ?? '',
                        ),
                      )
                    : StreamBuilder<List<UserDetails?>?>(
                        stream: userGroupCallDetail.userDetails,
                        builder: (_, snapshot) {
                          final otherUserDetails = snapshot.data?.firstWhere(
                            (element) => element?.userId != currentUserId,
                          );

                          if (otherUserDetails != null) {
                            return Text(
                              otherUserDetails.name ?? '',
                            );
                          } else {
                            return ThemeSizedBox.shrink;
                          }
                        },
                      );

                return ListTile(
                  onTap: () {},
                  leading: leading,
                  title: title,
                  subtitle: Text(
                    AppUtilsMethods.timeAgo(
                      callDetails.createdOnTimeStamp,
                      context,
                    ),
                  ),
                  trailing: IconButton(
                    onPressed: () async {
                      final navigator = context.navigator;
                      var users = <UserDetails>[];
                      if (callDetails.isGroupCall) {
                        final groupDetails = await userGroupCallDetail
                            .groupMessageDetails?.first;
                        if (groupDetails != null) {
                          users = await groupDetails.usersDetails.first
                            ..removeWhere(
                                (element) => element.userId == currentUserId);
                        }
                      } else {
                        final userDetails =
                            await userGroupCallDetail.userDetails?.first;
                        if (userDetails != null) {
                          users = [
                            userDetails.firstWhere(
                              (element) => element.userId != currentUserId,
                            )
                          ];
                        }
                      }
                      await navigator.pushNamed(
                        Routes.phoneCall,
                        arguments: CallDetailsArguments(
                          userDetails: users,
                          isPhoneCall: callDetails.isPhoneCall,
                          isVideoCall: callDetails.isVideoCall,
                          isGroupCall: callDetails.isGroupCall,
                        ),
                      );
                    },
                    icon: Icon(
                      callDetails.isVideoCall ? Icons.videocam : Icons.call,
                      color: context.themeData.iconTheme.color,
                    ),
                  ),
                );
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
