import 'package:camera/camera.dart';
import 'package:whatsapp/app/app.dart';

abstract class DeviceCameras {
  static List<CameraDescription>? cameras;

  static Future getCameras() async {
    if (!AppDetails.isWeb) {
      cameras = await availableCameras();
    }
  }

  static CameraDescription? getFrontCamera() {
    if (cameras != null && (cameras?.length ?? 0) > 0) {
      if (cameras!.length > 1) {
        return cameras![1];
      }
      return cameras![0];
    }
    return null;
  }

  static CameraDescription? getBackCamera() => cameras?[0];
}
