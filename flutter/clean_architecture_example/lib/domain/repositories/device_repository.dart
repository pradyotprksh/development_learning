import 'package:pet_perfect_assignemnt/device/device.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// The domain layer repository for the device, this will fetch the data
/// from the other layer.
class DomainDeviceRepository extends DeviceService {
  final _deviceRepository = DeviceRepository();

  @override
  void saveUserImage(String url) {
    _deviceRepository.saveUserImage(url);
  }

  @override
  String? getUserImage() => _deviceRepository.getUserImage();
}