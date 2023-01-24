import 'package:whatsapp/device/device.dart';

abstract class CoreConstants {
  static const userCollection = 'users';
  static const statusCollection = 'status';
  static const statusSeenCollection = 'status/$statusIdPlaceholder/seen-by';
  static const userIdPlaceholder = '{userId}';
  static const statusIdPlaceholder = '{statusId}';
  static const enableEncryption = 'enableEncryption';
  static const imageCompressionValue = 'imageCompressionValue';
  static const isApplicationDown = 'isApplicationDown';
  static const userProfileImage =
      '$userCollection/profileImages/$userIdPlaceholder';
  static String userStatusImage() =>
      '$userCollection/status/$userIdPlaceholder-${DeviceUtilsMethods.getCurrentTimeStamp()}';
}
