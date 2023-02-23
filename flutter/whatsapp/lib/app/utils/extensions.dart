import 'dart:io';

import 'package:email_validator/email_validator.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:filesize/filesize.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

extension BuildContextExtenion on BuildContext {
  AppLocalizations get translator => AppLocalizations.of(this)!;

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

extension StringExtensions on String {
  T? stringToEnum<T>(List<T> values) => EnumToString.fromString<T>(
        values,
        this,
      );

  bool isValidEmailAddress() => EmailValidator.validate(this);

  List<String> links() {
    var exp = RegExp(r'(?:(?:https?|ftp)://)?[\w/\-?=%.]+\.[\w/\-?=%.]+');
    var matches = exp.allMatches(this);
    return matches
        .toList()
        .map(
          (match) => substring(
            match.start,
            match.end,
          ),
        )
        .toList();
  }
}

extension FileExtention on FileSystemEntity {
  String? get name => path.split('/').last;
}

extension DoubleExtensions on double {
  String convertToComputerSize() {
    try {
      return filesize(toInt());
    } catch (e) {
      return '~';
    }
  }
}
