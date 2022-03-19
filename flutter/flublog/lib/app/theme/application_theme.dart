import 'package:enum_to_string/enum_to_string.dart';
import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';

/// A theme class to handle all the application theme related work.
abstract class ApplicationTheme {
  /// Get the theme for the application.
  ///
  /// [themeMode] : Current theme of the application, dark or light
  ///
  /// [currentFlexScheme] : Current flex scheme, which will be required
  /// while making the theme dynamic
  ///
  /// Return [ThemeData] with the theme details to be applied to the
  /// application.
  static ThemeData getTheme(ThemeMode themeMode, String currentFlexScheme) {
    final _currentFlexScheme = _getCurrentFlexScheme(currentFlexScheme);
    if (themeMode == ThemeMode.dark) {
      return _getDarkApplicationTheme(_currentFlexScheme);
    } else {
      return _getLightApplicationTheme(_currentFlexScheme);
    }
  }
}

/// [currentFlexScheme] is the current set scheme for the application, which
/// will be in String format.
///
/// It will be used to extract the enum corresponding value from [FlexScheme].
///
/// Return [FlexScheme] value as an Enum.
FlexScheme _getCurrentFlexScheme(String currentFlexScheme) =>
    EnumToString.fromString(FlexScheme.values, currentFlexScheme) ??
    FlexScheme.material;

/// Get the light [ThemeData] from the selected [currentFlexScheme].
ThemeData _getLightApplicationTheme(FlexScheme currentFlexScheme) =>
    FlexThemeData.dark(scheme: currentFlexScheme);

/// Get the dark [ThemeData] from the selected [currentFlexScheme].
ThemeData _getDarkApplicationTheme(FlexScheme currentFlexScheme) =>
    FlexThemeData.dark(scheme: currentFlexScheme);
