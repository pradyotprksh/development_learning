/// An abstract service class for making an device level call.
///
/// It will contain the calls needed to get the results.
abstract class DeviceService {
  const DeviceService();

  /// Save the image url in the device
  void saveUserImage(String url);

  /// Get current saved image
  String? getUserImage();
}
