import 'dart:async';
import 'dart:developer';

import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';

/// An AppBlocObserver which will be used as a logging observer for any bloc
/// which is being used (created, changed, listened and closed).
///
/// This will be like a middleware for all blocs so that we as a developer know
/// what is going to change and to what.
class AppBlocObserver extends BlocObserver {
  @override
  void onCreate(BlocBase bloc) {
    super.onCreate(bloc);
    log('onCreate(${bloc.runtimeType})');
  }

  @override
  void onChange(BlocBase bloc, Change change) {
    super.onChange(bloc, change);
    log('onChange(${bloc.runtimeType}, $change)');
  }

  @override
  void onClose(BlocBase bloc) {
    super.onClose(bloc);
    log('onClosed(${bloc.runtimeType})');
  }

  @override
  void onError(BlocBase bloc, Object error, StackTrace stackTrace) {
    super.onError(bloc, error, stackTrace);
    log('onError(${bloc.runtimeType}, $error, $stackTrace)');
  }

  @override
  void onTransition(Bloc bloc, Transition transition) {
    super.onTransition(bloc, transition);
    log('onTransition(${bloc.runtimeType}, $transition)');
  }
}

/// A builder which will be used to initiate some of the process before the
/// runApp() method is called.
///
/// This will be helpful to clean the main file and do all the initialization,
/// logic in this place.
///
/// This will be like a method call which will be trigger by some other place.
typedef BootstrapBuilder = Future<Widget> Function();

/// The method which will handle all the initialization required before the
/// runApp call.
///
/// It uses the [BootstrapBuilder] type def to do all the required work amd
/// also execute all the steps in a guarded zone which will handle
/// the errors if thrown.
Future<void> bootstrap(BootstrapBuilder builder) async {
  WidgetsFlutterBinding.ensureInitialized();
  FlutterError.onError = (details) {
    log(details.exceptionAsString(), stackTrace: details.stack);
  };

  await runZonedGuarded(
    () async {
      await BlocOverrides.runZoned(
        () async => runApp(
          await builder(),
        ),
        blocObserver: AppBlocObserver(),
      );
    },
    (error, stackTrace) => log(error.toString(), stackTrace: stackTrace),
  );
}
