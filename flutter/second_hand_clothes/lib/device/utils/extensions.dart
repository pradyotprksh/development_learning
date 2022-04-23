import 'package:flutter/material.dart';

/// An extension on BuildContext which will be used to take out the
/// common functionality to this for better readability across the project.
extension BuildContextExtension on BuildContext {
  /// Returns true if the user phone is in dark mode otherwise false.
  bool isPhoneInDarkMode() =>
      MediaQuery.of(this).platformBrightness == Brightness.light;
}
