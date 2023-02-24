import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

abstract class LocalizationsDetails {
  static List<LocalizationsDelegate> getLocalizationDelegates() => [
        AppLocalizations.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ];

  static List<Locale> getSupportedLocales() => const [
        Locale('en', ''),
      ];

  static Map<String, String> getSupportedLanguage() => const {
        'en': 'English',
      };

  static String getHumanReadableValue(String code) =>
      getSupportedLanguage()[code] ?? '';
}
