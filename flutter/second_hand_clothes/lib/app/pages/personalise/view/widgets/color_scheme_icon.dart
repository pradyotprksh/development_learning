import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A widget for scheme color icon, so it will return an icon widget
/// with the color as per the current color scheme selected by the user.
class WidgetsColorSchemeIcon extends StatelessWidget {
  /// [currentThemeMode] = Theme mode currently selected
  ///
  /// [currentLightFlexScheme] = Light mode color scheme currently selected
  ///
  /// [currentDarkFlexScheme] = Dark mode color scheme currently selected
  const WidgetsColorSchemeIcon({
    required this.currentThemeMode,
    required this.currentLightFlexScheme,
    required this.currentDarkFlexScheme,
    Key? key,
  }) : super(key: key);

  final ThemeMode currentThemeMode;
  final FlexScheme currentLightFlexScheme;
  final FlexScheme currentDarkFlexScheme;

  // TODO: Fix the icon color change issue in dark mode

  @override
  Widget build(BuildContext context) => Icon(
        Icons.color_lens,
        color: _getColorSchemeColor(context.isPhoneInDarkMode()),
      );

  /// Get color of the icon as per the theme and selected color scheme
  Color _getColorSchemeColor(bool isPhoneInDarkMode) {
    switch (currentThemeMode) {
      case ThemeMode.system:
        if (!isPhoneInDarkMode) {
          return _getLightColorSchemeIcon(currentLightFlexScheme);
        } else {
          return _getDarkColorSchemeIcon(currentDarkFlexScheme);
        }
      case ThemeMode.light:
        return _getLightColorSchemeIcon(currentLightFlexScheme);
      case ThemeMode.dark:
        return _getDarkColorSchemeIcon(currentDarkFlexScheme);
    }
  }

  /// Get light scheme color
  Color _getLightColorSchemeIcon(FlexScheme currentLightFlexScheme) =>
      FlexThemeData.light(
        scheme: currentLightFlexScheme,
      ).primaryColor;

  /// Get dark scheme color
  Color _getDarkColorSchemeIcon(FlexScheme currentDarkFlexScheme) =>
      FlexThemeData.dark(
        scheme: currentLightFlexScheme,
      ).primaryColor;
}
