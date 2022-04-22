import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

/// A values class for localization which will contain all the details related
/// to localization keys and its values.
class LocalizationValues {
  factory LocalizationValues() => _instance;

  LocalizationValues._privateConstructor();

  static final LocalizationValues _instance =
      LocalizationValues._privateConstructor();

  static LocalizationValues of(BuildContext context) {
    _buildContext = context;
    return _instance;
  }

  static late BuildContext _buildContext;

  String get welcomeTo =>
      AppLocalizations.of(_buildContext)?.welcomeTo ?? 'welcomeTo';
}
