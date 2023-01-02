import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class SplashView extends StatelessWidget {
  const SplashView({super.key});

  @override
  Widget build(BuildContext context) => MultiBlocListener(
        listeners: [
          BlocListener<AuthenticationBloc, AuthenticationState>(
            listener: (_, authState) {
              switch (authState.authenticationState) {
                case AuthenticationStatus.unknown:
                case AuthenticationStatus.authenticated:
                  context.read<UserBloc>().add(const FetchUserDetails());
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
          ),
          BlocListener<UserBloc, UserState>(
            listener: (_, userState) {
              if (userState is UserDetailsAvailable) {
                context.navigator.pushNamedAndRemoveUntil(
                  Routes.homeRoute,
                  (route) => false,
                );
              } else if (userState is UserDataNotAvailable) {
                context.navigator.pushNamedAndRemoveUntil(
                  Routes.userDetails,
                  (route) => false,
                );
              }
            },
          ),
        ],
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
