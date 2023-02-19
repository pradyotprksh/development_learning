import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:image_picker/image_picker.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class MessageUtilsMethods {
  static Future<List<FileInformationDetails>?> showAttachmentOptionsBottomSheet(
    BuildContext context,
  ) async =>
      await showModalBottomSheet<List<FileInformationDetails>>(
        context: context,
        builder: (_) => const AttachmentOptionsWidget(),
      );

  static Future<void> showFileDetails(
    BuildContext context,
    FileInformationDetails fileInformationDetails,
  ) async {
    await showModalBottomSheet<List<FileInformationDetails>>(
      context: context,
      builder: (_) => Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          backgroundColor: Colors.transparent,
          leading: const CloseButton(),
          elevation: 0,
          title: Text(
            context.translator.details,
          ),
        ),
        body: ListView(
          primary: false,
          children: [
            if (!(fileInformationDetails.fileName.isNotEmpty &&
                fileInformationDetails.fileSize != 0 &&
                fileInformationDetails.fileType != null &&
                fileInformationDetails.fileType?.isNotEmpty == true))
              Padding(
                padding: ThemeEdgeInsets.all15,
                child: Text(context.translator.noFileDetails),
              ),
            if (fileInformationDetails.fileName.isNotEmpty)
              ListTile(
                title: Text(context.translator.localFileName),
                subtitle: Text(fileInformationDetails.fileName),
              ),
            if (fileInformationDetails.fileSize != 0)
              ListTile(
                title: Text(context.translator.fileSize),
                subtitle: Text(
                    '${fileInformationDetails.fileSize} ${context.translator.bytes}'),
              ),
            if (fileInformationDetails.fileType != null &&
                fileInformationDetails.fileType?.isNotEmpty == true)
              ListTile(
                title: Text(context.translator.fileType),
                subtitle: Text(fileInformationDetails.fileType ?? ''),
              ),
          ],
        ),
      ),
    );
  }

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
