import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app.dart';
import 'package:second_hand_clothes/app_bloc_observer.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  BlocOverrides.runZoned(
    () {
      runApp(const App());
    },
    blocObserver: AppBlocObserver(),
  );
}
