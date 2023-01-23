import 'package:flutter_image_compress/flutter_image_compress.dart';
import 'package:video_compress/video_compress.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';

abstract class FileCompressor {
  static Future<String?> getCompressedImagePath(
    String rawFilePath,
  ) async {
    try {
      var result = await FlutterImageCompress.compressAndGetFile(
        rawFilePath,
        await DeviceUtilsMethods.getTempDirectoryPath(),
        quality: 60,
      );

      return result?.path;
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return rawFilePath;
    }
  }

  static Future<String?> getCompressedVideoPath(
    String rawFilePath,
  ) async {
    try {
      final mediaInfo = await VideoCompress.compressVideo(
        rawFilePath,
        quality: VideoQuality.LowQuality,
        deleteOrigin: true,
        includeAudio: true,
      );
      return mediaInfo?.path;
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return rawFilePath;
    }
  }
}
