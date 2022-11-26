import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

abstract class Routes {
  static const splashRoute = 'splashRoute';

  static const initialRoute = splashRoute;

  static final routes = <String, WidgetBuilder>{
    splashRoute: (context) => const SplashView()
  };
}
