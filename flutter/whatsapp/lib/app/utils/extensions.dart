import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

extension TR on BuildContext {
  AppLocalizations get translator => AppLocalizations.of(this)!;

  ThemeData get themeData => Theme.of(this);

  NavigatorState get navigator => Navigator.of(this);
}

extension StringExtensions on String {
  T? stringToEnum<T>(List<T> values) => EnumToString.fromString<T>(
        values,
        this,
      );
}
