import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:lottie/lottie.dart';
import 'package:second_hand_clothes/app/app.dart';

/// Splash screen of the splash page, this will be used to create the splash
/// widgets and show them to the user.
///
/// The state of this page will be handled by [SplashBloc].
class SplashScreen extends StatelessWidget {
  const SplashScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) => BlocListener<SplashBloc, SplashState>(
        listener: (BuildContext context, state) {
          switch (state.authenticationStatus) {
            case UtilsAuthenticationStatus.unknown:
              break;
            case UtilsAuthenticationStatus.authenticated:
              break;
            case UtilsAuthenticationStatus.unauthenticated:
              Navigator.pushReplacementNamed(
                context,
                NavigatorsConstants().personaliseRoute,
              );
              break;
          }
        },
        child: Scaffold(
          backgroundColor: context.themeData().backgroundColor,
          extendBody: true,
          extendBodyBehindAppBar: true,
          body: SafeArea(
            child: Padding(
              padding: ThemesEdgeInsets().top20Bottom20,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Text(
                    '${context.localizationValues().welcomeTo} ${context.localizationValues().applicationName}',
                    textAlign: TextAlign.center,
                    style: context.themeData().textTheme.headline4,
                  ),
                  const Spacer(),
                  Lottie.asset(
                    UtilsAssets().clothesAnim,
                  ),
                ],
              ),
            ),
          ),
        ),
      );
}