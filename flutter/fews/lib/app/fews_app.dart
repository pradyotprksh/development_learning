import 'package:fews/app/app.dart';
import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class FewsApp extends StatelessWidget {
  const FewsApp({super.key});

  @override
  Widget build(BuildContext context) {
    AppDetails.setApplicationOrientation();

    return MaterialApp(
      title: AppConstants.applicationName,
      themeMode: ThemeMode.system,
      theme: _getThemeData(false),
      darkTheme: _getThemeData(true),
      localizationsDelegates: LocalizationsDetails.getLocalizationDelegates(),
      supportedLocales: LocalizationsDetails.getSupportedLocales(),
      locale: const Locale(AppConstants.defaultLanguage, ''),
    );
  }

  ThemeData? _getThemeData(
    bool isDark,
  ) {
    final theme = !isDark
        ? FlexThemeData.light(
            scheme: FlexScheme.mandyRed,
            fontFamily: AppConstants.defaultFontFamily,
            useMaterial3: true,
            useMaterial3ErrorColors: true,
            appBarElevation: 5,
          )
        : FlexThemeData.dark(
            scheme: FlexScheme.mandyRed,
            fontFamily: AppConstants.defaultFontFamily,
            useMaterial3: true,
            useMaterial3ErrorColors: true,
            appBarElevation: 5,
          );
    return theme.copyWith(
      textTheme: GoogleFonts.getTextTheme(
        AppConstants.defaultFontFamily,
        theme.textTheme,
      ),
    );
  }
}
