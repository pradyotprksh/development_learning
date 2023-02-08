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
          builder: (_, groupMessageState) => Row(
            children: [
              CachedNetworkImageWidget(
                imageUrl:
                    groupMessageState.groupMessageDetails?.profileImage ?? '',
                placeholder: CircleAvatar(
                  radius: 20,
                  backgroundColor: context.themeData.primaryColor,
                  backgroundImage: const AssetImage(
                    AssetsPath.defaultGroupAvatar,
                  ),
                ),
                height: 40,
                width: 40,
                clipToCircle: true,
              ),
              ThemeSizedBox.width15,
              Flexible(
                child: ListTile(
                  onTap: () {},
                  contentPadding: ThemeEdgeInsets.zero,
                  title: Text(
                    groupMessageState.groupMessageDetails?.name ?? '',
                    style: context.themeData.textTheme.titleMedium,
                    overflow: TextOverflow.ellipsis,
                  ),
                ),
              ),
            ],
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
