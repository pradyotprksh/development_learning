import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:replay_bloc/replay_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A bloc class for the theme, this will be used to look for the
/// event emitted for theme and also update the state if required.
///
/// This has storage and undo/redo the state option, so that
/// if a user changes the theme state and doesn't like it or want to go
/// back to the previous state then this can be useful. And also to save
/// the theme details locally so that it is persistent even after restarting
/// the application.
class ThemeBloc extends HydratedBloc<ThemeEvent, ThemeState>
    with ReplayBlocMixin {
  ThemeBloc() : super(const ThemeState()) {
    on<ChangeThemeEvent>(_changeThemeEvent);
    on<FetchCurrentThemeEvent>(_fetchCurrentThemeEvent);
  }

  /// Whenever [ChangeThemeEvent] is sent this method will be called.
  void _changeThemeEvent(
    ChangeThemeEvent event,
    Emitter<ThemeState> emit,
  ) {
    final newThemeState = state.copyWith(
      themeMode: event.themeMode?.stringToEnum(ThemeMode.values),
      lightFlexScheme: event.lightFlexScheme?.stringToEnum(FlexScheme.values),
      lightContrastFlexScheme: event.lightContrastFlexScheme?.stringToEnum(FlexScheme.values),
      darkFlexScheme: event.darkFlexScheme?.stringToEnum(FlexScheme.values),
      darkContrastFlexScheme: event.darkContrastFlexScheme?.stringToEnum(FlexScheme.values),
    );
    emit(newThemeState);
  }

  /// Whenever [FetchCurrentThemeEvent] is sent this method will be called.
  void _fetchCurrentThemeEvent(
    FetchCurrentThemeEvent event,
    Emitter<ThemeState> emit,
  ) {
    emit(state);
  }

  @override
  ThemeState? fromJson(Map<String, dynamic> json) => ThemeState(
        currentThemeMode:
            (json['currentThemeMode'] as String).stringToEnum<ThemeMode>(
                  ThemeMode.values,
                ) ??
                ThemeMode.system,
        currentLightFlexScheme:
            (json['currentLightFlexScheme'] as String).stringToEnum<FlexScheme>(
                  FlexScheme.values,
                ) ??
                FlexScheme.material,
        currentLightContrastFlexScheme:
            (json['currentLightContrastFlexScheme'] as String)
                    .stringToEnum<FlexScheme>(
                  FlexScheme.values,
                ) ??
                FlexScheme.material,
        currentDarkFlexScheme:
            (json['currentDarkFlexScheme'] as String).stringToEnum<FlexScheme>(
                  FlexScheme.values,
                ) ??
                FlexScheme.material,
        currentDarkContrastFlexScheme:
            (json['currentDarkContrastFlexScheme'] as String)
                    .stringToEnum<FlexScheme>(
                  FlexScheme.values,
                ) ??
                FlexScheme.material,
      );

  @override
  Map<String, dynamic>? toJson(ThemeState state) => <String, String>{
        'currentThemeMode': state.currentThemeMode.enumToString(),
        'currentLightFlexScheme': state.currentLightFlexScheme.enumToString(),
        'currentLightContrastFlexScheme':
            state.currentLightContrastFlexScheme.enumToString(),
        'currentDarkFlexScheme': state.currentDarkFlexScheme.enumToString(),
        'currentDarkContrastFlexScheme':
            state.currentDarkContrastFlexScheme.enumToString(),
      };
}
