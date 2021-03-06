import 'package:flutter/material.dart';

/// A box class for getting different kinds of boxes used across the
/// application.
///
/// Helpful for making the widget code cleaner.
class ThemesBox {
  factory ThemesBox() => _instance;

  ThemesBox._privateConstructor();

  static final ThemesBox _instance = ThemesBox._privateConstructor();

  SizedBox height15 = const SizedBox(height: 15);

  SizedBox height20 = const SizedBox(height: 20);

  SizedBox height50 = const SizedBox(height: 50);

  SizedBox height30 = const SizedBox(height: 30);

  SizedBox width15 = const SizedBox(width: 15);

  SizedBox shrink = const SizedBox.shrink();

  SizedBox box({
    double? height = 0.0,
    double? width = 0.0,
    Key? key,
  }) =>
      SizedBox(
        key: key,
        height: height,
        width: width,
      );
}
