import 'package:equatable/equatable.dart';
import 'package:replay_bloc/replay_bloc.dart';

abstract class ThemeEvent extends ReplayEvent with EquatableMixin {
  const ThemeEvent();

  @override
  List<Object?> get props => [];
}

class ChangeThemeEvent extends ThemeEvent {
  const ChangeThemeEvent({
    this.themeMode,
    this.lightFlexScheme,
    this.lightContrastFlexScheme,
    this.darkFlexScheme,
    this.darkContrastFlexScheme,
    this.fontFamily,
    this.currentEnableMaterial3,
  });

  final String? themeMode;
  final String? lightFlexScheme;
  final String? lightContrastFlexScheme;
  final String? darkFlexScheme;
  final String? darkContrastFlexScheme;
  final String? fontFamily;
  final bool? currentEnableMaterial3;

  @override
  List<Object?> get props => [
        themeMode,
        lightFlexScheme,
        lightContrastFlexScheme,
        darkFlexScheme,
        darkContrastFlexScheme,
        fontFamily,
        currentEnableMaterial3,
      ];
}

class FetchCurrentThemeEvent extends ThemeEvent {
  const FetchCurrentThemeEvent();
}
