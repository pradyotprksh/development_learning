import 'package:equatable/equatable.dart';
import 'package:get/get_rx/get_rx.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class StatusEvent extends Equatable {
  const StatusEvent();

  @override
  List<Object?> get props => [];
}

class FetchStatus extends StatusEvent {
  const FetchStatus();
}

class MarkStatusAsSeen extends StatusEvent {
  const MarkStatusAsSeen(this.statusId, this.statusSeenBy);

  final String statusId;
  final RxList<StatusSeenDetails> statusSeenBy;
}
