import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/domain/domain.dart';

class SavedMessagesView extends StatelessWidget {
  const SavedMessagesView({super.key});

  void _openPageBasedOnSelection(
    BuildContext context,
    SavedMessagesMenuItem item,
  ) {
    switch (item) {
      case SavedMessagesMenuItem.clear:
        context.read<SavedMessagesBloc>().add(const ClearSavedMessages());
        break;
    }
  }

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.savedMessages,
          ),
          actions: [
            PopupMenuButton<SavedMessagesMenuItem>(
              onSelected: (item) {
                _openPageBasedOnSelection(context, item);
              },
              color: context.themeData.popupMenuTheme.color,
              itemBuilder: (_) => [
                PopupMenuItem(
                  value: SavedMessagesMenuItem.clear,
                  child: Text(
                    context.translator.clear,
                  ),
                ),
              ],
            ),
          ],
        ),
        body: BlocBuilder<SavedMessagesBloc, SavedMessagesState>(
          builder: (_, savedMessageState) {
            final currentUserId =
                context.read<UserBloc>().state.userDetails?.userId;

            return Stack(
              children: [
                ListView.builder(
                  itemCount: savedMessageState.messages.length,
                  itemBuilder: (_, index) {
                    final details = savedMessageState.messages[index];
                    if (details != null) {
                      return Card(
                        child: Padding(
                          padding: ThemeEdgeInsets.topLeftRight15,
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: [
                              StreamBuilder<UserDetails?>(
                                stream: details.userDetails,
                                builder: (_, snapshot) {
                                  final details = snapshot.data;
                                  if (details == null) {
                                    return ThemeSizedBox.shrink;
                                  } else {
                                    final userImage = UserImageWidget(
                                      profileImage: details.profileImage ?? '',
                                      userId: details.userId,
                                      currentMood: details.currentMood,
                                      isOnline: details.isOnline,
                                      enableAction:
                                          currentUserId != details.userId,
                                      useAvatarAsProfile:
                                          details.useAvatarAsProfile,
                                      avatarDetails: details.avatarDetails,
                                    );
                                    return ListTile(
                                      contentPadding: ThemeEdgeInsets.zero,
                                      leading: currentUserId == details.userId
                                          ? null
                                          : userImage,
                                      trailing: currentUserId != details.userId
                                          ? null
                                          : userImage,
                                      title: Text(
                                        details.name ?? '',
                                        textAlign:
                                            currentUserId != details.userId
                                                ? TextAlign.start
                                                : TextAlign.end,
                                      ),
                                    );
                                  }
                                },
                              ),
                              Flexible(
                                child: StreamBuilder<SingleMessageDetails?>(
                                  stream: details.messageDetails,
                                  builder: (_, snapshot) {
                                    final details = snapshot.data;
                                    if (details == null) {
                                      return ThemeSizedBox.shrink;
                                    } else {
                                      return MessageWidget(
                                        message: details,
                                        messageForwardSelected: (details) {},
                                        messageSavedSelected: (details) {},
                                        showOtherDetailsAndOption: false,
                                      );
                                    }
                                  },
                                ),
                              ),
                              Padding(
                                padding: ThemeEdgeInsets.all10,
                                child: SizedBox(
                                  width: double.infinity,
                                  child: Text(
                                    '${context.translator.saved} ${AppUtilsMethods.timeAgo(
                                      details
                                          .savedMessageDetails.savedOnTimeStamp,
                                      context,
                                    )}',
                                    style:
                                        context.themeData.textTheme.labelSmall,
                                    textAlign: TextAlign.end,
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ),
                      );
                    } else {
                      return ThemeSizedBox.shrink;
                    }
                  },
                ),
                if (savedMessageState.pageState == PageState.loading)
                  const CircularProgressIndicatorWidget(),
              ],
            );
          },
        ),
      );
}
