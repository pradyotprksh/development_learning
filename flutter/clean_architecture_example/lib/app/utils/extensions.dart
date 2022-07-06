import 'package:flutter/material.dart';

/// An extension on BuildContext which will be used to take out the
/// common functionality to this for better readability across the project.
extension BuildContextExtension on BuildContext {
  /// Get the theme data object, so instead of doing Theme.of(context) now
  /// only context.themeData() will be used.
  ThemeData themeData() => Theme.of(this);

  /// Get the Navigator from the context
  NavigatorState navigator() => Navigator.of(this);

  /// Get the MediaQuery from the context
  MediaQueryData mediaQuery() => MediaQuery.of(this);
}
