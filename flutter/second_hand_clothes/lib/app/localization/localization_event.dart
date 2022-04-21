import 'package:equatable/equatable.dart';
import 'package:replay_bloc/replay_bloc.dart';

/// An event class for the localization, which will initiate all the
/// events related to localization for the application.
///
/// It can be used to fetch the current localization or update it to a new
/// value.
abstract class LocalizationEvent extends ReplayEvent with EquatableMixin {
  @override
  List<Object?> get props => [];
}

/// A event to change the localization to a new one, which is a child
/// of [LocalizationEvent].
///
/// If any change required to the localization this event can be triggered.
class ChangeLocalizationEvent extends LocalizationEvent {
  /// [newLocale] = New locale which is to be used.
  ChangeLocalizationEvent({
    required this.newLocale,
  });

  String newLocale;

  @override
  List<Object?> get props => [newLocale];
}

/// A child of [LocalizationEvent] which will be used to fetch the
/// current details of the localization and update the state.
///
/// This will be used mainly when the application starts or user is on a page
/// where localization change option is available.
class FetchCurrentLocalizationEvent extends LocalizationEvent {}
