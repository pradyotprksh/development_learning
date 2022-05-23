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

  final mapLocalization = <String, String>{
    'applicationName': AppLocalizations.of(_buildContext)?.applicationName ??
        'applicationName',
    'welcomeTo': AppLocalizations.of(_buildContext)?.welcomeTo ?? 'welcomeTo',
    'personaliseTitle': AppLocalizations.of(_buildContext)?.personaliseTitle ??
        'personaliseTitle',
    'personaliseDescription':
        AppLocalizations.of(_buildContext)?.personaliseDescription ??
            'personaliseDescription',
    'themeTitle':
        AppLocalizations.of(_buildContext)?.themeTitle ?? 'themeTitle',
    'themeSubTitle':
        AppLocalizations.of(_buildContext)?.themeSubTitle ?? 'themeSubTitle',
    'themeModeUndoTitle':
        AppLocalizations.of(_buildContext)?.themeModeUndoTitle ??
            'themeModeUndoTitle',
    'undoOption':
        AppLocalizations.of(_buildContext)?.undoOption ?? 'undoOption',
    'colorSchemeTitle': AppLocalizations.of(_buildContext)?.colorSchemeTitle ??
        'colorSchemeTitle',
    'colorSchemeSubTitle':
        AppLocalizations.of(_buildContext)?.colorSchemeSubTitle ??
            'colorSchemeSubTitle',
    'languageTitle':
        AppLocalizations.of(_buildContext)?.languageTitle ?? 'languageTitle',
    'languageSubTitle': AppLocalizations.of(_buildContext)?.languageSubTitle ??
        'languageSubTitle',
    'fontTitle': AppLocalizations.of(_buildContext)?.fontTitle ?? 'fontTitle',
    'fontSubTitle':
        AppLocalizations.of(_buildContext)?.fontSubTitle ?? 'fontSubTitle',
    'hello': AppLocalizations.of(_buildContext)?.hello ?? 'hello',
    'helloAgain':
        AppLocalizations.of(_buildContext)?.helloAgain ?? 'helloAgain',
    'newUserMessage':
        AppLocalizations.of(_buildContext)?.newUserMessage ?? 'newUserMessage',
    'emailAddressLabel':
        AppLocalizations.of(_buildContext)?.emailAddressLabel ??
            'emailAddressLabel',
    'emailAddressHint': AppLocalizations.of(_buildContext)?.emailAddressHint ??
        'emailAddressHint',
    'emailAddressError':
        AppLocalizations.of(_buildContext)?.emailAddressError ??
            'emailAddressError',
    'passwordLabel':
        AppLocalizations.of(_buildContext)?.passwordLabel ?? 'passwordLabel',
    'passwordHint':
        AppLocalizations.of(_buildContext)?.passwordHint ?? 'passwordHint',
    'passwordError':
        AppLocalizations.of(_buildContext)?.passwordError ?? 'passwordError',
    'authenticateButtonTitle':
        AppLocalizations.of(_buildContext)?.authenticateButtonTitle ??
            'authenticateButtonTitle',
    'authenticateOtherOptionNote':
        AppLocalizations.of(_buildContext)?.authenticateOtherOptionNote ??
            'authenticateOtherOptionNote',
    'somethingWentWrong':
        AppLocalizations.of(_buildContext)?.somethingWentWrong ??
            'somethingWentWrong',
    'okayButton':
        AppLocalizations.of(_buildContext)?.okayButton ?? 'okayButton',
    'formParsingError': AppLocalizations.of(_buildContext)?.formParsingError ??
        'formParsingError',
    'wrongFormId':
        AppLocalizations.of(_buildContext)?.wrongFormId ?? 'wrongFormId',
    'fetchingFormDetails':
        AppLocalizations.of(_buildContext)?.fetchingFormDetails ??
            'fetchingFormDetails',
    'formRefreshMessage':
        AppLocalizations.of(_buildContext)?.formRefreshMessage ??
            'formRefreshMessage',
    'doNotHaveAnAccount':
        AppLocalizations.of(_buildContext)?.doNotHaveAnAccount ??
            'doNotHaveAnAccount',
    'formProcessErrorMessage':
        AppLocalizations.of(_buildContext)?.formProcessErrorMessage ??
            'formProcessErrorMessage',
  };

  final formParsingErrorKey = 'formParsingError';

  final formProcessErrorMessageKey = 'formProcessErrorMessage';

  final wrongFormIdKey = 'wrongFormId';

  String get doNotHaveAnAccount => mapLocalization['doNotHaveAnAccount'] ?? '****';

  String get formProcessErrorMessage => mapLocalization['formProcessErrorMessage'] ?? '****';

  String get formParsingError => mapLocalization['formParsingError'] ?? '****';

  String get wrongFormId => mapLocalization['wrongFormId'] ?? '****';

  String get fetchingFormDetails =>
      mapLocalization['fetchingFormDetails'] ?? '****';

  String get formRefreshMessage =>
      mapLocalization['formRefreshMessage'] ?? '****';

  String get applicationName => mapLocalization['applicationName'] ?? '****';

  String get welcomeTo => mapLocalization['welcomeTo'] ?? '****';

  String get personaliseTitle => mapLocalization['personaliseTitle'] ?? '****';

  String get personaliseDescription =>
      mapLocalization['personaliseDescription'] ?? '****';

  String get themeTitle => mapLocalization['themeTitle'] ?? '****';

  String get themeSubTitle => mapLocalization['themeSubTitle'] ?? '****';

  String get themeModeUndoTitle =>
      mapLocalization['themeModeUndoTitle'] ?? '****';

  String get undoOption => mapLocalization['undoOption'] ?? '****';

  String get colorSchemeTitle => mapLocalization['colorSchemeTitle'] ?? '****';

  String get colorSchemeSubTitle =>
      mapLocalization['colorSchemeSubTitle'] ?? '****';

  String get languageTitle => mapLocalization['languageTitle'] ?? '****';

  String get languageSubTitle => mapLocalization['languageSubTitle'] ?? '****';

  String get fontTitle => mapLocalization['fontTitle'] ?? '****';

  String get fontSubTitle => mapLocalization['fontSubTitle'] ?? '****';

  String get hello => mapLocalization['hello'] ?? '****';

  String get helloAgain => mapLocalization['helloAgain'] ?? '****';

  String get newUserMessage => mapLocalization['newUserMessage'] ?? '****';

  String get emailAddressLabel =>
      mapLocalization['emailAddressLabel'] ?? '****';

  String get emailAddressHint => mapLocalization['emailAddressHint'] ?? '****';

  String get emailAddressError =>
      mapLocalization['emailAddressError'] ?? '****';

  String get passwordLabel => mapLocalization['passwordLabel'] ?? '****';

  String get passwordHint => mapLocalization['passwordHint'] ?? '****';

  String get passwordError => mapLocalization['passwordError'] ?? '****';

  String get authenticateButtonTitle =>
      mapLocalization['authenticateButtonTitle'] ?? '****';

  String get authenticateOtherOptionNote =>
      mapLocalization['authenticateOtherOptionNote'] ?? '****';

  String get somethingWentWrong =>
      mapLocalization['somethingWentWrong'] ?? '****';

  String get okayButton => mapLocalization['okayButton'] ?? '****';
}
