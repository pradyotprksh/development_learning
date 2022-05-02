import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart' as app;

/// A widget for the form screen which will be used to show a text field.
class WidgetsFormTextField extends StatelessWidget {
  /// [textFieldItem] = Details related to the text field widget
  const WidgetsFormTextField({
    required this.textFieldItem,
    Key? key,
  }) : super(key: key);

  final app.FormItem textFieldItem;

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<app.FormBloc, app.FormState>(
        buildWhen: (previous, current) {
          final previousItem = previous.formItemDetails?.firstWhere(
            (element) => element.itemId == textFieldItem.id,
          );
          final currentItem = current.formItemDetails?.firstWhere(
            (element) => element.itemId == textFieldItem.id,
          );
          return previousItem?.itemId == currentItem?.itemId;
        },
        builder: (_, textFieldState) {
          final itemStateDetails = textFieldState.formItemDetails?.firstWhere(
            (element) => element.itemId == textFieldItem.id,
          );

          return TextField(
            key: Key(textFieldItem.id),
            decoration: InputDecoration(
              prefixIcon: Icon(
                textFieldItem.style?.icon,
                color: context.themeData().iconTheme.color,
              ),
              floatingLabelBehavior: FloatingLabelBehavior.auto,
              labelText: context
                  .localizationValues()
                  .mapLocalization[textFieldItem.style?.label],
              hintText: context
                  .localizationValues()
                  .mapLocalization[textFieldItem.style?.hint],
              errorText: context
                  .localizationValues()
                  .mapLocalization[itemStateDetails?.currentErrorMessage ?? ''],
            ),
            keyboardType: textFieldItem.style?.keyboardType,
            textInputAction: textFieldItem.style?.textInputAction,
            autofocus: textFieldItem.style?.autofocus ?? false,
            obscureText: textFieldItem.style?.obscureText ?? false,
            obscuringCharacter: textFieldItem.style?.obscuringCharacter ?? ' ',
            maxLength: textFieldItem.style?.maxLength,
            onChanged: (value) => context.read<app.FormBloc>().add(
                  app.TextFieldChangeFormEvent(
                    textFieldItem.id,
                    value,
                  ),
                ),
          );
        },
      );
}
