import 'package:fews/app/app.dart';
import 'package:flutter/material.dart';

abstract class Routes {
  static const splashRoute = '/splash';

  static const initialRoute = splashRoute;

  static final routes = <String, WidgetBuilder>{
    splashRoute: (context) => const SplashView(),
  };
}
