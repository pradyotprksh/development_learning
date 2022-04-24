import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:path_provider/path_provider.dart' as path_provider;
import 'package:second_hand_clothes/app.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/app_bloc_observer.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// The entry point of the application.
///
/// In this will be not be doing much and keep it clean.
void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  await _initialSetup();

  HydratedBlocOverrides.runZoned(
    () => runApp(
      MultiBlocProvider(
        providers: [
          BlocProvider(
            create: (_) => ThemeBloc()
              ..add(
                const FetchCurrentThemeEvent(),
              ),
          ),
          BlocProvider(
            create: (_) => LocalizationBloc()
              ..add(
                const FetchCurrentLocalizationEvent(),
              ),
          ),
          BlocProvider(
            create: (_) => AuthorizationBloc(),
          ),
        ],
        child: const App(),
      ),
    ),
    storage: await HydratedStorage.build(
      storageDirectory: await path_provider.getApplicationDocumentsDirectory(),
    ),
    blocObserver: AppBlocObserver(),
  );
}

/// Few of the setup are required before runApp() is called.
///
/// That will be handled from here.
Future<void> _initialSetup() async {
  final ServicesFirebaseCore _firebaseCore = FirebaseCore();
  await _firebaseCore.initializeFirebaseApp();
  _firebaseCore.implementFirebaseCrashlytics();
}
