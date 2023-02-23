import 'package:equatable/equatable.dart';
import 'package:replay_bloc/replay_bloc.dart';

abstract class LocalizationsEvent extends ReplayEvent with EquatableMixin {
  const LocalizationsEvent();

  @override
  List<Object?> get props => [];
}

class ChangeLocalizationEvent extends LocalizationsEvent {
  const ChangeLocalizationEvent({
    required this.newLocale,
  });

  final String newLocale;

  @override
  List<Object?> get props => [newLocale];
}

class FetchCurrentLocalizationEvent extends LocalizationsEvent {
  const FetchCurrentLocalizationEvent();
}
