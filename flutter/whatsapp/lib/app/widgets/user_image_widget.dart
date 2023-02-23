import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:fluttermoji/fluttermoji.dart';
import 'package:whatsapp/app/app.dart';

class UserImageWidget extends StatelessWidget {
  const UserImageWidget({
    super.key,
    required this.profileImage,
    required this.userId,
    this.currentMood,
    this.isOnline,
    this.enableAction = true,
    this.showProgressIndicator = false,
    this.size = 40,
    this.extraAction,
    this.useAvatarAsProfile = false,
    this.avatarDetails,
  });

  final String profileImage;
  final String? currentMood;
  final String userId;
  final double size;
  final bool enableAction;
  final void Function()? extraAction;
  final bool? isOnline;
  final bool showProgressIndicator;
  final bool useAvatarAsProfile;
  final String? avatarDetails;

  @override
  Widget build(BuildContext context) => GestureDetector(
        onTap: userId.isNotEmpty && (extraAction != null || enableAction)
            ? () {
                extraAction?.call();
                if (enableAction) {
                  context.navigator.pushNamed(
                    Routes.messages,
                    arguments: <String, String>{
                      Keys.userId: userId,
                    },
                  );
                }
              }
            : null,
        child: Stack(
          alignment: Alignment.bottomRight,
          children: [
            if (useAvatarAsProfile && avatarDetails != null)
              CircleAvatar(
                radius: size / 2,
                backgroundColor: context.themeData.primaryColor,
                child: ClipRRect(
                  borderRadius: BorderRadius.all(
                    Radius.circular(
                      size,
                    ),
                  ),
                  child: SvgPicture.string(
                    FluttermojiFunctions()
                        .decodeFluttermojifromString(avatarDetails.toString()),
                    height: size,
                    width: size,
                  ),
                ),
              ),
            if (!useAvatarAsProfile || avatarDetails == null)
              CachedNetworkImageWidget(
                tag: userId,
                imageUrl: profileImage,
                showProgressIndicator: showProgressIndicator,
                placeholder: CircleAvatar(
                  radius: size / 2,
                  backgroundColor: context.themeData.primaryColor,
                  child: Icon(
                    Icons.person,
                    color: Colors.white,
                    size: size / 2,
                  ),
                ),
                errorWidget: CircleAvatar(
                  radius: size / 2,
                  backgroundColor: context.themeData.primaryColor,
                  child: Icon(
                    Icons.person,
                    color: Colors.white,
                    size: size / 2,
                  ),
                ),
                height: size,
                width: size,
                clipToCircle: true,
              ),
            if (isOnline != null)
              Container(
                height: size / 2.5,
                width: size / 2.5,
                decoration: BoxDecoration(
                  color: isOnline ?? false ? Colors.green : Colors.grey,
                  borderRadius: BorderRadius.circular(
                    50,
                  ),
                ),
                alignment: Alignment.center,
                child: currentMood != null
                    ? Text(
                        currentMood ?? '',
                        textAlign: TextAlign.center,
                        style: context.themeData.textTheme.labelSmall?.copyWith(
                          fontSize: 8,
                        ),
                      )
                    : ThemeSizedBox.shrink,
              ),
          ],
        ),
      );
}
