import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart' as app;

/// A widget for text item type, which will be used to show a label
class WidgetsFormText extends StatelessWidget {
  /// [textItemId] = Item id for the text widget
  const WidgetsFormText({
    required this.textItemId,
    super.key,
  });

  final String textItemId;

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<app.FormBloc, app.FormState>(
        buildWhen: (previous, current) {
          final previousTextStateDetails =
              previous.formLabelDetails?.firstWhere(
            (element) => element.itemId == textItemId,
          );
          final currentTextStateDetails = current.formLabelDetails?.firstWhere(
            (element) => element.itemId == textItemId,
          );

          return previousTextStateDetails != currentTextStateDetails;
        },
        builder: (_, formState) {
          final textStateDetails = formState.formLabelDetails?.firstWhere(
            (element) => element.itemId == textItemId,
          );

          return (textStateDetails != null)
              ? Text(
                  context
                          .localizationValues()
                          .mapLocalization[textStateDetails.text] ??
                      '',
                  key: Key(textItemId),
                  style: app.FormUtilsSomeMethod().getTextStyle(
                    context,
                    textStateDetails.textStyle,
                  ),
                  textAlign: textStateDetails.textAlign,
                )
              : app.ThemesBox().shrink;
        },
      );
}
