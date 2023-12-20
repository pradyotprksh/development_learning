import 'package:equatable/equatable.dart';

abstract class JsonWidgetBuilderState extends Equatable {
  const JsonWidgetBuilderState();

  @override
  List<Object?> get props => [];
}

class InitialState extends JsonWidgetBuilderState {
  const InitialState();
}

class JsonInvalidState extends JsonWidgetBuilderState {
  const JsonInvalidState({
    this.errorMessage,
  });

  final String? errorMessage;
}