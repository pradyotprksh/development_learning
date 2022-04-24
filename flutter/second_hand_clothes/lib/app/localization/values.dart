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

  String get languageTitle =>
      AppLocalizations.of(_buildContext)?.languageTitle ??
          'languageTitle';

  String get languageSubTitle =>
      AppLocalizations.of(_buildContext)?.languageSubTitle ??
          'languageTitle';

  String get fontTitle =>
      AppLocalizations.of(_buildContext)?.fontTitle ??
          'fontTitle';

  String get fontSubTitle =>
      AppLocalizations.of(_buildContext)?.fontSubTitle ??
          'fontSubTitle';

  String get hello =>
      AppLocalizations.of(_buildContext)?.hello ??
          'hello';

  String get newUserMessage =>
      AppLocalizations.of(_buildContext)?.newUserMessage ??
          'newUserMessage';

  String get emailAddressLabel =>
      AppLocalizations.of(_buildContext)?.emailAddressLabel ??
          'emailAddressLabel';

  String get emailAddressHint =>
      AppLocalizations.of(_buildContext)?.emailAddressHint ??
          'emailAddressHint';

  String get emailAddressError =>
      AppLocalizations.of(_buildContext)?.emailAddressError ??
          'emailAddressError';

  String get passwordLabel =>
      AppLocalizations.of(_buildContext)?.passwordLabel ??
          'passwordLabel';

  String get passwordHint =>
      AppLocalizations.of(_buildContext)?.passwordHint ??
          'passwordHint';

  String get passwordError =>
      AppLocalizations.of(_buildContext)?.passwordError ??
          'passwordError';

  String get authenticateButtonTitle =>
      AppLocalizations.of(_buildContext)?.authenticateButtonTitle ??
          'authenticateButtonTitle';

  String get authenticateOtherOptionNote =>
      AppLocalizations.of(_buildContext)?.authenticateOtherOptionNote ??
          'authenticateOtherOptionNote';
}
