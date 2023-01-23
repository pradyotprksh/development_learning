import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:image_picker/image_picker.dart';
import 'package:whatsapp/app/app.dart';

class ImagePickerWidget extends StatelessWidget {
  ImagePickerWidget({
    super.key,
    required this.child,
    required this.image,
    required this.aspectRatioPresets,
    required this.cropStyle,
  });

  final ImagePicker _picker = ImagePicker();
  final Widget child;
  final void Function(String) image;
  final List<CropAspectRatioPreset> aspectRatioPresets;
  final CropStyle cropStyle;

  @override
  Widget build(BuildContext context) => GestureDetector(
        onTap: () async {
          final source = await ImagePickerUtils.selectPickerType(context);
          if (source != null) {
            // Pick file
            final file = await _picker.pickImage(source: source);
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
                    toolbarColor: context.themeData.appBarTheme.backgroundColor,
                    toolbarWidgetColor: context.themeData.iconTheme.color,
                    initAspectRatio: CropAspectRatioPreset.original,
                    lockAspectRatio: false,
                  ),
                  IOSUiSettings(
                    title: Constants.applicationName,
                  ),
                  WebUiSettings(
                    context: context,
                  ),
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
