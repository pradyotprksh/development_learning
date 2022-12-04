import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

abstract class Routes {
  static const splashRoute = '/splash';
  static const introRoute = '/intro';
  static const personaliseRoute = '/personalise';
  static const authenticateRoute = '/authenticate';
  static const homeRoute = '/home';

  static const initialRoute = splashRoute;

  static final routes = <String, WidgetBuilder>{
    splashRoute: (context) => const SplashView(),
    introRoute: (context) => const IntroView(),
    personaliseRoute: (context) => const PersonaliseView(),
    authenticateRoute: (context) => const AuthenticateView(),
    homeRoute: (context) => const HomeView(),
  };
}
