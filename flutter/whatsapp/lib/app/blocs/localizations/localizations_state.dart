import 'dart:ui';

import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

class LocalizationsState extends Equatable {
  const LocalizationsState({
    this.currentLocale = const Locale(Constants.defaultLanguage, ''),
  });

  final Locale currentLocale;

  @override
  List<Object?> get props => [currentLocale];
}
