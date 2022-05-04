import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart' as app;
import 'package:second_hand_clothes/app/app.dart';

/// A widget for showing buttons for the form based on the item details.
class WidgetFormButtons extends StatelessWidget {
  /// [buttonItemId] = Items id for the button
  const WidgetFormButtons({
    required this.buttonItemId,
    Key? key,
  }) : super(key: key);

  final String buttonItemId;

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<app.FormBloc, app.FormState>(
        buildWhen: (previous, current) {
          final previousButtonStateDetails =
              previous.formButtonDetails?.firstWhere(
            (element) => element.itemId == buttonItemId,
          );
          final currentButtonStateDetails =
              current.formButtonDetails?.firstWhere(
            (element) => element.itemId == buttonItemId,
          );

          return previousButtonStateDetails != currentButtonStateDetails;
        },
        builder: (_, formState) {
          final buttonStateDetails = formState.formButtonDetails?.firstWhere(
            (element) => element.itemId == buttonItemId,
          );

          if (buttonStateDetails != null) {
            var buttonContent =
                buttonStateDetails.buttonState == app.ButtonState.loading
                    ? const WidgetsCircularProgressIndicator.small()
                    : Text(
                        context
                                .localizationValues()
                                .mapLocalization[buttonStateDetails.text] ??
                            '',
                        style: context.themeData().textTheme.button,
                      );

            switch (buttonStateDetails.buttonType) {
              case app.ItemSubType.elevatedButton:
                return ElevatedButton(
                  onPressed:
                      buttonStateDetails.buttonState == app.ButtonState.enabled
                          ? () {
                              context.read<app.FormBloc>().add(
                                    app.ActionsFormEvent(
                                      buttonItemId,
                                      buttonStateDetails.buttonAction,
                                    ),
                                  );
                            }
                          : null,
                  child: buttonContent,
                );
              case app.ItemSubType.unknown:
              default:
                return app.ThemesBox().shrink;
            }
          } else {
            return app.ThemesBox().shrink;
          }
        },
      );
}
