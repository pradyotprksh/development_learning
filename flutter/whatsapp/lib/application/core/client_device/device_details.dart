import 'package:whatsapp/application/domain/domain.dart';

abstract class DeviceDetails {
  Future<UserDeviceDetails> getDeviceDetails();
}
