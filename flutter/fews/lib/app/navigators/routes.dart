import 'package:fews/app/app.dart';
import 'package:fews/data/data.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

abstract class Routes {
  static const splashRoute = '/splash';
  static const homeRoute = '/home';
  static const detailsRoute = '/details';

  static const initialRoute = splashRoute;

  static final routes = <String, WidgetBuilder>{
    splashRoute: (context) => const SplashView(),
    detailsRoute: (context) {
      final imageUrl = context.routeSettings?.arguments as String;
      return DetailsView(
        imageUrl: imageUrl,
      );
    },
    homeRoute: (context) => BlocProvider(
          create: (_) => HomeBloc(
            NewsServiceImplementation(),
          )..add(
              GetNews(
                language: context.localizations.languageCode,
              ),
            ),
          child: const HomeView(),
        ),
  };
}
