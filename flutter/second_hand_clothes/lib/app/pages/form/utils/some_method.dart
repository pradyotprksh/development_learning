import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A utility class for [FormScreen], this will contain the extra
/// functionality which will be required while UI update.
class FormUtilsSomeMethod {
  factory FormUtilsSomeMethod() => _instance;

  FormUtilsSomeMethod._privateConstructor();

  static final FormUtilsSomeMethod _instance =
      FormUtilsSomeMethod._privateConstructor();

  /// Handle snack bar for undo operation, and [content] is the message to be
  /// shown.
  ///
  /// [onOkay] = What to do when the okay option is clicked
  void handleUndoSnackBar(
    BuildContext context,
    String content,
    Function() onOkay,
  ) {
    context.replaceAndShowSnackBar(
      content,
      SnackBarAction(
        label: context.localizationValues().okayButton,
        textColor: context.themeData().snackBarTheme.actionTextColor,
        onPressed: onOkay,
      ),
    );
  }

  /// Get the text style based on the [ItemTextStyle] enum value.
  ///
  /// [context] = Current context of the tree
  ///
  /// [style] = Style from which the context style has to be fetched
  TextStyle? getTextStyle(
    BuildContext context,
    ItemTextStyle? style,
  ) {
    switch (style) {
      case ItemTextStyle.h2:
        return context.themeData().textTheme.headline2;
      case ItemTextStyle.labelLarge:
        return context.themeData().textTheme.labelLarge;
      case ItemTextStyle.caption:
        return context.themeData().textTheme.caption;
      case ItemTextStyle.unknown:
      default:
        return context.themeData().textTheme.bodyText1;
    }
  }
}
