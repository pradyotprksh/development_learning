import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class ChatsView extends StatefulWidget {
  const ChatsView({super.key});

  @override
  State<ChatsView> createState() => _ChatsViewState();
}

class _ChatsViewState extends State<ChatsView>
    with AutomaticKeepAliveClientMixin {
  @override
  Widget build(BuildContext context) {
    super.build(context);
    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          context.navigator.pushNamed(Routes.selectContact);
        },
        heroTag: Icons.chat.toString(),
        child: const Icon(Icons.chat),
      ),
      body: BlocBuilder<ChatBloc, ChatState>(
        builder: (_, chatState) => ListView(
          padding: ThemeEdgeInsets.zero,
          children: [
            if (chatState.directMessageListWithUserDetails.isNotEmpty)
              Padding(
                padding: ThemeEdgeInsets.all15,
                child: Text(
                  context.translator.personalChats,
                ),
              ),
            ListView.builder(
              primary: false,
              shrinkWrap: true,
              itemCount: chatState.directMessageListWithUserDetails.length,
              itemBuilder: (_, index) {
                final details =
                    chatState.directMessageListWithUserDetails[index];

                return StreamBuilder<UserDetails?>(
                  stream: details.otherUserDetails,
                  builder: (_, userDetails) => ListTile(
                    onTap: () {
                      final otherUserId = userDetails.data?.userId;
                      if (otherUserId != null) {
                        context.navigator.pushNamed(
                          Routes.messages,
                          arguments: <String, String>{
                            Keys.messageId:
                                details.directMessageDetails.messageId,
                          },
                        );
                      }
                    },
                    leading: UserImageWidget(
                      profileImage: userDetails.data?.profileImage ?? '',
                      userId: userDetails.data?.userId ?? '',
                      currentMood: userDetails.data?.currentMood,
                      isOnline: userDetails.data?.isOnline,
                      enableAction: false,
                      size: 40,
                      useAvatarAsProfile:
                          userDetails.data?.useAvatarAsProfile ?? false,
                      avatarDetails: userDetails.data?.avatarDetails,
                    ),
                    title: Text(
                      userDetails.data?.name ?? '',
                      maxLines: 1,
                      overflow: TextOverflow.ellipsis,
                    ),
                    subtitle: Text(
                      details.directMessageDetails.lastMessage ?? '',
                      maxLines: 1,
                      overflow: TextOverflow.ellipsis,
                    ),
                    trailing: Text(
                      AppUtilsMethods.timeAgo(
                        details.directMessageDetails.lastMessageOnTimeStamp,
                        context,
                      ),
                      style: context.themeData.textTheme.labelSmall,
                    ),
                  ),
                );
              },
            ),
            if (chatState.groupMessages.isNotEmpty)
              Padding(
                padding: ThemeEdgeInsets.all15,
                child: Text(
                  context.translator.groups,
                ),
              ),
            ListView.builder(
              primary: false,
              shrinkWrap: true,
              itemCount: chatState.groupMessages.length,
              itemBuilder: (_, index) {
                final details = chatState.groupMessages[index];

                return ListTile(
                  onTap: () {
                    final groupId = details.groupId;
                    context.navigator.pushNamed(
                      Routes.groupMessages,
                      arguments: <String, String>{
                        Keys.groupId: groupId,
                      },
                    );
                  },
                  leading: GroupImageWidget(
                    profileImage: details.profileImage ?? '',
                    groupId: details.groupId,
                    enableAction: false,
                    size: 40,
                  ),
                  title: Text(
                    details.name,
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                  ),
                  subtitle: Text(
                    details.lastMessage ?? '',
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                  ),
                  trailing: Text(
                    AppUtilsMethods.timeAgo(
                      details.lastMessageOnTimeStamp,
                      context,
                    ),
                    style: context.themeData.textTheme.bodySmall,
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
