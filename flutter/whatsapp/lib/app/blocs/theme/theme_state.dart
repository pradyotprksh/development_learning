import 'package:equatable/equatable.dart';
import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class ThemeState extends Equatable {
  const ThemeState({
    this.currentThemeMode = ThemeMode.system,
    this.currentLightFlexScheme = FlexScheme.material,
    this.currentLightContrastFlexScheme = FlexScheme.materialHc,
    this.currentDarkFlexScheme = FlexScheme.material,
    this.currentDarkContrastFlexScheme = FlexScheme.materialHc,
    this.currentFontFamily = Constants.defaultFontFamily,
    this.currentEnableMaterial3 = true,
  });

  ThemeState copyWith({
    ThemeMode? themeMode,
    FlexScheme? lightFlexScheme,
    FlexScheme? lightContrastFlexScheme,
    FlexScheme? darkFlexScheme,
    FlexScheme? darkContrastFlexScheme,
    String? fontFamily,
    bool? enableMaterial3,
  }) =>
      ThemeState(
        currentThemeMode: themeMode ?? currentThemeMode,
        currentLightFlexScheme: lightFlexScheme ?? currentLightFlexScheme,
        currentLightContrastFlexScheme:
            lightContrastFlexScheme ?? currentLightContrastFlexScheme,
        currentDarkFlexScheme: darkFlexScheme ?? currentDarkFlexScheme,
        currentDarkContrastFlexScheme:
            darkContrastFlexScheme ?? currentDarkContrastFlexScheme,
        currentFontFamily: fontFamily ?? currentFontFamily,
        currentEnableMaterial3: enableMaterial3 ?? currentEnableMaterial3,
      );

  final ThemeMode currentThemeMode;
  final FlexScheme currentLightFlexScheme;
  final FlexScheme currentLightContrastFlexScheme;
  final FlexScheme currentDarkFlexScheme;
  final FlexScheme currentDarkContrastFlexScheme;
  final String currentFontFamily;
  final bool currentEnableMaterial3;

  @override
  List<Object?> get props => [
        currentThemeMode,
        currentLightFlexScheme,
        currentLightContrastFlexScheme,
        currentDarkFlexScheme,
        currentDarkContrastFlexScheme,
        currentFontFamily,
        currentEnableMaterial3,
      ];
}
