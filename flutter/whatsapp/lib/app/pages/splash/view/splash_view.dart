import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class SplashView extends StatelessWidget {
  const SplashView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData().backgroundColor,
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
      );
}
