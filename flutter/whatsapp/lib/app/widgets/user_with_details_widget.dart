import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class UserWithDetailsWidget extends StatelessWidget {
  const UserWithDetailsWidget({
    super.key,
    required this.userDetail,
  });

  final UserDetails userDetail;

  @override
  Widget build(BuildContext context) => ListTile(
        onTap: () {
          context.navigator.pushNamed(
            Routes.messages,
            arguments: <String, String>{
              Keys.userId: userDetail.userId,
            },
          );
        },
        leading: UserImageWidget(
          profileImage: userDetail.profileImage ?? '',
          userId: userDetail.userId,
          currentMood: userDetail.currentMood,
          isOnline: userDetail.isOnline,
          enableAction: false,
          useAvatarAsProfile: userDetail.useAvatarAsProfile,
          avatarDetails: userDetail.avatarDetails,
        ),
        title: Text(
          userDetail.name ?? '',
        ),
        subtitle: Text('${userDetail.phoneNumber} | ${userDetail.emailId}'),
      );
}
