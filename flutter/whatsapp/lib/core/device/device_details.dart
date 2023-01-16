import 'package:whatsapp/domain/domain.dart';

abstract class DeviceDetails {
  Future<UserDeviceDetails> getDeviceDetails();
}
