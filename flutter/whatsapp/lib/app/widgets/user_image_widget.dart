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
                extraAction?.call();
                context.navigator.pushNamed(
                  Routes.messages,
                  arguments: <String, String>{
                    Keys.userId: userId,
                  },
                );
              }
            : null,
        child: CachedNetworkImageWidget(
          tag: userId,
          imageUrl: profileImage,
          placeholder: CircleAvatar(
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
      );
}
