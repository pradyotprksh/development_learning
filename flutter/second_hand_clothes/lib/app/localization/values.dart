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

  String get applicationName =>
      AppLocalizations.of(_buildContext)?.applicationName ?? 'applicationName';

  String get welcomeTo =>
      AppLocalizations.of(_buildContext)?.welcomeTo ?? 'welcomeTo';

  String get personaliseTitle =>
      AppLocalizations.of(_buildContext)?.personaliseTitle ??
      'personaliseTitle';

  String get personaliseDescription =>
      AppLocalizations.of(_buildContext)?.personaliseDescription ??
      'personaliseDescription';

  String get themeTitle =>
      AppLocalizations.of(_buildContext)?.themeTitle ??
      'themeTitle';

  String get themeSubTitle =>
      AppLocalizations.of(_buildContext)?.themeSubTitle ??
      'themeSubTitle';

  String get themeModeUndoTitle =>
      AppLocalizations.of(_buildContext)?.themeModeUndoTitle ??
          'themeModeUndoTitle';

  String get undoOption =>
      AppLocalizations.of(_buildContext)?.undoOption ??
          'undoOption';

  String get colorSchemeTitle =>
      AppLocalizations.of(_buildContext)?.colorSchemeTitle ??
          'colorSchemeTitle';

  String get colorSchemeSubTitle =>
      AppLocalizations.of(_buildContext)?.colorSchemeSubTitle ??
          'colorSchemeSubTitle';
}