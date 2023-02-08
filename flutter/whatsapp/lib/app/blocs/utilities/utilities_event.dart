import 'package:equatable/equatable.dart';

abstract class UtilitiesEvent extends Equatable {
  const UtilitiesEvent();

  @override
  List<Object?> get props => [];
}

class InitiateConnectivityCheck extends UtilitiesEvent {
  const InitiateConnectivityCheck();
}
