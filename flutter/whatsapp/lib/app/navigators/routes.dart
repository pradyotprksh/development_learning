import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/data/data.dart';
import 'package:whatsapp/device/device.dart';

abstract class Routes {
  static const splashRoute = '/splash';
  static const introRoute = '/intro';
  static const personaliseRoute = '/personalise';
  static const authenticateRoute = '/authenticate';
  static const homeRoute = '/home';
  static const userDetails = '/user-details';

  static const initialRoute = splashRoute;

  static final routes = <String, WidgetBuilder>{
    splashRoute: (context) => const SplashView(),
    introRoute: (context) => const IntroView(),
    personaliseRoute: (context) => const PersonaliseView(),
    authenticateRoute: (context) => const AuthenticateView(),
    homeRoute: (context) => const HomeView(),
    userDetails: (context) => BlocProvider(
          create: (_) => UserDetailsBloc(
            FirebaseAuthServiceImplementation(),
            FirebaseStorageServiceImplementation(),
            FirebaseFirestoreServiceImplementation(),
            DeviceDetailsImplementation(),
          )..add(
              const FetchFirebaseUserDetails(),
            ),
          child: UserDetailsView(),
        ),
  };
}
