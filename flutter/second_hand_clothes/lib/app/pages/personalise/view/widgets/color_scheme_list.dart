import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A widget to get the list of color scheme for the user to choose from.
///
/// It also shows a dot icon which is being selected by the user.
class WidgetsColorSchemeList extends StatelessWidget {
  const WidgetsColorSchemeList({super.key});

  @override
  Widget build(BuildContext context) => SizedBox(
    height: 100,
    child: BlocBuilder<ThemeBloc, ThemeState>(
      builder: (_, themeState) => ListView.builder(
        padding: ThemesEdgeInsets().left20,
        scrollDirection: Axis.horizontal,
        itemCount: FlexScheme.values.length,
        itemBuilder: (_, position) {
          final colorScheme = FlexScheme.values[position];

          final themeData = _getThemeData(
            context.isPhoneInDarkMode(),
            themeState.currentThemeMode,
            colorScheme,
          );

          final isSelected = colorScheme ==
              _getCurrentSelectedScheme(
                themeState,
                context.isPhoneInDarkMode(),
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
                        Radius.circular(10),
                      ),
                    ),
                    height: 90,
                    width: 50,
                    child: InkWell(
                      onTap: () {
                        if (!isSelected) {
                          context.read<ThemeBloc>().clearHistory();
                          final changeThemeEvent =
                          _getThemeChangeEvent(
                              themeState.currentThemeMode,
                              context.isPhoneInDarkMode(),
                              colorScheme);
                          context.read<ThemeBloc>().add(
                            changeThemeEvent,
                          );
                        }
                      },
                    ),
                  ),
                  const Spacer(),
                  if (isSelected)
                    Icon(
                      Icons.circle,
                      size: 5,
                      color: context.themeData().iconTheme.color,
                    )
                ],
              ),
              ThemesBox().width15,
            ],
          );
        },
      ),
    ),
  );

  /// Get the theme data based on the theme
  ThemeData _getThemeData(
      bool isPhoneInDarkMode,
      ThemeMode themeMode,
      FlexScheme flexScheme,
      ) {
    switch (themeMode) {
      case ThemeMode.system:
        if (!isPhoneInDarkMode) {
          return FlexThemeData.light(scheme: flexScheme);
        } else {
          return FlexThemeData.dark(scheme: flexScheme);
        }
      case ThemeMode.light:
        return FlexThemeData.light(scheme: flexScheme);
      case ThemeMode.dark:
        return FlexThemeData.dark(scheme: flexScheme);
    }
  }

  /// Get the current selected color scheme based on the theme mode.
  FlexScheme _getCurrentSelectedScheme(
      ThemeState themeState, bool isPhoneInDarkMode) {
    switch (themeState.currentThemeMode) {
      case ThemeMode.system:
        if (isPhoneInDarkMode) {
          return themeState.currentDarkFlexScheme;
        } else {
          return themeState.currentLightFlexScheme;
        }
      case ThemeMode.light:
        return themeState.currentLightFlexScheme;
      case ThemeMode.dark:
        return themeState.currentDarkFlexScheme;
    }
  }

  /// Get the theme change event based on the theme
  ChangeThemeEvent _getThemeChangeEvent(
      ThemeMode currentThemeMode,
      bool isPhoneInDarkMode,
      FlexScheme colorScheme,
      ) {
    switch (currentThemeMode) {
      case ThemeMode.system:
        if (isPhoneInDarkMode) {
          return ChangeThemeEvent(
            darkFlexScheme: colorScheme.name,
          );
        } else {
          return ChangeThemeEvent(
            lightFlexScheme: colorScheme.name,
          );
        }
      case ThemeMode.light:
        return ChangeThemeEvent(
          lightFlexScheme: colorScheme.name,
        );
      case ThemeMode.dark:
        return ChangeThemeEvent(
          darkFlexScheme: colorScheme.name,
        );
    }
  }
}
