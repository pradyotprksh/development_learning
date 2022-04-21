import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:second_hand_clothes/app.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/app_bloc_observer.dart';

/// The entry point of the application.
///
/// In this will be not be doing much and keep it clean.
void main() {
  WidgetsFlutterBinding.ensureInitialized();

  HydratedBlocOverrides.runZoned(
    () => runApp(
      MultiBlocProvider(
        providers: [
          BlocProvider(
            create: (_) => ThemeBloc()
              ..add(
                FetchCurrentThemeEvent(),
              ),
          ),
          BlocProvider(
            create: (_) => LocalizationBloc()
              ..add(
                FetchCurrentLocalizationEvent(),
              ),
          ),
        ],
        child: const App(),
      ),
    ),
    blocObserver: AppBlocObserver(),
  );
}
