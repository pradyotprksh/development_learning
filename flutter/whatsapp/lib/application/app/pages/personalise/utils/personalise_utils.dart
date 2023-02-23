import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:whatsapp/application/app/app.dart';

abstract class PersonaliseUtils {
  static void handleUndoSnackBar(
    BuildContext context,
    String content,
    Function() onUndo,
  ) {
    context.replaceAndShowSnackBar(
      content,
      SnackBarAction(
        label: context.translator.undo,
        onPressed: onUndo,
        textColor: context.themeData.snackBarTheme.actionTextColor,
      ),
    );
  }

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
            backgroundColor: Colors.transparent,
            appBar: AppBar(
              backgroundColor: Colors.transparent,
              leading: const CloseButton(),
              title: Text(
                buildContext.translator.chooseThemeMode,
                style: buildContext.themeData.appBarTheme.titleTextStyle,
              ),
              elevation: 0,
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
            backgroundColor: Colors.transparent,
            appBar: AppBar(
              backgroundColor: Colors.transparent,
              leading: const CloseButton(),
              title: Text(
                buildContext.translator.chooseLanguage,
                style: buildContext.themeData.appBarTheme.titleTextStyle,
              ),
              elevation: 0,
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

  static void showFontFamilyModal(
    BuildContext buildContext,
    String currentFontFamily,
  ) async {
    final themeBloc = buildContext.read<ThemeBloc>();

    final fontFamily = await getFont(buildContext, currentFontFamily);

    themeBloc
      ..clearHistory()
      ..add(
        ChangeThemeEvent(fontFamily: fontFamily),
      );
  }

  static Future<String> getFont(
    BuildContext buildContext,
    String currentFontFamily,
  ) async =>
      await showModalBottomSheet<String>(
        context: buildContext,
        builder: (_) => Scaffold(
          backgroundColor: Colors.transparent,
          appBar: AppBar(
            backgroundColor: Colors.transparent,
            leading: const CloseButton(),
            title: Text(
              buildContext.translator.chooseFont,
              style: buildContext.themeData.appBarTheme.titleTextStyle,
            ),
            elevation: 0,
          ),
          body: ListView.builder(
            primary: false,
            itemCount: GoogleFonts.asMap().length,
            itemBuilder: (_, position) {
              final fontFamily = GoogleFonts.asMap().keys.toList()[position];

              return ListTile(
                title: Text(
                  fontFamily,
                  style: buildContext.themeData.textTheme.titleLarge?.copyWith(
                    fontFamily: GoogleFonts.getFont(fontFamily).fontFamily,
                  ),
                ),
                subtitle: currentFontFamily == fontFamily
                    ? Text(
                        buildContext.translator.currentlySelected,
                        style: buildContext.themeData.textTheme.titleSmall,
                      )
                    : null,
                onTap: () {
                  buildContext.navigator.pop(fontFamily);
                },
              );
            },
          ),
        ),
      ) ??
      currentFontFamily;
}
