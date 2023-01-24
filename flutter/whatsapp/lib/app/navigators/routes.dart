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
  static const selectContact = '/select-contact';
  static const addStatusText = '/add-status-text';
  static const addStatusCamera = '/add-status-camera';

  static const initialRoute = splashRoute;

  static final routes = <String, WidgetBuilder>{
    splashRoute: (context) => const SplashView(),
    introRoute: (context) => const IntroView(),
    personaliseRoute: (context) => const PersonaliseView(),
    authenticateRoute: (context) => const AuthenticateView(),
    homeRoute: (context) => MultiBlocProvider(
          providers: [
            BlocProvider(
              create: (_) => StatusBloc(
                FirebaseFirestoreServiceImplementation(),
                FirebaseAuthServiceImplementation(),
                DeviceDetailsImplementation(),
              )..add(
                  const FetchStatus(),
                ),
            ),
          ],
          child: const HomeView(),
        ),
    addStatusText: (context) => BlocProvider(
          create: (_) => AddStatusBloc(
            context.read<ThemeBloc>().state.currentFontFamily,
            FirebaseAuthServiceImplementation(),
            FirebaseFirestoreServiceImplementation(),
            DeviceDetailsImplementation(),
            FirebaseStorageServiceImplementation(),
          ),
          child: AddTextStatusView(),
        ),
    addStatusCamera: (context) => BlocProvider(
          create: (_) => AddStatusBloc(
            context.read<ThemeBloc>().state.currentFontFamily,
            FirebaseAuthServiceImplementation(),
            FirebaseFirestoreServiceImplementation(),
            DeviceDetailsImplementation(),
            FirebaseStorageServiceImplementation(),
          ),
          child: AddCameraStatusView(),
        ),
    selectContact: (context) => BlocProvider(
          create: (_) => SelectContactBloc(
            FirebaseFirestoreServiceImplementation(),
          ),
          child: const SelectContactView(),
        ),
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
