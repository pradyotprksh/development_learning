import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class DirectMessageView extends StatefulWidget {
  const DirectMessageView({super.key});

  @override
  State<DirectMessageView> createState() => _DirectMessageViewState();
}

class _DirectMessageViewState extends State<DirectMessageView> {
  @override
  Widget build(BuildContext context) {
    final arguments = context.routeSettings?.arguments as Map<String, String>;
    final messageId = arguments[Keys.messageId];
    final userId = arguments[Keys.userId];
    if (messageId != null) {
      context.read<DirectMessageBloc>().add(
            FetchSelectedMessageDetails(
              messageId,
            ),
          );
    } else if (userId != null) {
      context.read<DirectMessageBloc>().add(
            FetchSelectedUserDetails(
              userId,
            ),
          );
    }

    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(
        title: BlocBuilder<DirectMessageBloc, DirectMessageState>(
          builder: (_, messageState) => ListTile(
            onTap: () {
              context.navigator.pushNamed(
                Routes.messageDetails,
                arguments: MessageRouteDetails(
                  directMessageId: messageId,
                ),
              );
            },
            contentPadding: ThemeEdgeInsets.zero,
            leading: UserImageWidget(
              profileImage: messageState.userDetails?.profileImage ?? '',
              userId: messageState.userDetails?.userId ?? '',
              currentMood: messageState.userDetails?.currentMood,
              isOnline: messageState.userDetails?.isOnline,
              enableAction: false,
              size: 30,
              useAvatarAsProfile:
                  messageState.userDetails?.useAvatarAsProfile ?? false,
              avatarDetails: messageState.userDetails?.avatarDetails,
            ),
            title: Text(
              messageState.userDetails?.name ?? '',
              style: context.themeData.textTheme.titleMedium,
              overflow: TextOverflow.ellipsis,
            ),
          ),
        ),
        actions: AppDetails.isWeb
            ? []
            : [
                IconButton(
                  onPressed: () {
                    _makeVideoOrPhoneCall(context, false);
                  },
                  icon: const Icon(
                    Icons.video_call,
                  ),
                ),
                IconButton(
                  onPressed: () {
                    _makeVideoOrPhoneCall(context, true);
                  },
                  icon: const Icon(
                    Icons.call,
                  ),
                ),
                PopupMenuButton<DirectMessageMenuItems>(
                  onSelected: (item) {
                    switch (item) {
                      case DirectMessageMenuItems.viewContact:
                        context.navigator.pushNamed(
                          Routes.messageDetails,
                          arguments: MessageRouteDetails(
                            directMessageId: messageId,
                          ),
                        );
                        break;
                      case DirectMessageMenuItems.mediaLinksDocs:
                        break;
                      case DirectMessageMenuItems.search:
                        break;
                      case DirectMessageMenuItems.muteNotifications:
                        break;
                      case DirectMessageMenuItems.disappearingMessages:
                        break;
                      case DirectMessageMenuItems.wallpaper:
                        break;
                      case DirectMessageMenuItems.more:
                        break;
                    }
                  },
                  color: context.themeData.popupMenuTheme.color,
                  itemBuilder: (_) => [
                    PopupMenuItem(
                      value: DirectMessageMenuItems.viewContact,
                      child: Text(
                        context.translator.viewContact,
                      ),
                    ),
                    PopupMenuItem(
                      value: DirectMessageMenuItems.mediaLinksDocs,
                      child: Text(
                        context.translator.mediaLinksDocs,
                      ),
                    ),
                    PopupMenuItem(
                      value: DirectMessageMenuItems.search,
                      child: Text(
                        context.translator.search,
                      ),
                    ),
                    PopupMenuItem(
                      value: DirectMessageMenuItems.muteNotifications,
                      child: Text(
                        context.translator.muteNotifications,
                      ),
                    ),
                    PopupMenuItem(
                      value: DirectMessageMenuItems.disappearingMessages,
                      child: Text(
                        context.translator.disappearingMessages,
                      ),
                    ),
                    PopupMenuItem(
                      value: DirectMessageMenuItems.wallpaper,
                      child: Text(
                        context.translator.wallpaper,
                      ),
                    ),
                    PopupMenuItem(
                      value: DirectMessageMenuItems.more,
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
      body: Column(
        children: [
          BlocBuilder<DirectMessageBloc, DirectMessageState>(
            builder: (_, directMessageState) => Flexible(
              child: ListView.builder(
                reverse: true,
                padding: ThemeEdgeInsets.zero,
                itemCount: directMessageState.messages.length,
                itemBuilder: (_, index) => MessageWidget(
                  message: directMessageState.messages[index],
                  userDetails: directMessageState.userDetails,
                  messageForwardSelected: (details) {},
                  messageSavedSelected: (details) {
                    context.read<DirectMessageBloc>().add(
                          SaveDirectMessageEvent(
                            messageId: details.messageId,
                            sentByUserId: details.sentByUserId,
                            directMessageId: directMessageState
                                .directMessageDetails?.messageId,
                          ),
                        );
                  },
                  directMessageId:
                      directMessageState.directMessageDetails?.messageId,
                ),
              ),
            ),
          ),
          Padding(
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
                  return BlocBuilder<DirectMessageBloc, DirectMessageState>(
                    builder: (_, directMessageState) =>
                        MessageFieldWithEmojiAttachmentsWidget(
                      onEmojiButtonPressed: () {
                        context.read<DirectMessageBloc>().add(
                              const ToggleEmojisOption(),
                            );
                      },
                      onMessageSubmitted: (message) {
                        if (message.trim().isNotEmpty ||
                            directMessageState.attachments.isNotEmpty) {
                          context.read<DirectMessageBloc>().add(
                                AddMessage(message),
                              );
                        }
                      },
                      closeEmojiOption: () {
                        context.read<DirectMessageBloc>().add(
                              const ToggleEmojisOption(
                                shouldShow: false,
                              ),
                            );
                      },
                      isEmojiOptionVisible:
                          directMessageState.isEmojiOptionVisible,
                      onAttachmentSelected: (details) {
                        context.read<DirectMessageBloc>().add(
                              DirectMessageAttachmentSelectedEvent(
                                details,
                              ),
                            );
                      },
                      attachments: directMessageState.attachments,
                      uploadingAttachment: directMessageState.uploadingFile,
                    ),
                  );
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

  void _makeVideoOrPhoneCall(
    BuildContext context,
    bool isPhoneCall,
  ) {
    context.navigator.pushNamed(
      Routes.call,
      arguments: CallDetailsArguments(
        userDetails: [
          context.read<DirectMessageBloc>().state.userDetails,
        ],
        isPhoneCall: isPhoneCall,
        isVideoCall: !isPhoneCall,
        isGroupCall: false,
      ),
    );
  }
}
