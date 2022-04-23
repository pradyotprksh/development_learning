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
        listenWhen: (previousState, newState) =>
            previousState != newState && context.read<ThemeBloc>().canUndo,
        listener: (_, themeState) {
          _handleUndoSnackBar(
            context,
            context.localizationValues().themeModeUndoTitle,
          );
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
            children: [
              ListTile(
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
                  _triggerThemeModeChangeEvent(context, currentThemeMode);
                },
              ),
              Divider(
                color: context.themeData().dividerColor,
              ),
              ListTile(
                leading: BlocBuilder<ThemeBloc, ThemeState>(
                  builder: (_, themeState) => WidgetsColorSchemeIcon(
                    currentThemeMode: themeState.currentThemeMode,
                    currentLightFlexScheme: themeState.currentLightFlexScheme,
                    currentDarkFlexScheme: themeState.currentDarkFlexScheme,
                  ),
                ),
                title: Text(
                  context.localizationValues().colorSchemeTitle,
                  style: context.themeData().textTheme.titleLarge,
                ),
                subtitle: Text(
                  context.localizationValues().colorSchemeSubTitle,
                  style: context.themeData().textTheme.subtitle1,
                ),
              ),
              ThemesBox().height15,
              const WidgetsColorSchemeList(),
              Divider(
                color: context.themeData().dividerColor,
              ),
              ListTile(
                leading: Icon(
                  Icons.language,
                  color: context.themeData().iconTheme.color,
                ),
                title: Text(
                  context.localizationValues().languageTitle,
                  style: context.themeData().textTheme.titleLarge,
                ),
                subtitle: Text(
                  context.localizationValues().languageSubTitle,
                  style: context.themeData().textTheme.subtitle1,
                ),
                onTap: () {
                  final currentLanguage =
                      context.read<LocalizationBloc>().state.currentLocale;
                  _triggerLocalizationChangeEvent(context, currentLanguage);
                },
              ),
              Divider(
                color: context.themeData().dividerColor,
              ),
              ThemesBox().height15,
              Container(
                margin: ThemesEdgeInsets().all15,
                child: WidgetsPrimaryNoteBox(
                  child: Text(
                    context.localizationValues().personaliseDescription,
                    style: context.themeData().textTheme.caption,
                  ),
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

  /// Handle snack bar for undo operation, and [content] is the message to be
  /// shown.
  void _handleUndoSnackBar(BuildContext context, String content) {
    ScaffoldMessenger.of(context).clearSnackBars();
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        backgroundColor: context.themeData().snackBarTheme.backgroundColor,
        content: Text(
          content,
          style: context.themeData().snackBarTheme.contentTextStyle,
        ),
        action: SnackBarAction(
          label: context.localizationValues().undoOption,
          textColor: context.themeData().snackBarTheme.actionTextColor,
          onPressed: () {
            context.read<ThemeBloc>().undo();
          },
        ),
      ),
    );
  }

  /// Trigger the theme change even whenever user chooses a new theme mode.
  ///
  /// Also clear the undo stack so that snack bar is handled more clearly
  /// rather than giving the undo option multiple time.
  void _triggerThemeModeChangeEvent(
    BuildContext context,
    ThemeMode currentThemeMode,
  ) async {
    final newThemeMode = await showDialog<ThemeMode>(
          context: context,
          barrierDismissible: true,
          builder: (dialogContext) => SimpleDialog(
            children: [
              ...ThemeMode.values.map(
                (e) => RadioListTile(
                  title: Text(
                    e.name.toCapitalized(),
                    style: context.themeData().textTheme.titleLarge,
                  ),
                  value: e,
                  groupValue: currentThemeMode,
                  onChanged: (value) {
                    Navigator.pop(dialogContext, value);
                  },
                ),
              ),
            ],
          ),
        ) ??
        currentThemeMode;
    context.read<ThemeBloc>().clearHistory();
    context.read<ThemeBloc>().add(
          ChangeThemeEvent(
            themeMode: newThemeMode.name,
          ),
        );
  }

  /// Trigger the language change event by showing a dialog.
  void _triggerLocalizationChangeEvent(
    BuildContext context,
    Locale currentLocale,
  ) async {
    final newLanguage = await showDialog<Locale>(
          context: context,
          barrierDismissible: true,
          builder: (dialogContext) => SimpleDialog(
            children: [
              // TODO: Fix the elected language not showing as selected in the UI
              ...LocalizationDetails().getSupportedLocales().map(
                    (e) => RadioListTile(
                      title: Text(
                        LocalizationDetails()
                                .getSupportedLanguage()[e.languageCode] ??
                            '',
                        style: context.themeData().textTheme.titleLarge,
                      ),
                      value: e.languageCode,
                      groupValue: currentLocale.languageCode,
                      onChanged: (value) {
                        Navigator.pop(dialogContext, e);
                      },
                    ),
                  ),
            ],
          ),
        ) ??
        currentLocale;
    context.read<LocalizationBloc>().clearHistory();
    context.read<LocalizationBloc>().add(
          ChangeLocalizationEvent(
            newLocale: newLanguage.languageCode,
          ),
        );
  }
}
