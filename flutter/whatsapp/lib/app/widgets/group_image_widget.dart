import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class GroupImageWidget extends StatelessWidget {
  const GroupImageWidget({
    super.key,
    required this.profileImage,
    required this.groupId,
    this.enableAction = true,
    this.size = 40,
    this.extraAction,
  });

  final String profileImage;
  final String groupId;
  final double size;
  final bool enableAction;
  final void Function()? extraAction;

  @override
  Widget build(BuildContext context) => InkWell(
        onTap: groupId.isNotEmpty && enableAction
            ? () {
                extraAction?.call();
                context.navigator.pushNamed(
                  Routes.groupMessages,
                  arguments: <String, String>{
                    Keys.groupId: groupId,
                  },
                );
              }
            : null,
        child: CachedNetworkImageWidget(
          tag: groupId,
          imageUrl: profileImage,
          placeholder: CircleAvatar(
            radius: size / 2,
            backgroundColor: context.themeData.primaryColor,
            child: Icon(
              Icons.group,
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
