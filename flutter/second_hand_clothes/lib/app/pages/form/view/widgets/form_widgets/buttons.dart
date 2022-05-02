import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart' as app;

/// A widget for showing buttons for the form based on the item details.
class WidgetFormButtons extends StatelessWidget {
  /// [buttonItem] = Items details for the button
  const WidgetFormButtons({
    required this.buttonItem,
    Key? key,
  }) : super(key: key);

  final app.FormItem buttonItem;

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<app.FormBloc, app.FormState>(
        builder: (_, buttonState) {
          switch (buttonItem.subType) {
            case app.ItemSubType.elevatedButton:
              return ElevatedButton(
                onPressed: buttonState.formStatus == FormzStatus.valid
                    ? () {}
                    : null,
                child: Text(
                  context
                          .localizationValues()
                          .mapLocalization[buttonItem.text] ??
                      '',
                  style: context.themeData().textTheme.button,
                ),
              );
            case app.ItemSubType.unknown:
            default:
              return app.ThemesBox().shrink;
          }
        },
      );
}
