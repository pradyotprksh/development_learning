/// A assets class which will hold all the details related to assets like the
/// path and all for the complete project to be used.
///
/// Helpful in getting the paths from one place rather than pasting the same
/// path again and again everywhere.
class Assets {
  factory Assets() => _instance;

  Assets._privateConstructor();

  static final Assets _instance = Assets._privateConstructor();

  final loginForm = 'assets/form/login_form.json';
  final signUpForm = 'assets/form/sign_up_form.json';
}
