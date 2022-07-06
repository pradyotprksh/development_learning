import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pet_perfect_assignemnt/app.dart';
import 'package:pet_perfect_assignemnt/app_bloc_observer.dart';

/// The entry point of the application.
///
/// In this will be not be doing much and keep it clean.
void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  BlocOverrides.runZoned(
    () => runApp(const App()),
    blocObserver: AppBlocObserver(),
  );
}
