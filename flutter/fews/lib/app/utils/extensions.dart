import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

extension BuildContextExtenion on BuildContext {
  AppLocalizations get translator => AppLocalizations.of(this)!;

  Locale get localizations => Localizations.localeOf(this);

  MediaQueryData get mediaQuery => MediaQuery.of(this);

  ThemeData get themeData => Theme.of(this);

  NavigatorState get navigator => Navigator.of(this);

  RouteSettings? get routeSettings => ModalRoute.of(this)?.settings;

  ModalRoute? get modalRoute => ModalRoute.of(this);

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
