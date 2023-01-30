import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class FirstIntroWidget extends StatelessWidget {
  const FirstIntroWidget({
    super.key,
    required this.title,
    required this.subtitle,
    required this.description,
    this.bottomContainer,
  });

  final Widget? bottomContainer;
  final String title;
  final String subtitle;
  final String description;

  @override
  Widget build(BuildContext context) => Container(
        padding: ThemeEdgeInsets.all20,
        color: context.themeData.scaffoldBackgroundColor,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          mainAxisSize: MainAxisSize.max,
          children: [
            Text(
              title,
              style: context.themeData.textTheme.titleLarge,
            ),
            ThemeSizedBox.height10,
            Text(
              subtitle,
              style: context.themeData.textTheme.titleMedium,
            ),
            const Spacer(),
            Text(
              description,
              style: context.themeData.textTheme.bodyLarge,
              textAlign: TextAlign.center,
            ),
            const Spacer(),
            bottomContainer ?? ThemeSizedBox.shrink,
          ],
        ),
      );
}
