import 'package:flutter/material.dart';
import 'package:step_progress_indicator/step_progress_indicator.dart';
import 'package:whatsapp/app/app.dart';

class StatusProfileImageWidget extends StatelessWidget {
  const StatusProfileImageWidget({
    super.key,
    required this.profileImage,
    required this.totalStatusCount,
    required this.readStatusCount,
    required this.userId,
    required this.currentMode,
    required this.isOnline,
    required this.useAvatarAsProfile,
    this.avatarDetails,
  });

  final String profileImage;
  final int totalStatusCount;
  final int readStatusCount;
  final String userId;
  final String? currentMode;
  final bool? isOnline;
  final bool useAvatarAsProfile;
  final String? avatarDetails;

  @override
  Widget build(BuildContext context) => SizedBox(
        height: 50,
        width: 50,
        child: Stack(
          alignment: Alignment.center,
          children: [
            UserImageWidget(
              profileImage: profileImage,
              userId: userId,
              currentMood: currentMode,
              size: 40,
              isOnline: isOnline,
              enableAction: false,
              useAvatarAsProfile: useAvatarAsProfile,
              avatarDetails: avatarDetails,
            ),
            if (totalStatusCount > 0)
              CircularStepProgressIndicator(
                totalSteps: totalStatusCount,
                currentStep: readStatusCount,
                roundedCap: (_, isSelected) => isSelected,
                stepSize: 2,
                selectedColor: context.themeData.primaryColor,
                unselectedColor: context.themeData.disabledColor,
              ),
          ],
        ),
      );
}
