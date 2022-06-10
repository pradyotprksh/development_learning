import 'package:flutter/widgets.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

export 'package:flutter_gen/gen_l10n/app_localizations.dart';

/// An extension for AppLocalizations which will be on BuildContext which will
/// return the object of AppLocalizations.
///
/// This will be used to take out the common code from the project and make
/// it more readable.
extension AppLocalizationsX on BuildContext {
  /// Returns the AppLocalizations type using the context.
  AppLocalizations get l10n => AppLocalizations.of(this);
}
