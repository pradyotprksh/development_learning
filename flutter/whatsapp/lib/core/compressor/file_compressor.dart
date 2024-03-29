import 'package:flutter_image_compress/flutter_image_compress.dart';
import 'package:pdf_manipulator/pdf_manipulator.dart';
import 'package:video_compress/video_compress.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';

abstract class FileCompressor {
  static Future<String> getCompressedImagePath(
    String rawFilePath,
  ) async {
    try {
      var result = await FlutterImageCompress.compressAndGetFile(
        rawFilePath,
        await DeviceUtilsMethods.getTempDirectoryPath(),
        quality: FirebaseRemoteConfigService.imageCompressionValue(),
      );

      return result?.path ?? rawFilePath;
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return rawFilePath;
    }
  }

  static Future<String> getCompressedVideoPath(
    String rawFilePath,
  ) async {
    try {
      final mediaInfo = await VideoCompress.compressVideo(
        rawFilePath,
        quality: VideoQuality.LowQuality,
        deleteOrigin: true,
        includeAudio: true,
      );
      return mediaInfo?.path ?? rawFilePath;
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return rawFilePath;
    }
  }

  static Future<String> tryAllCompression(
    String rawFilePath,
  ) async {
    try {
      final imageCompression = await getCompressedImagePath(rawFilePath);
      if (imageCompression == rawFilePath) {
        final videoCompression = await getCompressedVideoPath(rawFilePath);
        if (videoCompression == rawFilePath) {
          final compressedPdfPath = await PdfManipulator().pdfCompressor(
            params: PDFCompressorParams(
              pdfPath: rawFilePath,
              imageQuality: FirebaseRemoteConfigService.imageCompressionValue(),
              imageScale: 1,
            ),
          );
          if (compressedPdfPath == rawFilePath || compressedPdfPath == null) {
            // Add more compression mechanism
            return rawFilePath;
          } else {
            return compressedPdfPath;
          }
        } else {
          return videoCompression;
        }
      } else {
        return imageCompression;
      }
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return rawFilePath;
    }
  }
}
