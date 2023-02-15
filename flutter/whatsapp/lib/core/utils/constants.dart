import 'package:whatsapp/device/device.dart';

abstract class CoreConstants {
  static const userCollection = 'users';
  static const directMessageCollection = 'direct-messages';
  static const groupMessageCollection = 'group-messages';
  static const securityDetailsCollection = 'security-details';
  static const screenshotCollection =
      '$securityDetailsCollection/$userIdPlaceholder/screenshots';
  static const messageCopyForwardedSavedCollection =
      '$securityDetailsCollection/$userIdPlaceholder/$messageCopyForwardedSavedEndpointPlaceholder';
  static const messageCopyForwardedSavedEndpointPlaceholder =
      'messageCopyForwardedSaved';
  static const messageCopied = 'message-copied';
  static const messageForwarded = 'message-forwarded';
  static const messageSaved = 'message-saved';
  static const callsCollection = 'users/$userIdPlaceholder/calls';
  static const loginHistoryCollection =
      '$userCollection/$userIdPlaceholder/login-history';
  static const contactsAvailableCollection =
      '$userCollection/$userIdPlaceholder/contacts-available';
  static const contactsNotAvailableCollection =
      '$userCollection/$userIdPlaceholder/contacts-not-available';
  static const directMessagesCollection =
      '$directMessageCollection/$messageIdPlaceholder/messages';
  static const groupMessagesCollection =
      '$groupMessageCollection/$messageIdPlaceholder/messages';
  static const statusCollection = 'status';
  static const statusSeenCollection =
      '$statusCollection/$statusIdPlaceholder/seen-by';
  static const userIdPlaceholder = '{userId}';
  static const statusIdPlaceholder = '{statusId}';
  static const messageIdPlaceholder = '{message}';
  static const enableEncryption = 'enableEncryption';
  static const imageCompressionValue = 'imageCompressionValue';
  static const statusDeleteTimeValue = 'statusDeleteTimeValue';
  static const pinConfirmationTimeValue = 'pinConfirmationTimeValue';
  static const isApplicationDown = 'isApplicationDown';
  static const userProfileImage =
      '$userCollection/profileImages/$userIdPlaceholder';
  static String userStatusImage() =>
      '$userCollection/status/$userIdPlaceholder-${DeviceUtilsMethods.getCurrentTimeStamp()}';
  static String groupProfileImage() =>
      'groups/profileImages/$userIdPlaceholder-${DeviceUtilsMethods.getCurrentTimeStamp()}';
}
