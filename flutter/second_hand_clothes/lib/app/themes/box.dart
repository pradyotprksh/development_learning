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
}
