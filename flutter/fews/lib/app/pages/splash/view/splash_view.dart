import 'package:fews/app/app.dart';
import 'package:flutter/material.dart';

class SplashView extends StatefulWidget {
  const SplashView({super.key});

  @override
  State<SplashView> createState() => _SplashViewState();
}

class _SplashViewState extends State<SplashView> {
  @override
  void initState() {
    _initiateNavigation();
    super.initState();
  }

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        extendBody: true,
        extendBodyBehindAppBar: true,
        body: SafeArea(
          child: Column(
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
              Text(
                context.translator.applicationName,
                style: context.themeData.textTheme.headlineMedium,
              ),
              const Spacer(),
            ],
          ),
        ),
      );

  void _initiateNavigation() {
    Future.delayed(
      const Duration(seconds: 1),
      () {
        context.navigator.pushNamedAndRemoveUntil(
          Routes.homeRoute,
          (route) => false,
        );
      },
    );
  }
}
