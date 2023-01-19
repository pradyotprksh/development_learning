import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:whatsapp/app/app.dart';

class WhatsappApp extends StatelessWidget {
  const WhatsappApp({super.key});

  @override
  Widget build(BuildContext context) {
    AppDetails.setApplicationOrientation();

    return BlocBuilder<ThemeBloc, ThemeState>(
      builder: (_, themeState) =>
          BlocBuilder<LocalizationsBloc, LocalizationsState>(
        builder: (_, localizationsState) {
          AppUtilsMethods.setTimeAgoLocalMessage(
            localizationsState.currentLocale.languageCode,
            TimeAgoCustomMessage(context),
          );

          return MaterialApp(
            title: Constants.applicationName,
            themeMode: themeState.currentThemeMode,
            debugShowCheckedModeBanner: !AppDetails.isReleaseMode,
            theme: _getThemeData(
              themeState.currentLightFlexScheme,
              themeState.currentFontFamily,
              themeState.currentEnableMaterial3,
              false,
            ),
            highContrastTheme: _getThemeData(
              themeState.currentLightContrastFlexScheme,
              themeState.currentFontFamily,
              themeState.currentEnableMaterial3,
              false,
            ),
            darkTheme: _getThemeData(
              themeState.currentDarkFlexScheme,
              themeState.currentFontFamily,
              themeState.currentEnableMaterial3,
              true,
            ),
            highContrastDarkTheme: _getThemeData(
              themeState.currentDarkContrastFlexScheme,
              themeState.currentFontFamily,
              themeState.currentEnableMaterial3,
              true,
            ),
            localizationsDelegates:
                LocalizationsDetails.getLocalizationDelegates(),
            supportedLocales: LocalizationsDetails.getSupportedLocales(),
            locale: localizationsState.currentLocale,
            initialRoute: Routes.initialRoute,
            routes: Routes.routes,
          );
        },
      ),
    );
  }

  ThemeData? _getThemeData(
    FlexScheme scheme,
    String fontFamily,
    bool useMaterial3,
    bool isDark,
  ) {
    final theme = !isDark
        ? FlexThemeData.light(
            scheme: scheme,
            fontFamily: fontFamily,
            useMaterial3: useMaterial3,
            useMaterial3ErrorColors: useMaterial3,
            appBarElevation: 5,
          )
        : FlexThemeData.dark(
            scheme: scheme,
            fontFamily: fontFamily,
            useMaterial3: useMaterial3,
            useMaterial3ErrorColors: useMaterial3,
            appBarElevation: 5,
          );
    return theme.copyWith(
      textTheme: GoogleFonts.getTextTheme(
        fontFamily,
        theme.textTheme,
      ),
    );
  }
}
