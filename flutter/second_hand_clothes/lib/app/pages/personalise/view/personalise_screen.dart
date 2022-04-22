import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A screen for letting the user to update the look and feel of the
/// application.
///
/// Theme, color schema, font, etc. can be change by the user.
class PersonaliseScreen extends StatelessWidget {
  const PersonaliseScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) => BlocListener<ThemeBloc, ThemeState>(
        listenWhen: (previousState, newState) => previousState != newState,
        listener: (context, state) {
          // TODO: Handle undo option for theme state change
        },
        child: Scaffold(
          backgroundColor: context.themeData().backgroundColor,
          appBar: AppBar(
            backgroundColor: context.themeData().appBarTheme.backgroundColor,
            foregroundColor: context.themeData().appBarTheme.foregroundColor,
            shadowColor: context.themeData().appBarTheme.shadowColor,
            title: Text(
              context.localizationValues().personaliseTitle,
              style: context.themeData().appBarTheme.titleTextStyle,
            ),
          ),
          body: ListView(
            padding: ThemesEdgeInsets().all15,
            children: [
              ListTile(
                contentPadding: ThemesEdgeInsets().zero,
                leading: BlocBuilder<ThemeBloc, ThemeState>(
                  builder: (_, themeState) => Icon(
                    _getThemeIcon(themeState.currentThemeMode),
                    color: context.themeData().iconTheme.color,
                  ),
                ),
                title: Text(
                  context.localizationValues().themeTitle,
                  style: context.themeData().textTheme.titleLarge,
                ),
                subtitle: Text(
                  context.localizationValues().themeSubTitle,
                  style: context.themeData().textTheme.subtitle1,
                ),
                onTap: () async {
                  final currentThemeMode =
                      context.read<ThemeBloc>().state.currentThemeMode;
                  final newThemeMode = await showDialog<ThemeMode>(
                        context: context,
                        barrierDismissible: true,
                        builder: (dialogContext) => SimpleDialog(
                          children: [
                            RadioListTile(
                              title: Text(
                                ThemeMode.system.name.toCapitalized(),
                                style: context.themeData().textTheme.titleLarge,
                              ),
                              value: ThemeMode.system,
                              groupValue: currentThemeMode,
                              onChanged: (value) {
                                Navigator.pop(dialogContext, value);
                              },
                            ),
                            RadioListTile(
                              title: Text(
                                ThemeMode.light.name.toCapitalized(),
                                style: context.themeData().textTheme.titleLarge,
                              ),
                              value: ThemeMode.light,
                              groupValue: currentThemeMode,
                              onChanged: (value) {
                                Navigator.pop(dialogContext, value);
                              },
                            ),
                            RadioListTile(
                              title: Text(
                                ThemeMode.dark.name.toCapitalized(),
                                style: context.themeData().textTheme.titleLarge,
                              ),
                              value: ThemeMode.dark,
                              groupValue: currentThemeMode,
                              onChanged: (value) {
                                Navigator.pop(dialogContext, value);
                              },
                            ),
                          ],
                        ),
                      ) ??
                      currentThemeMode;
                  context.read<ThemeBloc>().add(
                        ChangeThemeEvent(
                          themeMode: newThemeMode.name,
                        ),
                      );
                },
              ),
              ThemesBox().height15,
              WidgetsPrimaryNoteBox(
                child: Text(
                  context.localizationValues().personaliseDescription,
                  style: context.themeData().textTheme.caption,
                ),
              ),
            ],
          ),
        ),
      );

  /// Get the theme icon of the application based on [currentThemeMode].
  IconData _getThemeIcon(ThemeMode currentThemeMode) {
    switch (currentThemeMode) {
      case ThemeMode.light:
        return Icons.light_mode;
      case ThemeMode.dark:
        return Icons.dark_mode;
      default:
        return Icons.phone_android;
    }
  }
}
