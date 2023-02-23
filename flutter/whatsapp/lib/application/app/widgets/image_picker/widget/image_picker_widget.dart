import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:whatsapp/application/app/app.dart';

class ImagePickerWidget extends StatelessWidget {
  const ImagePickerWidget({
    super.key,
    required this.child,
    required this.image,
    required this.aspectRatioPresets,
    required this.cropStyle,
    this.showAvatarOption = false,
    this.avatarOptionSelected,
  });

  final Widget child;
  final void Function(String) image;
  final void Function(bool)? avatarOptionSelected;
  final List<CropAspectRatioPreset> aspectRatioPresets;
  final CropStyle cropStyle;
  final bool showAvatarOption;

  @override
  Widget build(BuildContext context) => GestureDetector(
        onTap: () async {
          if (showAvatarOption) {
            final fromSource = await ImagePickerUtils.selectSourceOption(
              context,
            );
            if (fromSource != null) {
              if (fromSource == PickerOptions.userAvatarOption) {
                avatarOptionSelected?.call(true);
              } else if (fromSource == PickerOptions.existingImage) {
                avatarOptionSelected?.call(false);
              } else {
                if (context.mounted) {
                  await _performPhoneOperation(context);
                }
              }
            }
          } else {
            if (context.mounted) {
              await _performPhoneOperation(context);
            }
          }
        },
        child: child,
      );

  Future<void> _performPhoneOperation(BuildContext context) async {
    if (context.mounted) {
      final webSettings = WebUiSettings(
        context: context,
      );
      final themeData = context.themeData;
      final source = await ImagePickerUtils.selectPickerType(context);
      if (source != null) {
        // Pick file
        final file = await ImagePickerUtils.getImageBasedOnSource(source);
        if (file != null) {
          // Crop the picked image
          final filePath = file.path;
          final croppedImage = await ImagePickerUtils.getCroppedImage(
            filePath,
            aspectRatioPresets,
            cropStyle,
            [
              AndroidUiSettings(
                toolbarTitle: AppConstants.applicationName,
                toolbarColor: themeData.appBarTheme.backgroundColor,
                toolbarWidgetColor: themeData.iconTheme.color,
                initAspectRatio: CropAspectRatioPreset.original,
                lockAspectRatio: false,
              ),
              IOSUiSettings(
                title: AppConstants.applicationName,
              ),
              webSettings,
            ],
          );
          if (croppedImage != null) {
            image(croppedImage);
          }
        }
      }
    }
  }
}
