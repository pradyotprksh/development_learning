import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageWidget extends StatelessWidget {
  const MessageWidget({
    super.key,
    required this.message,
    required this.messageForwardSelected,
    this.userDetails,
    this.directMessageId,
    this.groupId,
  });

  final SingleMessageDetails message;
  final UserDetails? userDetails;
  final String? directMessageId;
  final String? groupId;
  final Function(SingleMessageDetails) messageForwardSelected;

  void _itemSelectedFromMenu(
    BuildContext context,
    MessageMenuItem item,
  ) {
    switch (item) {
      case MessageMenuItem.reply:
        break;
      case MessageMenuItem.copy:
        context.read<UtilitiesBloc>().add(
              MessageCopyForwardEvent(
                message.messageId,
                directMessageId,
                groupId,
                true,
                false,
              ),
            );
        AppUtilsMethods.copyTextWithSnackBar(context, message.message);
        break;
      case MessageMenuItem.forward:
        context.read<UtilitiesBloc>().add(
              MessageCopyForwardEvent(
                message.messageId,
                directMessageId,
                groupId,
                false,
                true,
              ),
            );
        messageForwardSelected(message);
        break;
      case MessageMenuItem.pin:
        break;
      case MessageMenuItem.delete:
        break;
    }
  }

  @override
  Widget build(BuildContext context) {
    final currentUserId = context.read<UserBloc>().state.userDetails?.userId;
    final isCurrentUserMessage = message.sentByUserId == currentUserId;

    if (message.isSystemMessage) {
      return Padding(
        padding: ThemeEdgeInsets.all15,
        child: Text(
          message.message,
          textAlign: TextAlign.center,
          style: context.themeData.textTheme.bodySmall,
        ),
      );
    } else {
      return Theme(
        data: context.themeData.copyWith(
          splashColor: Colors.transparent,
          highlightColor: Colors.transparent,
        ),
        child: PopupMenuButton<MessageMenuItem>(
          position: PopupMenuPosition.under,
          offset: Offset(
            isCurrentUserMessage ? context.mediaQuery.size.width : 0,
            0,
          ),
          padding: ThemeEdgeInsets.zero,
          enableFeedback: false,
          tooltip: message.message,
          splashRadius: 0,
          onSelected: (item) {
            _itemSelectedFromMenu(context, item);
          },
          itemBuilder: (_) => [
            PopupMenuItem(
              value: MessageMenuItem.reply,
              child: Row(
                children: [
                  const Icon(
                    Icons.reply,
                    size: 15,
                  ),
                  ThemeSizedBox.width5,
                  Text(
                    context.translator.reply,
                  ),
                ],
              ),
            ),
            PopupMenuItem(
              value: MessageMenuItem.copy,
              child: Row(
                children: [
                  const Icon(
                    Icons.copy,
                    size: 15,
                  ),
                  ThemeSizedBox.width5,
                  Text(
                    context.translator.copy,
                  ),
                ],
              ),
            ),
            PopupMenuItem(
              value: MessageMenuItem.forward,
              child: Row(
                children: [
                  const Icon(
                    Icons.next_plan,
                    size: 15,
                  ),
                  ThemeSizedBox.width5,
                  Text(
                    context.translator.forward,
                  ),
                ],
              ),
            ),
            PopupMenuItem(
              value: MessageMenuItem.pin,
              child: Row(
                children: [
                  const Icon(
                    Icons.vertical_align_top,
                    size: 15,
                  ),
                  ThemeSizedBox.width5,
                  Text(
                    context.translator.pin,
                  ),
                ],
              ),
            ),
            if (message.sentByUserId == currentUserId)
              PopupMenuItem(
                value: MessageMenuItem.delete,
                child: Row(
                  children: [
                    const Icon(
                      Icons.delete,
                      size: 15,
                    ),
                    ThemeSizedBox.width5,
                    Text(
                      context.translator.delete,
                    ),
                  ],
                ),
              ),
          ],
          child: Column(
            children: [
              ThemeSizedBox.height10,
              Padding(
                padding: ThemeEdgeInsets.left15Right15,
                child: Row(
                  mainAxisAlignment: message.sentByUserId == currentUserId
                      ? MainAxisAlignment.end
                      : MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    if (message.sentByUserId != currentUserId)
                      UserImageWidget(
                        profileImage: userDetails?.profileImage ?? '',
                        userId: userDetails?.userId ?? '',
                        isOnline: userDetails?.isOnline,
                        size: 25,
                      ),
                    if (message.sentByUserId != currentUserId)
                      ThemeSizedBox.width5,
                    if (message.sentByUserId == currentUserId) const Spacer(),
                    Flexible(
                      fit: FlexFit.loose,
                      child: Container(
                        decoration: BoxDecoration(
                          color: message.sentByUserId == currentUserId
                              ? context.themeData.primaryColor.withAlpha(25)
                              : context.themeData.primaryColor,
                          borderRadius: BorderRadius.circular(
                            20,
                          ),
                        ),
                        child: Padding(
                          padding: ThemeEdgeInsets.leftRight15TopBottom5,
                          child: Text(
                            message.message,
                            textAlign: message.sentByUserId == currentUserId
                                ? TextAlign.end
                                : TextAlign.start,
                            style: context.themeData.textTheme.bodyMedium
                                ?.copyWith(
                              color: message.sentByUserId == currentUserId
                                  ? context
                                      .themeData.textTheme.bodyMedium?.color
                                  : Colors.white,
                            ),
                          ),
                        ),
                      ),
                    ),
                    if (message.sentByUserId != currentUserId) const Spacer(),
                  ],
                ),
              ),
            ],
          ),
        ),
      );
    }
  }
}