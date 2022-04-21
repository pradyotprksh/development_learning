import 'package:equatable/equatable.dart';
import 'package:replay_bloc/replay_bloc.dart';

/// An event to change/fetch the current theme of the application.
///
/// Will be used to set the theme when the application starts or
/// change the theme if required.
abstract class ThemeEvent extends ReplayEvent with EquatableMixin {
  @override
  List<Object?> get props => [];
}

/// A child of [ThemeEvent] which will be used to change the theme of the
/// application.
///
/// All the values in this will be nullable, because there might be a case
/// where only one theme details will be changed by the user not all.
class ChangeThemeEvent extends ThemeEvent {
  /// [themeMode] = New theme mode of the application
  ///
  /// [lightFlexScheme] = New light flex scheme
  ///
  /// [lightContrastFlexScheme] = New light contrast flex scheme
  ///
  /// [darkFlexScheme] = New dark flex scheme
  ///
  /// [darkContrastFlexScheme] = New dark contrast flex scheme
  ChangeThemeEvent(
    this.themeMode,
    this.lightFlexScheme,
    this.lightContrastFlexScheme,
    this.darkFlexScheme,
    this.darkContrastFlexScheme,
  );

  final String? themeMode;
  final String? lightFlexScheme;
  final String? lightContrastFlexScheme;
  final String? darkFlexScheme;
  final String? darkContrastFlexScheme;

  @override
  List<Object?> get props => [
        themeMode,
        lightFlexScheme,
        lightContrastFlexScheme,
        darkFlexScheme,
        darkContrastFlexScheme,
      ];
}

/// A child of [ThemeEvent] which will be used to fetch the current details of
/// the theme and update the state.
///
/// This will be used mainly when the application starts or user is on a page
/// where theme change option is available.
class FetchCurrentThemeEvent extends ThemeEvent {}
