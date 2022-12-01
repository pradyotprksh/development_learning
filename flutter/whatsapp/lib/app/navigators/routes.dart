import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

abstract class Routes {
  static const splashRoute = 'splashRoute';
  static const introRoute = 'introRoute';

  static const initialRoute = splashRoute;

  static final routes = <String, WidgetBuilder>{
    splashRoute: (context) => const SplashView(),
    introRoute: (context) => const IntroView(),
  };
}
