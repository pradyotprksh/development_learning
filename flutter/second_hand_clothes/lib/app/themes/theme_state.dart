import 'package:equatable/equatable.dart';
import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';

/// A theme state for the application. This will contain the current
/// application theme details.
///
/// And if any change is required in the theme will be done by changing the
/// values assigned in this.
class ThemeState extends Equatable {
  /// [currentThemeMode] = Is the current theme mode of the application. Values
  /// can be checked in [ThemeMode].
  ///
  /// [currentLightFlexScheme] = It is the color scheme for the light theme,
  /// values can be checked in [FlexColorScheme]
  ///
  /// [currentLightContrastFlexScheme] = It is the color scheme for the light
  /// contrast theme, values can be checked in [FlexColorScheme]
  ///
  /// [currentDarkFlexScheme] = It is the color scheme for the dark theme,
  /// values can be checked in [FlexColorScheme]
  ///
  /// [currentDarkContrastFlexScheme] = It is the color scheme for the dark
  /// contrast theme, values can be checked in [FlexColorScheme]
  const ThemeState({
    this.currentThemeMode = ThemeMode.system,
    this.currentLightFlexScheme = FlexScheme.material,
    this.currentLightContrastFlexScheme = FlexScheme.materialHc,
    this.currentDarkFlexScheme = FlexScheme.material,
    this.currentDarkContrastFlexScheme = FlexScheme.materialHc,
  });

  ThemeState copyWith({
    ThemeMode? themeMode,
    FlexScheme? lightFlexScheme,
    FlexScheme? lightContrastFlexScheme,
    FlexScheme? darkFlexScheme,
    FlexScheme? darkContrastFlexScheme,
  }) =>
      ThemeState(
        currentThemeMode: themeMode ?? currentThemeMode,
        currentLightFlexScheme: lightFlexScheme ?? currentLightFlexScheme,
        currentLightContrastFlexScheme:
            lightContrastFlexScheme ?? currentLightContrastFlexScheme,
        currentDarkFlexScheme: darkFlexScheme ?? currentDarkFlexScheme,
        currentDarkContrastFlexScheme:
            darkContrastFlexScheme ?? currentDarkContrastFlexScheme,
      );

  final ThemeMode currentThemeMode;
  final FlexScheme currentLightFlexScheme;
  final FlexScheme currentLightContrastFlexScheme;
  final FlexScheme currentDarkFlexScheme;
  final FlexScheme currentDarkContrastFlexScheme;

  @override
  List<Object?> get props => [
        currentThemeMode,
        currentLightFlexScheme,
        currentLightContrastFlexScheme,
        currentDarkFlexScheme,
        currentDarkContrastFlexScheme,
      ];
}
