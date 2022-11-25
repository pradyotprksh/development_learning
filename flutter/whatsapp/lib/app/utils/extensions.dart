import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

extension TR on BuildContext {
  AppLocalizations get translator => AppLocalizations.of(this)!;
}