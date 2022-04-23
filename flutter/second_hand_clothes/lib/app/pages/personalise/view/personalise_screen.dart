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
  Widget build(BuildContext context) => MultiBlocListener(
        listeners: [
          BlocListener<ThemeBloc, ThemeState>(
            listenWhen: (previousState, newState) =>
                previousState != newState && context.read<ThemeBloc>().canUndo,
            listener: (_, __) {
              UtilsSomeMethod().handleUndoSnackBar(
                context,
                context.localizationValues().themeModeUndoTitle,
                () {
                  context.read<ThemeBloc>().undo();
                },
              );
            },
          ),
          BlocListener<LocalizationBloc, LocalisationState>(
            listenWhen: (previousState, newState) =>
                previousState != newState && context.read<ThemeBloc>().canUndo,
            listener: (_, __) {
              UtilsSomeMethod().handleUndoSnackBar(
                context,
                context.localizationValues().themeModeUndoTitle,
                () {
                  context.read<LocalizationBloc>().undo();
                },
              );
            },
          ),
        ],
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
                    UtilsSomeMethod().getThemeIcon(themeState.currentThemeMode),
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
                  UtilsSomeMethod()
                      .triggerThemeModeChangeEvent(context, currentThemeMode);
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
                  UtilsSomeMethod()
                      .triggerLocalizationChangeEvent(context, currentLanguage);
                },
              ),
              Divider(
                color: context.themeData().dividerColor,
              ),
              ListTile(
                leading: Icon(
                  Icons.font_download,
                  color: context.themeData().iconTheme.color,
                ),
                title: BlocBuilder<ThemeBloc, ThemeState>(
                  builder: (_, themeState) => Text(
                    '${context.localizationValues().fontTitle} - ${themeState.currentFontFamily}',
                    style: context.themeData().textTheme.titleLarge,
                  ),
                ),
                subtitle: Text(
                  context.localizationValues().fontSubTitle,
                  style: context.themeData().textTheme.subtitle1,
                ),
                onTap: () {
                  UtilsSomeMethod().triggerFontFamilyChangeEvent(
                    context,
                    context.read<ThemeBloc>().state.currentFontFamily,
                  );
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
}
