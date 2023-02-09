import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class GroupMessageView extends StatelessWidget {
  const GroupMessageView({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = context.routeSettings?.arguments as Map<String, String>;
    final groupId = arguments[Keys.groupId] as String;
    context.read<GroupMessageBloc>().add(
          FetchGroupDetails(groupId),
        );

    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(
        title: BlocBuilder<GroupMessageBloc, GroupMessageState>(
          builder: (_, groupMessageState) => ListTile(
            onTap: () {},
            contentPadding: ThemeEdgeInsets.zero,
            leading: CachedNetworkImageWidget(
              imageUrl: groupMessageState
                      .groupMessageDetails?.groupMessageDetails?.profileImage ??
                  '',
              placeholder: CircleAvatar(
                radius: 20,
                backgroundColor: context.themeData.primaryColor,
                child: const Icon(
                  Icons.group,
                  color: Colors.white,
                ),
              ),
              height: 40,
              width: 40,
              clipToCircle: true,
            ),
            title: Text(
              groupMessageState
                      .groupMessageDetails?.groupMessageDetails?.name ??
                  '',
              style: context.themeData.textTheme.titleMedium,
              overflow: TextOverflow.ellipsis,
            ),
          ),
        ),
        actions: [
          IconButton(
            onPressed: () {},
            icon: const Icon(
              Icons.video_call,
            ),
          ),
          IconButton(
            onPressed: () async {
              final navigator = context.navigator;
              final groupId = context
                  .read<GroupMessageBloc>()
                  .state
                  .groupMessageDetails
                  ?.groupMessageDetails
                  ?.groupId;
              final currentUserId =
                  context.read<UserBloc>().state.userDetails?.userId;
              final users = await context
                  .read<GroupMessageBloc>()
                  .state
                  .groupMessageDetails
                  ?.usersDetails
                  .first;
              users?.removeWhere((element) => element.userId == currentUserId);
              if (users != null) {
                await navigator.pushNamed(
                  Routes.phoneCall,
                  arguments: CallDetailsArguments(
                    userDetails: users,
                    isPhoneCall: true,
                    isVideoCall: false,
                    isGroupCall: true,
                    groupId: groupId,
                  ),
                );
              }
            },
            icon: const Icon(
              Icons.call,
            ),
          ),
          PopupMenuButton<GroupMessageMenuItems>(
            onSelected: (item) {},
            color: context.themeData.popupMenuTheme.color,
            itemBuilder: (_) => [
              PopupMenuItem(
                value: GroupMessageMenuItems.groupInfo,
                child: Text(
                  context.translator.groupInfo,
                ),
              ),
              PopupMenuItem(
                value: GroupMessageMenuItems.groupMedia,
                child: Text(
                  context.translator.groupMedia,
                ),
              ),
              PopupMenuItem(
                value: GroupMessageMenuItems.search,
                child: Text(
                  context.translator.search,
                ),
              ),
              PopupMenuItem(
                value: GroupMessageMenuItems.muteNotifications,
                child: Text(
                  context.translator.muteNotifications,
                ),
              ),
              PopupMenuItem(
                value: GroupMessageMenuItems.disappearingMessages,
                child: Text(
                  context.translator.disappearingMessages,
                ),
              ),
              PopupMenuItem(
                value: GroupMessageMenuItems.wallpaper,
                child: Text(
                  context.translator.wallpaper,
                ),
              ),
              PopupMenuItem(
                value: GroupMessageMenuItems.more,
                child: Text(
                  context.translator.more,
                ),
              ),
            ],
            icon: const Icon(
              Icons.more_vert,
            ),
          ),
        ],
      ),
      body: Stack(
        children: [
          ListView(),
          Container(
            alignment: Alignment.bottomCenter,
            padding: ThemeEdgeInsets.all10,
            child: TextFormField(),
          ),
        ],
      ),
    );
  }
}
