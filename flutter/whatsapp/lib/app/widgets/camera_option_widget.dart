import 'package:camerawesome/camerawesome_plugin.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class FileDetails {
  FileDetails({
    required this.path,
    required this.editedFilePath,
    required this.isImage,
    required this.isVideo,
  });

  FileDetails copyWith(
    String croppedImagePath,
  ) =>
      FileDetails(
        path: path,
        editedFilePath: croppedImagePath,
        isImage: isImage,
        isVideo: isVideo,
      );

  final String path;
  final String editedFilePath;
  final bool isImage;
  final bool isVideo;
}

class CameraOptionWidget extends StatelessWidget {
  const CameraOptionWidget({
    super.key,
    required this.onMediaSelected,
  });

  final void Function(FileDetails) onMediaSelected;

  Future<String> _path(CaptureMode captureMode) async {
    final tempPath = await AppUtilsMethods.getTempDirectory('status');
    final fileExtension = captureMode == CaptureMode.photo ? 'jpg' : 'mp4';
    final filePath =
        '$tempPath}/${DateTime.now().millisecondsSinceEpoch}.$fileExtension';
    return filePath;
  }

  @override
  Widget build(BuildContext context) => CameraAwesomeBuilder.awesome(
        saveConfig: SaveConfig.photoAndVideo(
          photoPathBuilder: () => _path(CaptureMode.photo),
          videoPathBuilder: () => _path(CaptureMode.video),
          initialCaptureMode: CaptureMode.photo,
        ),
        flashMode: FlashMode.auto,
        aspectRatio: CameraAspectRatios.ratio_16_9,
        onMediaTap: (mediaCapture) {
          onMediaSelected(
            FileDetails(
              path: mediaCapture.filePath,
              editedFilePath: mediaCapture.filePath,
              isImage: mediaCapture.isPicture,
              isVideo: mediaCapture.isVideo,
            ),
          );
        },
        progressIndicator: const Center(
          child: CircularProgressIndicatorWidget(),
        ),
      );
}
