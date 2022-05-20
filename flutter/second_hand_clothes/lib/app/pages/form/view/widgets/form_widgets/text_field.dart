import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:logger/logger.dart';
import 'package:second_hand_clothes/app/app.dart' as app;

/// A widget for the form screen which will be used to show a text field.
class WidgetsFormTextField extends StatelessWidget {
  /// [textFieldItemId] = Id related to the text field widget
  const WidgetsFormTextField({
    required this.textFieldItemId,
    super.key,
  });

  final String textFieldItemId;

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<app.FormBloc, app.FormState>(
        buildWhen: (previous, current) {
          final previousTextFieldStateDetails =
              previous.formTextFieldDetails?.firstWhere(
            (element) => element.itemId == textFieldItemId,
          );
          final currentTextFieldStateDetails =
              current.formTextFieldDetails?.firstWhere(
            (element) => element.itemId == textFieldItemId,
          );

          return previousTextFieldStateDetails != currentTextFieldStateDetails;
        },
        builder: (_, formState) {
          final textFieldStateDetails =
              formState.formTextFieldDetails?.firstWhere(
            (element) => element.itemId == textFieldItemId,
          );

          app.UtilsLogger().log(
            'Creating ${textFieldStateDetails?.itemId} text field',
            logLevel: Level.info,
          );

          return (textFieldStateDetails != null)
              ? TextField(
                  key: Key(textFieldItemId),
                  decoration: InputDecoration(
                    prefixIcon: Icon(
                      textFieldStateDetails.icon,
                      color: context.themeData().iconTheme.color,
                    ),
                    floatingLabelBehavior: FloatingLabelBehavior.auto,
                    labelText: context
                        .localizationValues()
                        .mapLocalization[textFieldStateDetails.label],
                    hintText: context
                        .localizationValues()
                        .mapLocalization[textFieldStateDetails.hint],
                    errorText: context.localizationValues().mapLocalization[
                        textFieldStateDetails.currentErrorMessage],
                  ),
                  keyboardType: textFieldStateDetails.keyboardType,
                  textInputAction: textFieldStateDetails.textInputAction,
                  autofocus: textFieldStateDetails.autofocus ?? false,
                  obscureText: textFieldStateDetails.obscureText ?? false,
                  obscuringCharacter:
                      textFieldStateDetails.obscuringCharacter ?? ' ',
                  maxLength: textFieldStateDetails.maxLength,
                  onChanged: (value) => context.read<app.FormBloc>().add(
                        app.TextFieldChangeFormEvent(
                          textFieldItemId,
                          value,
                        ),
                      ),
                )
              : app.ThemesBox().shrink;
        },
      );
}
