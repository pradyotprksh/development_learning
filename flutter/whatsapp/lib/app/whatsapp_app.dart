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
        builder: (_, localizationsState) => MaterialApp(
          title: Constants.applicationName,
          themeMode: themeState.currentThemeMode,
          debugShowCheckedModeBanner: !AppDetails.isReleaseMode,
          theme: FlexThemeData.light(
            scheme: themeState.currentLightFlexScheme,
            fontFamily: themeState.currentFontFamily,
            textTheme: GoogleFonts.getTextTheme(themeState.currentFontFamily),
          ),
          highContrastTheme: FlexThemeData.light(
            scheme: themeState.currentLightContrastFlexScheme,
            fontFamily: themeState.currentFontFamily,
            textTheme: GoogleFonts.getTextTheme(themeState.currentFontFamily),
          ),
          darkTheme: FlexThemeData.dark(
            scheme: themeState.currentDarkFlexScheme,
            fontFamily: themeState.currentFontFamily,
            textTheme: GoogleFonts.getTextTheme(themeState.currentFontFamily),
          ),
          highContrastDarkTheme: FlexThemeData.dark(
            scheme: themeState.currentDarkContrastFlexScheme,
            fontFamily: themeState.currentFontFamily,
            textTheme: GoogleFonts.getTextTheme(themeState.currentFontFamily),
          ),
          localizationsDelegates:
              LocalizationsDetails.getLocalizationDelegates(),
          supportedLocales: LocalizationsDetails.getSupportedLocales(),
          locale: localizationsState.currentLocale,
          initialRoute: Routes.initialRoute,
          routes: Routes.routes,
        ),
      ),
    );
  }
}
