import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class SavedMessagesView extends StatelessWidget {
  const SavedMessagesView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.savedMessages,
          ),
        ),
        body: BlocBuilder<SavedMessagesBloc, SavedMessagesState>(
          builder: (_, savedMessageState) {
            final currentUserId =
                context.read<UserBloc>().state.userDetails?.userId;

            return ListView.builder(
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
                                  enableAction: currentUserId != details.userId,
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
                                    textAlign: currentUserId != details.userId
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
                                  details.savedMessageDetails.savedOnTimeStamp,
                                  context,
                                )}',
                                style: context.themeData.textTheme.labelSmall,
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
            );
          },
        ),
      );
}
