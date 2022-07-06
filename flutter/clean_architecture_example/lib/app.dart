import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:pet_perfect_assignemnt/app_details.dart';
import 'package:pet_perfect_assignemnt/constants.dart';

/// An App stateless widget, which will be the parent of all the widgets.
///
/// Here we will initialize and manage all the services and operations which
/// are required before the application starts and is ready for the user to be
/// used.
///
/// We will also set the theme and localization of the application from here.
class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    UtilsAppDetails().setApplicationOrientation();

    return MaterialApp(
      title: Constants().appName,
      debugShowCheckedModeBanner: !UtilsAppDetails().isReleaseMode,
      themeMode: ThemeMode.system,
      theme: FlexThemeData.light(
        scheme: FlexScheme.redWine,
        fontFamily: GoogleFonts.poppins().fontFamily,
      ),
      darkTheme: FlexThemeData.dark(
        scheme: FlexScheme.redWine,
        fontFamily: GoogleFonts.poppins().fontFamily,
      ),
      initialRoute: NavigatorsRoutes().initialRoute,
      routes: NavigatorsRoutes().routes,
    );
  }
}
