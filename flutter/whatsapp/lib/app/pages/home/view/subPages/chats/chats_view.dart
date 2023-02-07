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
        child: const Icon(Icons.chat),
      ),
      body: BlocBuilder<ChatBloc, ChatState>(
        builder: (_, chatState) => ListView(
          padding: ThemeEdgeInsets.zero,
          children: [
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
                  stream: details.otherUserDetails.stream,
                  builder: (_, userDetails) => ListTile(
                    onTap: () {
                      final otherUserId = userDetails.data?.userId;
                      if (otherUserId != null) {
                        context.navigator.pushNamed(
                          Routes.messages,
                          arguments: <String, String>{
                            Keys.userId: otherUserId,
                          },
                        );
                      }
                    },
                    leading: UserImageWidget(
                      profileImage: userDetails.data?.profileImage ?? '',
                      userId: userDetails.data?.userId ?? '',
                      enableAction: false,
                      size: 40,
                    ),
                    title: Text(
                      userDetails.data?.name ?? '',
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
                  onTap: () {},
                  leading: CachedNetworkImageWidget(
                    imageUrl: details.profileImage ?? '',
                    placeholder: CircleAvatar(
                      radius: 20,
                      backgroundColor: context.themeData.primaryColor,
                      backgroundImage: const AssetImage(
                        AssetsPath.defaultAvatar,
                      ),
                    ),
                    height: 40,
                    width: 40,
                    clipToCircle: true,
                  ),
                  title: Text(
                    details.name,
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
                    style: context.themeData.textTheme.labelSmall,
                  ),
                );
              },
            ),
          ],
        ),
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}
