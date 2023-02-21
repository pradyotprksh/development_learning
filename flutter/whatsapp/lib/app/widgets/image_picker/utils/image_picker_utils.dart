import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:image_picker/image_picker.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

enum PickerOptions {
  phone,
  userAvatarOption,
}

abstract class ImagePickerUtils {
  static final ImagePicker _picker = ImagePicker();

  static Future<PickerOptions?> selectSourceOption(
    BuildContext context,
  ) async {
    final option = await showModalBottomSheet<PickerOptions>(
      context: context,
      builder: (_) => Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          backgroundColor: Colors.transparent,
          leading: const CloseButton(),
          title: Text(
            context.translator.imagePickerTitle,
            style: context.themeData.appBarTheme.titleTextStyle,
          ),
          elevation: 0,
        ),
        body: ListView(
          children: [
            ...PickerOptions.values.map(
              (e) {
                switch (e) {
                  case PickerOptions.phone:
                    return ListTile(
                      title: Text(
                        context.translator.usePhone,
                        style: context.themeData.textTheme.titleLarge,
                      ),
                      onTap: () {
                        context.navigator.pop(e);
                      },
                    );
                  case PickerOptions.userAvatarOption:
                    return ListTile(
                      title: Text(
                        context.translator.useAvatar,
                        style: context.themeData.textTheme.titleLarge,
                      ),
                      onTap: () {
                        context.navigator.pop(e);
                      },
                    );
                }
              },
            ),
          ],
        ),
      ),
    );

    return option;
  }

  static Future<ImageSource?> selectPickerType(
    BuildContext context,
  ) async {
    final option = await showModalBottomSheet<ImageSource>(
      context: context,
      builder: (_) => Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          backgroundColor: Colors.transparent,
          leading: const CloseButton(),
          title: Text(
            context.translator.imagePickerTitle,
            style: context.themeData.appBarTheme.titleTextStyle,
          ),
          elevation: 0,
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

  static Future<XFile?> getImageBasedOnSource(ImageSource imageSource) async =>
      await _picker.pickImage(source: imageSource);

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
      compressQuality: FirebaseRemoteConfigService.imageCompressionValue(),
    );
    return croppedImage?.path;
  }
}
