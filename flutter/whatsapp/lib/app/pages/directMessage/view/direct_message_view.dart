import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class DirectMessageView extends StatelessWidget {
  const DirectMessageView({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = context.routeSettings?.arguments as Map<String, String>;
    context.read<DirectMessageBloc>().add(
          FetchSelectedUserDetails(
            arguments[Keys.userId] ?? '',
          ),
        );

    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(
        title: BlocBuilder<DirectMessageBloc, DirectMessageState>(
          buildWhen: (previousState, currentState) =>
              previousState.userDetails != currentState.userDetails,
          builder: (_, messageState) => Row(
            children: [
              UserImageWidget(
                profileImage: messageState.userDetails?.profileImage ?? '',
                userId: messageState.userDetails?.userId ?? '',
                enableAction: false,
                size: 30,
              ),
              ThemeSizedBox.width15,
              Flexible(
                child: ListTile(
                  onTap: () {},
                  contentPadding: ThemeEdgeInsets.zero,
                  title: Text(
                    messageState.userDetails?.name ?? '',
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
          PopupMenuButton<MessageMenuItems>(
            onSelected: (item) {},
            color: context.themeData.popupMenuTheme.color,
            itemBuilder: (_) => [
              PopupMenuItem(
                value: MessageMenuItems.viewContact,
                child: Text(
                  context.translator.viewContact,
                ),
              ),
              PopupMenuItem(
                value: MessageMenuItems.mediaLinksDocs,
                child: Text(
                  context.translator.mediaLinksDocs,
                ),
              ),
              PopupMenuItem(
                value: MessageMenuItems.search,
                child: Text(
                  context.translator.search,
                ),
              ),
              PopupMenuItem(
                value: MessageMenuItems.muteNotifications,
                child: Text(
                  context.translator.muteNotifications,
                ),
              ),
              PopupMenuItem(
                value: MessageMenuItems.disappearingMessages,
                child: Text(
                  context.translator.disappearingMessages,
                ),
              ),
              PopupMenuItem(
                value: MessageMenuItems.wallpaper,
                child: Text(
                  context.translator.wallpaper,
                ),
              ),
              PopupMenuItem(
                value: MessageMenuItems.more,
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
            child: BlocBuilder<DirectMessageBloc, DirectMessageState>(
              buildWhen: (previousState, currentState) =>
                  previousState.directMessageDetails !=
                      currentState.directMessageDetails ||
                  previousState.pageState != currentState.pageState,
              builder: (_, messageState) {
                if (messageState.pageState == PageState.loading) {
                  return const LinearProgressIndicatorWidget();
                }

                final messageDetails = messageState.directMessageDetails;

                if (messageDetails != null) {
                  return TextFormField();
                } else {
                  return ElevatedButton(
                    onPressed: () {
                      context.read<DirectMessageBloc>().add(
                            CreateDirectMessage(
                              context.translator.conversationCreated,
                            ),
                          );
                    },
                    child: Text(
                      context.translator.startConversation,
                    ),
                  );
                }
              },
            ),
          ),
        ],
      ),
    );
  }
}