import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:snake_ladders/pages/game_binding.dart';
import 'package:snake_ladders/pages/game_view.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'Snake & Ladder',
      themeMode: ThemeMode.light,
      theme: FlexColorScheme.light(
        scheme: FlexScheme.red,
        fontFamily: GoogleFonts.asap().fontFamily,
      ).toTheme,
      darkTheme: FlexColorScheme.dark(
        scheme: FlexScheme.red,
        fontFamily: GoogleFonts.asap().fontFamily,
      ).toTheme,
      getPages: [
        GetPage<GameView>(
          name: '/game_screen',
          page: () => const GameView(),
          transition: Transition.downToUp,
          binding: GameBinding(),
        ),
      ],
      initialRoute: '/game_screen',
    );
  }
}
