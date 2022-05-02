import 'package:flutter/services.dart';

/// A assets class which will hold all the details related to assets like the
/// path and all.
///
/// Helpful in getting the paths from one place rather than pasting the same
/// path again and again everywhere.
class UtilsAssets {
  factory UtilsAssets() => _instance;

  UtilsAssets._privateConstructor();

  static final UtilsAssets _instance = UtilsAssets._privateConstructor();

  final clothesAnim = 'assets/lottie/clothes_anim.json';
  final loginFormId = 'assets/form/login_form.json';

  /// Get file data which is in json format, the path of the file will be
  /// stored in [filePath].
  Future<String> getJSONData(String filePath) async =>
      await rootBundle.loadString(filePath);
}
