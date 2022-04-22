import 'package:flutter/material.dart';

/// A paddings class for getting different kinds of edge insets used across the
/// application.
///
/// Helpful for making the widget code cleaner.
class ThemesEdgeInsets {
  factory ThemesEdgeInsets() => _instance;

  ThemesEdgeInsets._privateConstructor();

  static final ThemesEdgeInsets _instance =
      ThemesEdgeInsets._privateConstructor();

  EdgeInsets top20Bottom20 = const EdgeInsets.only(
    top: 20,
    bottom: 20,
  );
}
