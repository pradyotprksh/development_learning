import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A utility class for [PersonaliseScreen], this will contain the extra
/// functionality which will be required while UI update.
class UtilsSomeMethod {
  factory UtilsSomeMethod() => _instance;

  UtilsSomeMethod._privateConstructor();

  static final UtilsSomeMethod _instance =
      UtilsSomeMethod._privateConstructor();

  /// Get the theme icon of the application based on [currentThemeMode].
  IconData getThemeIcon(ThemeMode currentThemeMode) {
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
  ///
  /// [onUndo] = What to do when a undo option is clicked
  void handleUndoSnackBar(
    BuildContext context,
    String content,
    Function() onUndo,
  ) {
    context.replaceAndShowSnackBar(
      content,
      SnackBarAction(
        label: context.localizationValues().undoOption,
        textColor: context.themeData().snackBarTheme.actionTextColor,
        onPressed: onUndo,
      ),
    );
  }

  /// Trigger the theme change even whenever user chooses a new theme mode.
  ///
  /// Also clear the undo stack so that snack bar is handled more clearly
  /// rather than giving the undo option multiple time.
  void triggerThemeModeChangeEvent(
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
  void triggerLocalizationChangeEvent(
    BuildContext context,
    Locale currentLocale,
  ) async {
    final newLanguage = await showDialog<Locale>(
          context: context,
          barrierDismissible: true,
          builder: (dialogContext) => SimpleDialog(
            children: [
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

  /// Show a bottom sheet dialog which gives an option to choose the required
  /// font by the user.
  void triggerFontFamilyChangeEvent(
    BuildContext context,
    String currentFontFamily,
  ) async {
    final newFontFamily = await showModalBottomSheet<String>(
          context: context,
          backgroundColor: context.themeData().bottomSheetTheme.backgroundColor,
          builder: (bottomSheetDialog) => Scaffold(
            appBar: AppBar(
              backgroundColor: context.themeData().appBarTheme.backgroundColor,
              title: Text(
                context.localizationValues().fontTitle,
                style: context.themeData().appBarTheme.titleTextStyle,
              ),
              leading: const CloseButton(),
            ),
            body: ListView.builder(
              primary: false,
              itemCount: GoogleFonts.asMap().length,
              itemBuilder: (_, position) {
                final fontFamily = GoogleFonts.asMap().keys.toList()[position];
                return ListTile(
                  title: Text(
                    fontFamily,
                    style: context.themeData().textTheme.titleLarge?.copyWith(
                          fontFamily:
                              GoogleFonts.getFont(fontFamily).fontFamily,
                        ),
                  ),
                  onTap: () {
                    Navigator.pop(
                      bottomSheetDialog,
                      GoogleFonts.asMap().keys.toList()[position],
                    );
                  },
                );
              },
            ),
          ),
        ) ??
        currentFontFamily;

    // TODO: Font family change not reflecting on the application.
    context.read<ThemeBloc>().clearHistory();
    context.read<ThemeBloc>().add(
          ChangeThemeEvent(
            fontFamily: newFontFamily,
          ),
        );
  }
}
