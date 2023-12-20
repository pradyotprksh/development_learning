import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:json_widget/app/app.dart';
import 'package:json_widget/core/core.dart';

class JsonWidgetBuilderBloc
    extends Bloc<JsonWidgetBuilderEvent, JsonWidgetBuilderState> {
  JsonWidgetBuilderBloc() : super(const InitialState()) {
    on<OnJsonReceivedEvent>(_jsonReceivedEvent);
  }

  void _jsonReceivedEvent(
    OnJsonReceivedEvent event,
    Emitter<JsonWidgetBuilderState> emit,
  ) {
    final jsonValidity = UtilMethods.isAValidJson(event.jsonString);
    final isValidJson = jsonValidity.$1;
    final errorMessage = jsonValidity.$2;

    if (!isValidJson) {
      if (errorMessage != null) {
        AppLogger.showLog(
          message: errorMessage.toString(),
          logType: LogType.fatal,
          error: errorMessage,
        );
      }

      emit(
        JsonInvalidState(
          errorMessage: errorMessage?.toString(),
        ),
      );
    }
  }
}
