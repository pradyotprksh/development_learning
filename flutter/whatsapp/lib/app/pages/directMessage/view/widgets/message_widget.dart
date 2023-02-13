import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageWidget extends StatelessWidget {
  const MessageWidget({
    super.key,
    required this.message,
    this.userDetails,
  });

  final SingleMessageDetails message;
  final UserDetails? userDetails;

  @override
  Widget build(BuildContext context) {
    final currentUserId = context.read<UserBloc>().state.userDetails?.userId;

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
      return Column(
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
                    currentMood: userDetails?.currentMood,
                    isOnline: null,
                    size: 25,
                  ),
                if (message.sentByUserId != currentUserId) ThemeSizedBox.width5,
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
                        style: context.themeData.textTheme.bodyMedium?.copyWith(
                          color: message.sentByUserId == currentUserId
                              ? context.themeData.textTheme.bodyMedium?.color
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
      );
    }
  }
}
