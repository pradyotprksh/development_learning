abstract class AppUtilsMethods {
  static String invitationMessage(String appName) =>
      'Let\'s chat on $appName! It\'s a fast, simple, and secure app we can use to message and call each other for free. Get it at ';

  static String emailUrl(String appName, String emailAddress) =>
      'mailto:$emailAddress?subject=Join $appName!&body=${invitationMessage(appName)}';

  static String smsUrl(String phoneNumber, String body) =>
      'sms:$phoneNumber?body=$body';
}
