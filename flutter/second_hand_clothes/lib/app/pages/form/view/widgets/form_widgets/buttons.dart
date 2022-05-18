import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart' as app;

/// A widget for showing buttons for the form based on the item details.
class WidgetFormButtons extends StatelessWidget {
  /// [buttonItemId] = Items id for the button
  const WidgetFormButtons({
    required this.buttonItemId,
    super.key,
  });

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

          app.UtilsLogger().log(
            'Creating ${buttonStateDetails?.itemId} button which '
            'is a ${buttonStateDetails?.buttonType}',
          );

          if (buttonStateDetails != null) {
            var buttonContent =
                buttonStateDetails.buttonState == app.ButtonState.loading
                    ? const app.WidgetsCircularProgressIndicator.small()
                    : Text(
                        context
                                .localizationValues()
                                .mapLocalization[buttonStateDetails.text] ??
                            '',
                        style: context.themeData().textTheme.button,
                      );

            var buttonAction =
                buttonStateDetails.buttonState == app.ButtonState.enabled
                    ? () {
                        context.read<app.FormBloc>().add(
                              app.ActionsFormEvent(
                                buttonItemId,
                                buttonStateDetails.buttonAction,
                              ),
                            );
                      }
                    : null;

            switch (buttonStateDetails.buttonType) {
              case app.ItemSubType.elevatedButton:
                return ElevatedButton(
                  key: Key(buttonStateDetails.itemId),
                  onPressed: buttonAction,
                  child: buttonContent,
                );
              case app.ItemSubType.iconButton:
                return IconButton(
                  key: Key(buttonStateDetails.itemId),
                  onPressed: buttonAction,
                  icon: Icon(
                    buttonStateDetails.icon,
                  ),
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
