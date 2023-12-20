import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:json_widget/app/app.dart';

class JsonWidgetBlocObserver extends BlocObserver {
  const JsonWidgetBlocObserver({
    required this.showLogs,
  });

  final bool showLogs;

  @override
  void onCreate(BlocBase bloc) {
    super.onCreate(bloc);
    AppLogger.showLog(
      message: 'BLOC [${bloc.runtimeType}] created.',
      logType: LogType.information,
    );
  }

  @override
  void onEvent(Bloc bloc, Object? event) {
    super.onEvent(bloc, event);
    AppLogger.showLog(
      message:
          'BLOC [${bloc.runtimeType}] received [${event.runtimeType}] event.',
      logType: LogType.information,
    );
  }

  @override
  void onChange(BlocBase bloc, Change change) {
    super.onChange(bloc, change);
    AppLogger.showLog(
      message: 'BLOC [${bloc.runtimeType}] had the [$change] changes.',
      logType: LogType.information,
    );
  }

  @override
  void onTransition(Bloc bloc, Transition transition) {
    AppLogger.showLog(
      message: 'BLOC [${bloc.runtimeType}] had the [$transition] transition.',
      logType: LogType.information,
    );
    super.onTransition(bloc, transition);
  }

  @override
  void onError(BlocBase bloc, Object error, StackTrace stackTrace) {
    AppLogger.showLog(
      message: 'BLOC ${bloc.runtimeType} had the following error.',
      error: error,
      stackTrace: stackTrace,
      logType: LogType.error,
    );
    super.onError(bloc, error, stackTrace);
  }

  @override
  void onClose(BlocBase bloc) {
    AppLogger.showLog(
      message: 'BLOC [${bloc.runtimeType}] closed.',
      logType: LogType.information,
    );
    super.onClose(bloc);
  }
}
