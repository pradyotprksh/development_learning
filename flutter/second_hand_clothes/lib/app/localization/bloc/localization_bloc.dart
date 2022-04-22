import 'package:flutter/material.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:replay_bloc/replay_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A bloc class for Localization, this will listen to all the events and
/// update the state related to localization.
///
/// Any change required to localization will be handled through here. This
/// will also store the details of localization in the local storage so
/// that the values don't change even if the application is closed or
/// restarted.
class LocalizationBloc
    extends HydratedBloc<LocalizationEvent, LocalisationState>
    with ReplayBlocMixin {
  LocalizationBloc() : super(const LocalisationState()) {
    on<ChangeLocalizationEvent>(_changeLocalization);
    on<FetchCurrentLocalizationEvent>(_fetchCurrentLocalization);
  }

  @override
  LocalisationState? fromJson(Map<String, dynamic> json) => LocalisationState(
        currentLocale: Locale(
          json['currentLocale'] as String,
        ),
      );

  @override
  Map<String, dynamic>? toJson(LocalisationState state) => <String, dynamic>{
        'currentLocale': state.currentLocale.languageCode,
      };

  /// Whenever [ChangeLocalizationEvent] is sent this method will be called.
  void _changeLocalization(
    ChangeLocalizationEvent event,
    Emitter<LocalisationState> emit,
  ) {
    final newLocale = Locale(event.newLocale);
    if (LocalizationDetails().getSupportedLocales().contains(newLocale)) {
      emit(
        LocalisationState(
          currentLocale: newLocale,
        ),
      );
    } else {
      emit(state);
    }
  }

  /// Whenever [FetchCurrentLocalizationEvent] is sent this method will be called.
  void _fetchCurrentLocalization(
    FetchCurrentLocalizationEvent event,
    Emitter<LocalisationState> emit,
  ) {
    emit(state);
  }
}
