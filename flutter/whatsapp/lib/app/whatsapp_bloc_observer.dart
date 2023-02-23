import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/core/core.dart';

class WhatsAppBlocObserver extends BlocObserver {
  @override
  void onCreate(BlocBase bloc) {
    super.onCreate(bloc);
    UtilsLogger.debugLog('${bloc.runtimeType} created');
  }

  @override
  void onChange(BlocBase bloc, Change change) {
    super.onChange(bloc, change);
    UtilsLogger.debugLog('${bloc.runtimeType} changed to $change');
  }

  @override
  void onClose(BlocBase bloc) {
    super.onClose(bloc);
    UtilsLogger.debugLog('${bloc.runtimeType} closed');
  }

  @override
  void onTransition(Bloc bloc, Transition transition) {
    super.onTransition(bloc, transition);
    UtilsLogger.debugLog('${bloc.runtimeType} transitioned to $transition');
  }
}
