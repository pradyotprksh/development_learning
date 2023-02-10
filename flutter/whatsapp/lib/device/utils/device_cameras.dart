import 'package:camera/camera.dart';

abstract class DeviceCameras {
  static late List<CameraDescription> cameras;

  static Future getCameras() async {
    cameras = await availableCameras();
  }

  static CameraDescription getCamera() {
    if (cameras.length > 1) {
      return cameras[1];
    }
    return cameras[0];
  }
}
