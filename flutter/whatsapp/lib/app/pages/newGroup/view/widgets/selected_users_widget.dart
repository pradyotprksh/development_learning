import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class SelectedUsersWidget extends StatelessWidget {
  const SelectedUsersWidget({
    super.key,
    required this.selectedUserDetails,
    this.onDelete,
  });

  final List<UserDetails> selectedUserDetails;
  final void Function(UserDetails)? onDelete;

  @override
  Widget build(BuildContext context) => SizedBox(
        height: 45,
        child: ListView.builder(
          shrinkWrap: true,
          scrollDirection: Axis.horizontal,
          itemCount: selectedUserDetails.length,
          padding: ThemeEdgeInsets.left15,
          itemBuilder: (_, index) {
            final userDetail = selectedUserDetails[index];

            return Row(
              children: [
                Chip(
                  avatar: UserImageWidget(
                    profileImage: userDetail.profileImage ?? '',
                    currentMood: null,
                    userId: userDetail.userId,
                    isOnline: null,
                    enableAction: false,
                    useAvatarAsProfile: userDetail.useAvatarAsProfile,
                    avatarDetails: userDetail.avatarDetails,
                  ),
                  label: Text(
                    userDetail.name ?? '',
                  ),
                  onDeleted: onDelete != null
                      ? () {
                          onDelete?.call(userDetail);
                        }
                      : null,
                ),
                ThemeSizedBox.width15,
              ],
            );
          },
        ),
      );
}
