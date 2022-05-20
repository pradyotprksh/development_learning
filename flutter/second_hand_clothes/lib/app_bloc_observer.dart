import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';

/// An project level observer for bloc operations.
///
/// Any create, change, close,
/// error and transition made in Bloc will be logged and that will be handled
/// through here.
///
/// This will be helpful to debug a state change or any error and so on.
class AppBlocObserver extends BlocObserver {
  @override
  void onCreate(BlocBase bloc) {
    super.onCreate(bloc);
    UtilsLogger().log('${bloc.runtimeType} created');
  }

  @override
  void onChange(BlocBase bloc, Change change) {
    super.onChange(bloc, change);
    UtilsLogger().log('${bloc.runtimeType} changed to $change');
  }

  @override
  void onClose(BlocBase bloc) {
    super.onClose(bloc);
    UtilsLogger().log('${bloc.runtimeType} closed');
  }

  @override
  void onError(BlocBase bloc, Object error, StackTrace stackTrace) {
    super.onError(bloc, error, stackTrace);
    UtilsLogger().log(
      '${bloc.runtimeType} thrown error',
      error: error,
      stackTrace: stackTrace,
    );
  }

  @override
  void onTransition(Bloc bloc, Transition transition) {
    super.onTransition(bloc, transition);
    UtilsLogger().log('${bloc.runtimeType} transitioned to $transition');
  }
}
