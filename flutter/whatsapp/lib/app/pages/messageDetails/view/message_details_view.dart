import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class MessageDetailsView extends StatelessWidget {
  const MessageDetailsView({super.key});

  @override
  Widget build(BuildContext context) {
    final details = context.routeSettings?.arguments as MessageRouteDetails;
    context.read<MessageDetailsBloc>().add(FetchDetails(details));

    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(
        actions: [
          PopupMenuButton<MessageDetailsMenuItem>(
            onSelected: (item) {
              switch (item) {
                case MessageDetailsMenuItem.share:
                  break;
                case MessageDetailsMenuItem.edit:
                  break;
                case MessageDetailsMenuItem.viewInAddressBook:
                  break;
                case MessageDetailsMenuItem.viewSecurityCode:
                  break;
                case MessageDetailsMenuItem.changeSubject:
                  break;
              }
            },
            color: context.themeData.popupMenuTheme.color,
            itemBuilder: (_) => [
              if (details.isDirectMessage)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.edit,
                  child: Text(
                    context.translator.viewContact,
                  ),
                ),
              if (details.isGroup)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.changeSubject,
                  child: Text(
                    context.translator.mediaLinksDocs,
                  ),
                ),
              if (details.isDirectMessage)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.share,
                  child: Text(
                    context.translator.search,
                  ),
                ),
              if (details.isDirectMessage)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.viewInAddressBook,
                  child: Text(
                    context.translator.muteNotifications,
                  ),
                ),
              if (details.isDirectMessage)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.viewSecurityCode,
                  child: Text(
                    context.translator.disappearingMessages,
                  ),
                ),
            ],
            icon: const Icon(
              Icons.more_vert,
            ),
          ),
        ],
      ),
    );
  }
}
