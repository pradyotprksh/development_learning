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
        builder: (_, chatState) => ListView.builder(
          itemCount: chatState.messageListWithUserDetails.length,
          itemBuilder: (_, index) {
            final details = chatState.messageListWithUserDetails[index];

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
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}
