import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class PersonaliseView extends StatelessWidget {
  const PersonaliseView({super.key});

  @override
  Widget build(BuildContext context) => BlocBuilder<ThemeBloc, ThemeState>(
        builder: (_, themeState) =>
            BlocBuilder<LocalizationsBloc, LocalizationsState>(
          builder: (_, localizationState) => Scaffold(
            backgroundColor: context.themeData.scaffoldBackgroundColor,
            appBar: AppBar(
              title: Text(
                context.translator.personaliseAppBarTitle,
                style: context.themeData.appBarTheme.titleTextStyle,
              ),
              backgroundColor: context.themeData.appBarTheme.backgroundColor,
              elevation: context.themeData.appBarTheme.elevation,
            ),
            body: SafeArea(
              child: ListView(
                children: [
                  ListTile(
                    leading: Icon(
                      PersonaliseUtils.getThemeIcon(
                        themeState.currentThemeMode,
                      ),
                      color: context.themeData.iconTheme.color,
                    ),
                    title: Text(
                      context.translator.themeMode,
                      style: context.themeData.textTheme.titleMedium,
                    ),
                    subtitle: Text(
                      context.translator.themeModeSubtitle,
                      style: context.themeData.textTheme.titleSmall,
                    ),
                    onTap: () {
                      PersonaliseUtils.showThemeModeModal(
                        context,
                        themeState.currentThemeMode,
                      );
                    },
                  ),
                  Divider(
                    color: context.themeData.dividerTheme.color,
                  ),
                  ListTile(
                    title: Text(
                      context.translator.colorTheme,
                      style: context.themeData.textTheme.titleMedium,
                    ),
                    subtitle: Text(
                      context.translator.selectColorTheme,
                      style: context.themeData.textTheme.titleSmall,
                    ),
                  ),
                  ColorSchemeSelectorWidget(
                    currentThemeMode: themeState.currentThemeMode,
                    currentLightFlexScheme: themeState.currentLightFlexScheme,
                    currentDarkFlexScheme: themeState.currentDarkFlexScheme,
                  ),
                  Divider(
                    color: context.themeData.dividerTheme.color,
                  ),
                  ListTile(
                    leading: Icon(
                      Icons.font_download,
                      color: context.themeData.iconTheme.color,
                    ),
                    title: Text(
                      context.translator.font,
                      style: context.themeData.textTheme.titleMedium,
                    ),
                    subtitle: Text(
                      context.translator.selectFont,
                      style: context.themeData.textTheme.titleSmall,
                    ),
                    onTap: () {
                      PersonaliseUtils.showFontFamilyModal(
                        context,
                        themeState.currentFontFamily,
                      );
                    },
                  ),
                  Divider(
                    color: context.themeData.dividerTheme.color,
                  ),
                  SwitchListTile(
                    value: themeState.currentEnableMaterial3,
                    onChanged: (value) {
                      context.read<ThemeBloc>()
                        ..clearHistory()
                        ..add(
                          ChangeThemeEvent(
                            currentEnableMaterial3: value,
                          ),
                        );
                    },
                    title: Text(
                      context.translator.enableMaterialYou,
                      style: context.themeData.textTheme.titleMedium,
                    ),
                    subtitle: Text(
                      context.translator.enableMaterialYouSubtitle,
                      style: context.themeData.textTheme.titleSmall,
                    ),
                  ),
                  Divider(
                    color: context.themeData.dividerTheme.color,
                  ),
                  ListTile(
                    leading: Icon(
                      Icons.language,
                      color: context.themeData.iconTheme.color,
                    ),
                    title: Text(
                      context.translator.languageTitle,
                      style: context.themeData.textTheme.titleMedium,
                    ),
                    subtitle: Text(
                      context.translator.languageSubtitle,
                      style: context.themeData.textTheme.titleSmall,
                    ),
                    onTap: () {
                      PersonaliseUtils.showLanguageModal(
                        context,
                        localizationState.currentLocale.languageCode,
                      );
                    },
                  ),
                  Divider(
                    color: context.themeData.dividerTheme.color,
                  ),
                ],
              ),
            ),
          ),
        ),
      );
}
