import 'package:whatsapp/device/device.dart';

abstract class CoreConstants {
  static const userCollection = 'users';
  static const statusCollection = 'status';
  static const userIdPlaceholder = '{userId}';
  static const enableEncryption = 'enableEncryption';
  static const isApplicationDown = 'isApplicationDown';
  static const userProfileImage =
      '$userCollection/profileImages/$userIdPlaceholder';
  static String userStatusImage() =>
      '$userCollection/status/$userIdPlaceholder-${DeviceUtilsMethods.getCurrentTimeStamp()}';
}
