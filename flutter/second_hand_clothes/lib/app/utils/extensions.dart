import 'package:email_validator/email_validator.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/material.dart';
import 'package:password_strength/password_strength.dart';
import 'package:second_hand_clothes/app/app.dart';

/// An extension on an String, this will be used to move the common
/// functionality to a single place rather that creating a new function
/// or redoing the same thing again on the String.
extension StringExtensions on String {
  /// Convert the string to enum. And it the enum values will be taken from
  /// [values].
  T? stringToEnum<T>(List<T> values) => EnumToString.fromString<T>(
        values,
        this,
      );

  /// Capitalized the first character of the string
  String toCapitalized() =>
      length > 0 ? '${this[0].toUpperCase()}${substring(1).toLowerCase()}' : '';

  /// Is a valid email address
  bool isValidEmail() => EmailValidator.validate(this);

  /// Is the password too weak to be used.
  bool isPasswordTooWeak() => getPasswordStrength() < 0.3;

  /// Get the password strong to be used.
  double getPasswordStrength() => estimatePasswordStrength(this);
}

/// An extension on BuildContext which will be used to take out the
/// common functionality to this for better readability across the project.
extension BuildContextExtension on BuildContext {
  /// Get the theme data object, so instead of doing Theme.of(context) now
  /// only context.themeData() will be used.
  ThemeData themeData() => Theme.of(this);

  /// Return the localization values object, so LocalizationValues.of(context)
  /// will change to context.localizationValues().
  LocalizationValues localizationValues() => LocalizationValues.of(this);

  /// Returns true if the user phone is in dark mode otherwise false.
  bool isPhoneInDarkMode() =>
      MediaQuery.of(this).platformBrightness == Brightness.light;

  /// Clear all the snack bars
  void clearSnackBars() {
    ScaffoldMessenger.of(this).clearSnackBars();
  }

  /// Replace an existing snack bar with a new one.
  ///
  /// [content] = text to be shown on the snack bar
  /// [action] = action required on the snack bar
  void replaceAndShowSnackBar(String content, SnackBarAction? action) {
    clearSnackBars();
    ScaffoldMessenger.of(this).showSnackBar(
      SnackBar(
        backgroundColor: themeData().snackBarTheme.backgroundColor,
        content: Text(
          content,
          style: themeData().snackBarTheme.contentTextStyle,
        ),
        action: action,
      ),
    );
  }
}
