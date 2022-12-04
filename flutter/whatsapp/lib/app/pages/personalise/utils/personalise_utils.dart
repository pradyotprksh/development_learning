import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

abstract class PersonaliseUtils {
  static IconData getThemeIcon(ThemeMode currentThemeMode) {
    switch (currentThemeMode) {
      case ThemeMode.system:
        return Icons.phone_android;
      case ThemeMode.light:
        return Icons.light_mode;
      case ThemeMode.dark:
        return Icons.dark_mode;
    }
  }

  static ThemeData getThemeData(
    bool isPhoneInDarkMode,
    ThemeMode themeMode,
    FlexScheme flexScheme,
  ) {
    switch (themeMode) {
      case ThemeMode.system:
        if (isPhoneInDarkMode) {
          return FlexThemeData.dark(scheme: flexScheme);
        } else {
          return FlexThemeData.light(scheme: flexScheme);
        }
      case ThemeMode.light:
        return FlexThemeData.light(scheme: flexScheme);
      case ThemeMode.dark:
        return FlexThemeData.dark(scheme: flexScheme);
    }
  }

  static ChangeThemeEvent getThemeChangeEvent(
    bool isPhoneInDarkMode,
    ThemeMode themeMode,
    FlexScheme flexScheme,
  ) {
    switch (themeMode) {
      case ThemeMode.system:
        if (isPhoneInDarkMode) {
          return ChangeThemeEvent(
            darkFlexScheme: flexScheme.name,
          );
        } else {
          return ChangeThemeEvent(
            lightFlexScheme: flexScheme.name,
          );
        }
      case ThemeMode.light:
        return ChangeThemeEvent(
          lightFlexScheme: flexScheme.name,
        );
      case ThemeMode.dark:
        return ChangeThemeEvent(
          darkFlexScheme: flexScheme.name,
        );
    }
  }

  static FlexScheme getCurrentSelectedFlexScheme(
    bool isPhoneInDarkMode,
    ThemeMode themeMode,
    FlexScheme lightColorScheme,
    FlexScheme darkColorScheme,
  ) {
    switch (themeMode) {
      case ThemeMode.system:
        if (isPhoneInDarkMode) {
          return darkColorScheme;
        } else {
          return lightColorScheme;
        }
      case ThemeMode.light:
        return lightColorScheme;
      case ThemeMode.dark:
        return darkColorScheme;
    }
  }

  static void showThemeModeModal(
    BuildContext buildContext,
    ThemeMode currentThemeMode,
  ) async {
    final themeBloc = buildContext.read<ThemeBloc>();

    final themeMode = await showModalBottomSheet<ThemeMode>(
          context: buildContext,
          builder: (_) => Scaffold(
            backgroundColor:
                buildContext.themeData.bottomSheetTheme.backgroundColor,
            appBar: AppBar(
              backgroundColor:
                  buildContext.themeData.appBarTheme.backgroundColor,
              leading: const CloseButton(),
              title: Text(
                buildContext.translator.chooseThemeMode,
                style: buildContext.themeData.appBarTheme.titleTextStyle,
              ),
              elevation: buildContext.themeData.appBarTheme.elevation,
            ),
            body: ListView(
              children: [
                ...ThemeMode.values.map(
                  (e) => RadioListTile(
                    title: Text(
                      e.name.capitalize,
                      style: buildContext.themeData.textTheme.titleLarge,
                    ),
                    value: e,
                    groupValue: currentThemeMode,
                    onChanged: (value) {
                      buildContext.navigator.pop(value);
                    },
                  ),
                ),
              ],
            ),
          ),
        ) ??
        currentThemeMode;

    themeBloc
      ..clearHistory()
      ..add(
        ChangeThemeEvent(themeMode: themeMode.name),
      );
  }

  static void showLanguageModal(
    BuildContext buildContext,
    String currentLanguage,
  ) async {
    final localizationBloc = buildContext.read<LocalizationsBloc>();

    final language = await showModalBottomSheet<String>(
          context: buildContext,
          builder: (_) => Scaffold(
            backgroundColor:
                buildContext.themeData.bottomSheetTheme.backgroundColor,
            appBar: AppBar(
              backgroundColor:
                  buildContext.themeData.appBarTheme.backgroundColor,
              leading: const CloseButton(),
              title: Text(
                buildContext.translator.chooseThemeMode,
                style: buildContext.themeData.appBarTheme.titleTextStyle,
              ),
              elevation: buildContext.themeData.appBarTheme.elevation,
            ),
            body: ListView(
              children: [
                ...LocalizationsDetails.getSupportedLocales().map(
                  (e) => RadioListTile(
                    value: e.languageCode,
                    groupValue: currentLanguage,
                    onChanged: (value) {
                      buildContext.navigator.pop(value);
                    },
                    title: Text(
                      LocalizationsDetails.getSupportedLanguage()[
                              e.languageCode] ??
                          '',
                      style: buildContext.themeData.textTheme.titleLarge,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ) ??
        currentLanguage;

    localizationBloc
      ..clearHistory()
      ..add(
        ChangeLocalizationEvent(newLocale: language),
      );
  }
}
