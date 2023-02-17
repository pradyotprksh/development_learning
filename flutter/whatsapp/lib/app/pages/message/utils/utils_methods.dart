import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:image_picker/image_picker.dart';
import 'package:whatsapp/app/app.dart';

abstract class MessageUtilsMethods {
  static Future<List<FileInformation>?> showAttachmentOptionsBottomSheet(
    BuildContext context,
  ) async =>
      await showModalBottomSheet<List<FileInformation>>(
        context: context,
        builder: (_) => const AttachmentOptionsWidget(),
      );

  static Future<String?> startCameraFilePicker(BuildContext context) async {
    final webSettings = WebUiSettings(
      context: context,
    );
    final themeData = context.themeData;
    final file = await ImagePickerUtils.getImageBasedOnSource(
      ImageSource.camera,
    );
    if (file != null) {
      final filePath = file.path;
      return await getCroppedImagePath(webSettings, themeData, filePath);
    }
    return null;
  }

  static Future<FilePickerResult?> pickFileFromStorage() async =>
      await FilePicker.platform.pickFiles(
        allowMultiple: true,
      );

  static Future<String?> startGalleryFilePicker(BuildContext context) async {
    final webSettings = WebUiSettings(
      context: context,
    );
    final themeData = context.themeData;
    final file = await ImagePickerUtils.getImageBasedOnSource(
      ImageSource.gallery,
    );
    if (file != null) {
      final filePath = file.path;
      return await getCroppedImagePath(webSettings, themeData, filePath);
    }
    return null;
  }

  static Future<String?> getCroppedImagePath(
    WebUiSettings webSettings,
    ThemeData themeData,
    String filePath,
  ) async {
    final croppedImage = await ImagePickerUtils.getCroppedImage(
      filePath,
      null,
      null,
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
    return croppedImage ?? filePath;
  }
}
