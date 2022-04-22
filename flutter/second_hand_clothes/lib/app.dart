import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/constants.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// An App stateful widget, which will be the parent of all the widgets.
///
/// Here we will initialize and manage all the services and operations which
/// are required before the application starts and is ready for the user to be
/// used.
///
/// We will also set the theme and localization of the application from here.
class App extends StatelessWidget {
  const App({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    UtilsAppDetails().setApplicationOrientation();
    final ServicesFirebaseCore _firebaseCore = FirebaseCore();

    return BlocBuilder<ThemeBloc, ThemeState>(
      builder: (_, themeState) =>
          BlocBuilder<LocalizationBloc, LocalisationState>(
        builder: (_, localizationState) => MaterialApp(
          title: Constants().appName,
          debugShowCheckedModeBanner: !UtilsAppDetails().isReleaseMode,
          themeMode: themeState.currentThemeMode,
          theme: FlexThemeData.light(
            scheme: themeState.currentLightFlexScheme,
            fontFamily: GoogleFonts.roboto().fontFamily,
          ),
          highContrastTheme: FlexThemeData.light(
            scheme: themeState.currentLightContrastFlexScheme,
            fontFamily: GoogleFonts.roboto().fontFamily,
          ),
          darkTheme: FlexThemeData.dark(
            scheme: themeState.currentDarkFlexScheme,
            fontFamily: themeState.currentFontFamily,
          ),
          highContrastDarkTheme: FlexThemeData.dark(
            scheme: themeState.currentDarkContrastFlexScheme,
            fontFamily: themeState.currentFontFamily,
          ),
          localizationsDelegates:
              LocalizationDetails().getLocalizationDelegates(),
          supportedLocales: LocalizationDetails().getSupportedLocales(),
          locale: localizationState.currentLocale,
          navigatorObservers: _firebaseCore.getFirebaseNavigatorObservers(),
          initialRoute: NavigatorsRoutes().initialRoute,
          routes: NavigatorsRoutes().routes,
        ),
      ),
    );
  }
}
