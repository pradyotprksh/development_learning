import 'package:equatable/equatable.dart';

abstract class UtilitiesEvent extends Equatable {
  const UtilitiesEvent();

  @override
  List<Object?> get props => [];
}

class InitiateConnectivityCheck extends UtilitiesEvent {
  const InitiateConnectivityCheck();
}

class ScreenshotTaken extends UtilitiesEvent {
  const ScreenshotTaken(this.name, this.arguments);

  final String? name;
  final Object? arguments;
}
