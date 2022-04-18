import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/utils/utils.dart';

class AppBlocObserver extends BlocObserver {
  @override
  void onCreate(BlocBase bloc) {
    super.onCreate(bloc);
    UtilLogger.log('${bloc.runtimeType} created');
  }

  @override
  void onChange(BlocBase bloc, Change change) {
    super.onChange(bloc, change);
    UtilLogger.log('${bloc.runtimeType} changed to $change');
  }

  @override
  void onClose(BlocBase bloc) {
    super.onClose(bloc);
    UtilLogger.log('${bloc.runtimeType} closed');
  }

  @override
  void onError(BlocBase bloc, Object error, StackTrace stackTrace) {
    super.onError(bloc, error, stackTrace);
    UtilLogger.log('${bloc.runtimeType} thrown error', error, stackTrace);
  }

  @override
  void onTransition(Bloc bloc, Transition transition) {
    super.onTransition(bloc, transition);
    UtilLogger.log('${bloc.runtimeType} transitioned to $transition');
  }
}
