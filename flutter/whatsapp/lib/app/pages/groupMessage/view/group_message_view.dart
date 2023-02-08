import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class GroupMessageView extends StatelessWidget {
  const GroupMessageView({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = context.routeSettings?.arguments as Map<String, String>;
    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(
        actions: [
          IconButton(
            onPressed: () {},
            icon: const Icon(
              Icons.video_call,
            ),
          ),
          IconButton(
            onPressed: () {},
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
