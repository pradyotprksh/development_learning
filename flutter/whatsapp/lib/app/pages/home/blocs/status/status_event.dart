import 'package:equatable/equatable.dart';

abstract class StatusEvent extends Equatable {
  const StatusEvent();

  @override
  List<Object?> get props => [];
}

class FetchStatus extends StatusEvent {
  const FetchStatus();
}

class MarkStatusAsSeen extends StatusEvent {
  const MarkStatusAsSeen(this.statusId);

  final String statusId;
}
