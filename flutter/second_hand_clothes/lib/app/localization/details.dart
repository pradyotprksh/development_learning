import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

/// A class related to the localization default details, mainly contains values
/// which are not going to change during runtime.
///
/// This will help in easily fetch the details related to localization.
class LocalizationDetails {
  factory LocalizationDetails() => _instance;

  LocalizationDetails._privateConstructor();

  static final LocalizationDetails _instance =
      LocalizationDetails._privateConstructor();

  /// Get localization delegates
  List<LocalizationsDelegate> getLocalizationDelegates() => [
        AppLocalizations.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ];

  /// Get list of supported locales
  List<Locale> getSupportedLocales() => const [
        Locale('en', ''),
        Locale('es', ''),
      ];
}
