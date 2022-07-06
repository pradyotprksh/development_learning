import 'package:equatable/equatable.dart';

/// A form event class which will listen to any api events made by the
/// api screen.
abstract class ApiEvent extends Equatable {
  const ApiEvent();

  @override
  List<Object?> get props => [];
}

/// A child of [ApiEvent] which will trigger the fetch event.
class FetchDetails extends ApiEvent {
  const FetchDetails();
}
