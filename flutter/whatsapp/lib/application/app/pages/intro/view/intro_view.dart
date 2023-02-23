import 'package:flutter/material.dart';
import 'package:liquid_swipe/liquid_swipe.dart';
import 'package:whatsapp/application/app/app.dart';

class IntroView extends StatefulWidget {
  const IntroView({super.key});

  @override
  State<IntroView> createState() => _IntroViewState();
}

class _IntroViewState extends State<IntroView> {
  late LiquidController _liquidController;

  @override
  void initState() {
    super.initState();
    _liquidController = LiquidController();
  }

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        body: SafeArea(
          child: Builder(
            builder: (builderContext) => LiquidSwipe(
              liquidController: _liquidController,
              enableLoop: false,
              pages: [
                FirstIntroWidget(
                  title: context.translator.firstIntroTitle,
                  subtitle: context.translator.firstIntroSubtitle,
                  description: context.translator.firstIntroDescription,
                  bottomContainer: Row(
                    children: [
                      TextButton(
                        onPressed: () {
                          _liquidController.jumpToPage(page: 1);
                        },
                        style: context.themeData.textButtonTheme.style,
                        child: Text(
                          context.translator.next,
                        ),
                      ),
                      const Spacer(),
                      TextButton(
                        onPressed: () {
                          _liquidController.jumpToPage(page: 2);
                        },
                        style: context.themeData.textButtonTheme.style,
                        child: Text(
                          context.translator.skip,
                        ),
                      ),
                    ],
                  ),
                ),
                FirstIntroWidget(
                  title: context.translator.secondIntroTitle,
                  subtitle: context.translator.secondIntroSubtitle,
                  description: context.translator.secondIntroDescription,
                  bottomContainer: Row(
                    children: [
                      TextButton(
                        onPressed: () {
                          _liquidController.jumpToPage(page: 2);
                        },
                        style: context.themeData.textButtonTheme.style,
                        child: Text(
                          context.translator.next,
                        ),
                      ),
                      const Spacer(),
                      TextButton(
                        onPressed: () {
                          _liquidController.jumpToPage(page: 0);
                        },
                        style: context.themeData.textButtonTheme.style,
                        child: Text(
                          context.translator.previous,
                        ),
                      ),
                    ],
                  ),
                ),
                FirstIntroWidget(
                  title: context.translator.thirdIntroTitle,
                  subtitle: context.translator.thirdIntroSubtitle,
                  description: context.translator.thirdIntroDescription,
                  bottomContainer: Row(
                    children: [
                      TextButton(
                        onPressed: () {
                          _liquidController.jumpToPage(page: 1);
                        },
                        style: context.themeData.textButtonTheme.style,
                        child: Text(
                          context.translator.previous,
                        ),
                      ),
                      const Spacer(),
                      ElevatedButton(
                        onPressed: () {
                          context.navigator.pushNamedAndRemoveUntil(
                            Routes.authenticateRoute,
                            (route) => false,
                          );
                        },
                        style: context.themeData.elevatedButtonTheme.style,
                        child: Text(
                          context.translator.continueText,
                        ),
                      )
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      );
}
