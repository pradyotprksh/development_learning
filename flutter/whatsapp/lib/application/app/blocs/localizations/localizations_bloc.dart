import 'dart:ui';

import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:replay_bloc/replay_bloc.dart';
import 'package:whatsapp/application/app/app.dart';

class LocalizationsBloc
    extends HydratedBloc<LocalizationsEvent, LocalizationsState>
    with ReplayBlocMixin {
  LocalizationsBloc() : super(const LocalizationsState()) {
    on<ChangeLocalizationEvent>(_changeLocalization);
    on<FetchCurrentLocalizationEvent>(_fetchCurrentLocalization);
  }

  void _changeLocalization(
    ChangeLocalizationEvent event,
    Emitter<LocalizationsState> emit,
  ) {
    final newLocale = Locale(event.newLocale);
    emit(LocalizationsState(currentLocale: newLocale));
  }

  void _fetchCurrentLocalization(
    FetchCurrentLocalizationEvent event,
    Emitter<LocalizationsState> emit,
  ) {
    emit(state);
  }

  @override
  LocalizationsState? fromJson(Map<String, dynamic> json) => LocalizationsState(
        currentLocale: Locale(
          json[Keys.currentLocale] as String,
        ),
      );

  @override
  Map<String, dynamic>? toJson(LocalizationsState state) => <String, dynamic>{
        Keys.currentLocale: state.currentLocale.languageCode,
      };
}
