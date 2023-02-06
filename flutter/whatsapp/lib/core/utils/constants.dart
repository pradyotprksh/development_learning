import 'package:whatsapp/device/device.dart';

abstract class CoreConstants {
  static const userCollection = 'users';
  static const directMessageCollection = 'direct-messages';
  static const loginHistoryCollection =
      'users/$userIdPlaceholder/login-history';
  static const contactsAvailableCollection =
      'users/$userIdPlaceholder/contacts-available';
  static const contactsNotAvailableCollection =
      'users/$userIdPlaceholder/contacts-not-available';
  static const messagesCollection =
      'direct-messages/$messageIdPlaceholder/messages';
  static const statusCollection = 'status';
  static const statusSeenCollection = 'status/$statusIdPlaceholder/seen-by';
  static const userIdPlaceholder = '{userId}';
  static const statusIdPlaceholder = '{statusId}';
  static const messageIdPlaceholder = '{message}';
  static const enableEncryption = 'enableEncryption';
  static const imageCompressionValue = 'imageCompressionValue';
  static const isApplicationDown = 'isApplicationDown';
  static const userProfileImage =
      '$userCollection/profileImages/$userIdPlaceholder';
  static String userStatusImage() =>
      '$userCollection/status/$userIdPlaceholder-${DeviceUtilsMethods.getCurrentTimeStamp()}';
}
