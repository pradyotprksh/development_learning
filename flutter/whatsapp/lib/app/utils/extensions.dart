import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

extension TR on BuildContext {
  AppLocalizations get translator => AppLocalizations.of(this)!;

  ThemeData get themeData => Theme.of(this);

  NavigatorState get navigator => Navigator.of(this);

  bool get isPhoneInDarkMode =>
      MediaQuery.of(this).platformBrightness != Brightness.light;

  void clearSnackBars() {
    ScaffoldMessenger.of(this).clearSnackBars();
  }

  void replaceAndShowSnackBar(String content, SnackBarAction? action) {
    clearSnackBars();
    ScaffoldMessenger.of(this).showSnackBar(
      SnackBar(
        backgroundColor: themeData.snackBarTheme.backgroundColor,
        content: Text(
          content,
          style: themeData.snackBarTheme.contentTextStyle,
        ),
        action: action,
      ),
    );
  }
}

extension StringExtensions on String {
  T? stringToEnum<T>(List<T> values) => EnumToString.fromString<T>(
        values,
        this,
      );
}