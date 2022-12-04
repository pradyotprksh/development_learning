import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class SplashView extends StatelessWidget {
  const SplashView({super.key});

  @override
  Widget build(BuildContext context) =>
      BlocListener<AuthenticationBloc, AuthenticationState>(
        listener: (_, authState) {
          switch (authState.authenticationState) {
            case AuthenticationStatus.unknown:
              // TODO: Handle this case.
              break;
            case AuthenticationStatus.authenticated:
              Future<void>.delayed(
                const Duration(seconds: 3),
                () {
                  context.navigator.pushNamedAndRemoveUntil(
                    Routes.homeRoute,
                    (route) => false,
                  );
                },
              );
              break;
            case AuthenticationStatus.unauthenticated:
              Future<void>.delayed(
                const Duration(seconds: 3),
                () {
                  context.navigator.pushNamedAndRemoveUntil(
                    Routes.introRoute,
                    (route) => false,
                  );
                },
              );
              break;
          }
        },
        child: Scaffold(
          backgroundColor: context.themeData.scaffoldBackgroundColor,
          extendBody: true,
          extendBodyBehindAppBar: true,
          body: SafeArea(
            child: Padding(
              padding: ThemeEdgeInsets.top20Bottom20,
              child: Center(
                child: Image.asset(
                  AssetsPath.appIcon,
                  height: 150,
                  width: 150,
                ),
              ),
            ),
          ),
        ),
      );
}
