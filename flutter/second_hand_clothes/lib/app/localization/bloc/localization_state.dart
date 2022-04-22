import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';

/// A state for localization which will contains the details of the
/// localization used by the current application.
///
/// If any change required to the localization this state will be used to
/// change/update it.
class LocalisationState extends Equatable {
  /// [currentLocale] = Current locale of the application.
  const LocalisationState({
    this.currentLocale = const Locale('en', ''),
  });

  final Locale currentLocale;

  @override
  List<Object?> get props => [currentLocale];
}
