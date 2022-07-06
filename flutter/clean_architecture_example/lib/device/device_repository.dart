import 'package:pet_perfect_assignemnt/device/device.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// The repository class for [DeviceService] which will implement
/// the functionality in the device layer
class DeviceRepository extends DeviceService {
  @override
  void saveUserImage(String url) {
    HiveClient().hiveBox.put(DeviceConstants.userImage, url);
  }

  @override
  String? getUserImage() => HiveClient().hiveBox.get(DeviceConstants.userImage);
}
