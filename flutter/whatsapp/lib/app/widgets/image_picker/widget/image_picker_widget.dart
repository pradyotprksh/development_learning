import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:whatsapp/app/app.dart';

class ImagePickerWidget extends StatelessWidget {
  const ImagePickerWidget({
    super.key,
    required this.child,
    required this.image,
    required this.aspectRatioPresets,
    required this.cropStyle,
  });

  final Widget child;
  final void Function(String) image;
  final List<CropAspectRatioPreset> aspectRatioPresets;
  final CropStyle cropStyle;

  @override
  Widget build(BuildContext context) => GestureDetector(
        onTap: () async {
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
                    toolbarTitle: Constants.applicationName,
                    toolbarColor: themeData.appBarTheme.backgroundColor,
                    toolbarWidgetColor: themeData.iconTheme.color,
                    initAspectRatio: CropAspectRatioPreset.original,
                    lockAspectRatio: false,
                  ),
                  IOSUiSettings(
                    title: Constants.applicationName,
                  ),
                  webSettings,
                ],
              );
              if (croppedImage != null) {
                image(croppedImage);
              }
            }
          }
        },
        child: child,
      );
}
