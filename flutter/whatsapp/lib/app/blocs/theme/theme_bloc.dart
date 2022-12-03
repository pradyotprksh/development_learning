import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:replay_bloc/replay_bloc.dart';
import 'package:whatsapp/app/app.dart';

class ThemeBloc extends HydratedBloc<ThemeEvent, ThemeState>
    with ReplayBlocMixin {
  ThemeBloc() : super(const ThemeState()) {
    on<ChangeThemeEvent>(_changeThemeEvent);
    on<FetchCurrentThemeEvent>(_fetchCurrentThemeEvent);
  }

  void _changeThemeEvent(
    ChangeThemeEvent event,
    Emitter<ThemeState> emit,
  ) {
    final newThemeState = state.copyWith(
      themeMode: event.themeMode?.stringToEnum(ThemeMode.values),
      lightFlexScheme: event.lightFlexScheme?.stringToEnum(FlexScheme.values),
      lightContrastFlexScheme:
          event.lightContrastFlexScheme?.stringToEnum(FlexScheme.values),
      darkFlexScheme: event.darkFlexScheme?.stringToEnum(FlexScheme.values),
      darkContrastFlexScheme:
          event.darkContrastFlexScheme?.stringToEnum(FlexScheme.values),
      fontFamily: event.fontFamily,
      enableMaterial3: event.currentEnableMaterial3,
    );
    emit(newThemeState);
  }

  void _fetchCurrentThemeEvent(
    FetchCurrentThemeEvent event,
    Emitter<ThemeState> emit,
  ) {
    emit(state);
  }

  @override
  ThemeState? fromJson(Map<String, dynamic> json) => ThemeState(
        currentThemeMode:
            (json[Keys.currentThemeMode] as String).stringToEnum<ThemeMode>(
                  ThemeMode.values,
                ) ??
                ThemeMode.system,
        currentLightFlexScheme: (json[Keys.currentLightFlexScheme] as String)
                .stringToEnum<FlexScheme>(
              FlexScheme.values,
            ) ??
            FlexScheme.material,
        currentLightContrastFlexScheme:
            (json[Keys.currentLightContrastFlexScheme] as String)
                    .stringToEnum<FlexScheme>(
                  FlexScheme.values,
                ) ??
                FlexScheme.materialHc,
        currentDarkFlexScheme: (json[Keys.currentDarkFlexScheme] as String)
                .stringToEnum<FlexScheme>(
              FlexScheme.values,
            ) ??
            FlexScheme.material,
        currentDarkContrastFlexScheme:
            (json[Keys.currentDarkContrastFlexScheme] as String)
                    .stringToEnum<FlexScheme>(
                  FlexScheme.values,
                ) ??
                FlexScheme.materialHc,
        currentFontFamily: json[Keys.currentFontFamily] as String,
    currentEnableMaterial3: json[Keys.currentEnableMaterial3] as bool,
      );

  @override
  Map<String, dynamic>? toJson(ThemeState state) => <String, dynamic>{
        Keys.currentThemeMode: state.currentThemeMode.name,
        Keys.currentLightFlexScheme: state.currentLightFlexScheme.name,
        Keys.currentLightContrastFlexScheme:
            state.currentLightContrastFlexScheme.name,
        Keys.currentDarkFlexScheme: state.currentDarkFlexScheme.name,
        Keys.currentDarkContrastFlexScheme:
            state.currentDarkContrastFlexScheme.name,
        Keys.currentFontFamily: state.currentFontFamily,
        Keys.currentEnableMaterial3: state.currentEnableMaterial3,
      };
}
