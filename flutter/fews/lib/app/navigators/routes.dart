import 'package:fews/app/app.dart';
import 'package:fews/data/data.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

abstract class Routes {
  static const splashRoute = '/splash';
  static const homeRoute = '/home';

  static const initialRoute = splashRoute;

  static final routes = <String, WidgetBuilder>{
    splashRoute: (context) => const SplashView(),
    homeRoute: (context) => BlocProvider(
          create: (_) => HomeBloc(
            NewsServiceImplementation(),
          )..add(
              const GetNews(),
            ),
          child: const HomeView(),
        ),
  };
}
