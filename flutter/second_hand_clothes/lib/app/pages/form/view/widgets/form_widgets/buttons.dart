import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:logger/logger.dart';
import 'package:second_hand_clothes/app/app.dart' as app;
import 'package:second_hand_clothes/domain/domain.dart' as domain;

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
            logLevel: Level.info,
          );

          if (buttonStateDetails != null) {
            var buttonContent =
                buttonStateDetails.buttonState == domain.ButtonState.loading
                    ? const app.WidgetsCircularProgressIndicator.small()
                    : (buttonStateDetails.buttonType ==
                                domain.ItemSubType.elevatedButton ||
                            buttonStateDetails.buttonType ==
                                domain.ItemSubType.outlinedButton)
                        ? Text(
                            context
                                    .localizationValues()
                                    .mapLocalization[buttonStateDetails.text] ??
                                buttonStateDetails.text,
                            style: context.themeData().textTheme.button,
                          )
                        : Icon(
                            buttonStateDetails.icon,
                          );

            var buttonAction =
                buttonStateDetails.buttonState == domain.ButtonState.enabled
                    ? () {
                        context.dismissKeyboard();

                        context.read<app.FormBloc>().add(
                              app.ActionsFormEvent(
                                buttonItemId,
                                buttonStateDetails.buttonAction,
                              ),
                            );
                      }
                    : null;

            switch (buttonStateDetails.buttonType) {
              case domain.ItemSubType.elevatedButton:
                return ElevatedButton(
                  key: Key(buttonStateDetails.itemId),
                  onPressed: buttonAction,
                  child: buttonContent,
                );
              case domain.ItemSubType.iconButton:
                return IconButton(
                  key: Key(buttonStateDetails.itemId),
                  onPressed: buttonAction,
                  icon: buttonContent,
                );
              case domain.ItemSubType.outlinedButton:
                return OutlinedButton(
                  key: Key(buttonStateDetails.itemId),
                  onPressed: buttonAction,
                  child: buttonContent,
                );
              case domain.ItemSubType.unknown:
              default:
                return app.ThemesBox().shrink;
            }
          } else {
            return app.ThemesBox().shrink;
          }
        },
      );
}
