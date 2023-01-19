import 'dart:io';

import 'package:camerawesome/camerawesome_plugin.dart';
import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';
import 'package:whatsapp/app/app.dart';

class CameraOptionWidget extends StatelessWidget {
  const CameraOptionWidget({super.key});

  Future<String> _path(CaptureMode captureMode) async {
    final extDir = await getTemporaryDirectory();
    final statusDir =
        await Directory('${extDir.path}/status').create(recursive: true);
    final fileExtension = captureMode == CaptureMode.photo ? 'jpg' : 'mp4';
    final filePath =
        '${statusDir.path}/${DateTime.now().millisecondsSinceEpoch}.$fileExtension';
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
          context.navigator.pop(mediaCapture);
        },
        progressIndicator: const Center(
          child: CircularProgressIndicatorWidget(),
        ),
      );
}
