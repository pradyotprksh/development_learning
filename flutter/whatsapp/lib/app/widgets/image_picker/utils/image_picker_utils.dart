import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:image_picker/image_picker.dart';
import 'package:whatsapp/app/app.dart';

abstract class ImagePickerUtils {
  static Future<ImageSource?> selectPickerType(
    BuildContext context,
  ) async {
    final option = await showModalBottomSheet<ImageSource>(
      context: context,
      builder: (_) => Scaffold(
        backgroundColor: context.themeData.bottomSheetTheme.backgroundColor,
        appBar: AppBar(
          backgroundColor: context.themeData.appBarTheme.backgroundColor,
          leading: const CloseButton(),
          title: Text(
            context.translator.imagePickerTitle,
            style: context.themeData.appBarTheme.titleTextStyle,
          ),
          elevation: context.themeData.appBarTheme.elevation,
        ),
        body: ListView(
          children: [
            ...ImageSource.values.map(
              (e) => ListTile(
                title: Text(
                  e.name.capitalize,
                  style: context.themeData.textTheme.titleLarge,
                ),
                onTap: () {
                  context.navigator.pop(e);
                },
              ),
            ),
          ],
        ),
      ),
    );

    return option;
  }

  static Future<String?> getCroppedImage(
    String filePath,
    List<CropAspectRatioPreset>? aspectRatioPresets,
    CropStyle? cropStyle,
    List<PlatformUiSettings> platformSettings,
  ) async {
    final croppedImage = await ImageCropper().cropImage(
      sourcePath: filePath,
      aspectRatioPresets: aspectRatioPresets ??
          const [
            CropAspectRatioPreset.original,
            CropAspectRatioPreset.square,
            CropAspectRatioPreset.ratio3x2,
            CropAspectRatioPreset.ratio4x3,
            CropAspectRatioPreset.ratio16x9
          ],
      cropStyle: cropStyle ?? CropStyle.rectangle,
      uiSettings: platformSettings,
      compressQuality: 60,
    );
    return croppedImage?.path;
  }
}
