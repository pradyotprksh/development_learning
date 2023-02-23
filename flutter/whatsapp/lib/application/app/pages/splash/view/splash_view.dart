import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';

class SplashView extends StatelessWidget {
  const SplashView({super.key});

  @override
  Widget build(BuildContext context) => MultiBlocListener(
        listeners: [
          BlocListener<AuthenticationBloc, AuthenticationState>(
            listener: (_, authState) {
              switch (authState.authenticationState) {
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
                case AuthenticationStatus.applicationDown:
                case AuthenticationStatus.unknown:
                  break;
              }
            },
          ),
          BlocListener<UserBloc, UserState>(
            listener: (_, userState) {
              if (userState is UserDetailsAvailable ||
                  userState is AskForPinConfirmation) {
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
            child: BlocBuilder<AuthenticationBloc, AuthenticationState>(
              builder: (_, authenticationState) => Column(
                children: [
                  const Spacer(),
                  Padding(
                    padding: ThemeEdgeInsets.top20Bottom20,
                    child: Center(
                      child: Image.asset(
                        AssetsPath.appIcon,
                        height: 150,
                        width: 150,
                      ),
                    ),
                  ),
                  const Spacer(),
                  if (authenticationState.authenticationState ==
                      AuthenticationStatus.applicationDown)
                    Card(
                      child: Padding(
                        padding: ThemeEdgeInsets.all15,
                        child: Text(
                          context.translator.systemDownMessage,
                        ),
                      ),
                    ),
                ],
              ),
            ),
          ),
        ),
      );
}
