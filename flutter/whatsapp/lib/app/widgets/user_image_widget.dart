import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class UserImageWidget extends StatelessWidget {
  const UserImageWidget({
    super.key,
    required this.profileImage,
    required this.userId,
    this.enableAction = true,
    this.size = 40,
    this.extraAction,
  });

  final String profileImage;
  final String userId;
  final double size;
  final bool enableAction;
  final void Function()? extraAction;

  @override
  Widget build(BuildContext context) => InkWell(
        onTap: userId.isNotEmpty && enableAction
            ? () {
                if (extraAction != null) {
                  extraAction!();
                }
                context.navigator.pushNamed(
                  Routes.messages,
                  arguments: <String, String>{
                    Keys.userId: userId,
                  },
                );
              }
            : null,
        child: CachedNetworkImageWidget(
          imageUrl: profileImage,
          placeholder: CircleAvatar(
            radius: size / 2,
            backgroundColor: context.themeData.primaryColor,
            backgroundImage: const AssetImage(
              AssetsPath.defaultAvatar,
            ),
          ),
          height: size,
          width: size,
          clipToCircle: true,
        ),
      );
}
