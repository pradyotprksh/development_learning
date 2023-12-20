import 'package:equatable/equatable.dart';

abstract class JsonWidgetBuilderEvent extends Equatable {
  const JsonWidgetBuilderEvent();

  @override
  List<Object?> get props => [];
}

class OnJsonReceivedEvent extends JsonWidgetBuilderEvent {
  const OnJsonReceivedEvent({
    required this.jsonString,
  });

  final String jsonString;
}

class OnTextChangedEvent extends JsonWidgetBuilderEvent {
  const OnTextChangedEvent({
    required this.jsonKey,
    required this.value,
  });

  final String jsonKey;
  final String value;
}

class OnButtonTapEvent extends JsonWidgetBuilderEvent {
  const OnButtonTapEvent({
    required this.jsonKey,
  });

  final String jsonKey;
}
