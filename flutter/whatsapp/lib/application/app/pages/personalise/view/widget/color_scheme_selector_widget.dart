import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';

class ColorSchemeSelectorWidget extends StatelessWidget {
  const ColorSchemeSelectorWidget({
    super.key,
    required this.currentThemeMode,
    required this.currentLightFlexScheme,
    required this.currentDarkFlexScheme,
  });

  final ThemeMode currentThemeMode;
  final FlexScheme currentLightFlexScheme;
  final FlexScheme currentDarkFlexScheme;

  @override
  Widget build(BuildContext context) => SizedBox(
        height: 100,
        child: ListView.builder(
          padding: ThemeEdgeInsets.left16,
          scrollDirection: Axis.horizontal,
          itemCount: FlexScheme.values.length,
          itemBuilder: (_, position) {
            final colorScheme = FlexScheme.values[position];

            final themeData = PersonaliseUtils.getThemeData(
              context.isPhoneInDarkMode,
              currentThemeMode,
              colorScheme,
            );

            final isSelected = colorScheme ==
                PersonaliseUtils.getCurrentSelectedFlexScheme(
                  context.isPhoneInDarkMode,
                  currentThemeMode,
                  currentLightFlexScheme,
                  currentDarkFlexScheme,
                );

            return Row(
              children: [
                Column(
                  children: [
                    Container(
                      decoration: BoxDecoration(
                        color: themeData.primaryColor.withAlpha(80),
                        border: Border.all(
                          color: themeData.primaryColor,
                        ),
                        borderRadius: const BorderRadius.all(
                          Radius.circular(
                            10,
                          ),
                        ),
                      ),
                      height: 90,
                      width: 50,
                      child: GestureDetector(
                        onTap: () {
                          if (!isSelected) {
                            final event = PersonaliseUtils.getThemeChangeEvent(
                              context.isPhoneInDarkMode,
                              currentThemeMode,
                              colorScheme,
                            );

                            context.read<ThemeBloc>()
                              ..clearHistory()
                              ..add(event);
                          }
                        },
                      ),
                    ),
                    const Spacer(),
                    if (isSelected)
                      Icon(
                        Icons.circle,
                        size: 5,
                        color: context.themeData.iconTheme.color,
                      )
                  ],
                ),
                ThemeSizedBox.width15,
              ],
            );
          },
        ),
      );
}
