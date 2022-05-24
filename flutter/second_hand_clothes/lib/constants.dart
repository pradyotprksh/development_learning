/// A constant class for the whole project level.
class Constants {
  factory Constants() => _instance;

  Constants._privateConstructor();

  static final Constants _instance = Constants._privateConstructor();

  final appName = 'Clothes';
  final loginFormId = 'login_form';
  final signUpFormId = 'sign_up_form';

  final formStatusChangeDuration = const Duration(seconds: 1);
}