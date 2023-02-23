import 'package:equatable/equatable.dart';

abstract class NetworkEvent extends Equatable {
  const NetworkEvent();

  @override
  List<Object?> get props => [];
}

class StartAllSizeListenersEvent extends NetworkEvent {
  const StartAllSizeListenersEvent();
}

class ToggleLessDataForCall extends NetworkEvent {
  const ToggleLessDataForCall();
}

class ClearNetworkUsageDetails extends NetworkEvent {
  const ClearNetworkUsageDetails();
}
