import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// A routes class which will contain all the details related to routes
/// which are being taken in the applicaiton.
class NavigatorsRoutes {
  factory NavigatorsRoutes() => _instance;

  NavigatorsRoutes._privateConstructor();

  static final NavigatorsRoutes _instance =
      NavigatorsRoutes._privateConstructor();

  final initialRoute = NavigatorsConstants().splash;

  final routes = <String, WidgetBuilder>{
    NavigatorsConstants().splash: (context) => const SplashScreen(),
    NavigatorsConstants().displayRoute: (context) => BlocProvider(
          create: (_) => DisplayBloc(
            DomainApiRepository(),
            DomainDeviceRepository(),
          )..add(
              const FetchFiles(),
            ),
          child: const DisplayScreen(),
        ),
    NavigatorsConstants().apiRoute: (context) => BlocProvider(
          create: (_) => ApiBloc(DomainApiRepository())
            ..add(
              const FetchDetails(),
            ),
          child: const ApiScreen(),
        ),
  };
}
