import 'package:flutter/material.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:pet_perfect_assignemnt/constants.dart';

/// Splash screen of the splash page, this will be used to create the splash
/// widgets and show them to the user.
class SplashScreen extends StatelessWidget {
  const SplashScreen({super.key});

  @override
  Widget build(BuildContext context) => FutureBuilder(
        future: _navigateToNextRoute(context),
        builder: (_, __) => Scaffold(
            backgroundColor: context.themeData().scaffoldBackgroundColor,
            extendBody: true,
            extendBodyBehindAppBar: true,
            body: SafeArea(
              minimum: const EdgeInsets.all(15),
              child: Center(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Image.asset(
                      'assets/ic_launcher.png',
                      height: 50,
                    ),
                    Text(
                      Constants().appName,
                      style: context.themeData().textTheme.headline3,
                    ),
                  ],
                ),
              ),
            )),
      );

  /// Navigate to next screen after a duration of 5 seconds.
  Future<void> _navigateToNextRoute(BuildContext context) async {
    await Future.delayed(
      const Duration(seconds: 5),
      () {
        context.navigator().pushNamedAndRemoveUntil(
              NavigatorsConstants().displayRoute,
              (Route<dynamic> route) => false,
            );
      },
    );
  }
}
