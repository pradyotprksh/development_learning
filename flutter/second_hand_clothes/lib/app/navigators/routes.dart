import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';
import 'package:second_hand_clothes/domain/repositories/firebase_db.dart';

/// A routes class which will contain all the details related to routes
/// which are being taken in the applicaiton.
class NavigatorsRoutes {
  factory NavigatorsRoutes() => _instance;

  NavigatorsRoutes._privateConstructor();

  static final NavigatorsRoutes _instance =
      NavigatorsRoutes._privateConstructor();

  final initialRoute = NavigatorsConstants().splashRoute;

  final routes = <String, WidgetBuilder>{
    NavigatorsConstants().splashRoute: (context) => const SplashScreen(),
    NavigatorsConstants().personaliseRoute: (context) =>
        const PersonaliseScreen(),
    NavigatorsConstants().formRoute: (context) => BlocProvider(
          create: (_) => FormBloc(
            RepositoriesFirebaseAuth(),
            RepositoriesFirebaseDB(),
          ),
          child: const FormScreen(),
        ),
    NavigatorsConstants().homeRoute: (context) => const HomeScreen(),
  };
}
