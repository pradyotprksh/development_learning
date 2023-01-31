import 'package:equatable/equatable.dart';
import 'package:replay_bloc/replay_bloc.dart';

abstract class MessageEvent extends ReplayEvent with EquatableMixin {
  const MessageEvent();

  @override
  List<Object?> get props => [];
}

class FetchSelectedUserDetails extends MessageEvent {
  const FetchSelectedUserDetails(this.userId);

  final String userId;
}
