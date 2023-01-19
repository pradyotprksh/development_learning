import 'package:flutter/material.dart';
import 'package:step_progress_indicator/step_progress_indicator.dart';
import 'package:whatsapp/app/app.dart';

class StatusProfileImageWidget extends StatelessWidget {
  const StatusProfileImageWidget({
    super.key,
    required this.profileImage,
    required this.totalStatusCount,
    required this.readStatusCount,
  });

  final String profileImage;
  final int totalStatusCount;
  final int readStatusCount;

  @override
  Widget build(BuildContext context) => SizedBox(
        height: 50,
        width: 50,
        child: Stack(
          alignment: Alignment.center,
          children: [
            CachedNetworkImageWidget(
              imageUrl: profileImage,
              placeholder: CircleAvatar(
                radius: 15,
                backgroundColor: context.themeData.primaryColor,
                backgroundImage: const AssetImage(
                  AssetsPath.defaultAvatar,
                ),
              ),
              height: 30,
              width: 30,
              clipToCircle: true,
            ),
            CircularStepProgressIndicator(
              totalSteps: totalStatusCount,
              currentStep: readStatusCount,
              roundedCap: (_, isSelected) => isSelected,
              stepSize: 1,
              customColor: (color) => context.themeData.primaryColor,
            ),
          ],
        ),
      );
}
