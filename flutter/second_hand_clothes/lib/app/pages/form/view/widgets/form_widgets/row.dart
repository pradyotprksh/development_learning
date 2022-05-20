import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:logger/logger.dart';
import 'package:second_hand_clothes/app/app.dart' as app;

/// A widget for showing row for the form based on the item details.
class WidgetsFormRow extends StatelessWidget {
  /// [rowItemId] = Items id for the row
  const WidgetsFormRow({
    required this.rowItemId,
    super.key,
  });

  final String rowItemId;

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<app.FormBloc, app.FormState>(
        buildWhen: (previous, current) {
          final previousRowStateDetails = previous.formRowDetails?.firstWhere(
            (element) => element.itemId == rowItemId,
          );
          final currentRowStateDetails = current.formRowDetails?.firstWhere(
            (element) => element.itemId == rowItemId,
          );

          return previousRowStateDetails != currentRowStateDetails;
        },
        builder: (_, formState) {
          final rowStateDetails = formState.formRowDetails?.firstWhere(
            (element) => element.itemId == rowItemId,
          );

          app.UtilsLogger().log(
            'Creating ${rowStateDetails?.itemId} row',
            logLevel: Level.info,
          );

          return (rowStateDetails != null)
              ? Row(
                  key: Key(rowItemId),
                  mainAxisAlignment: rowStateDetails.mainAxisAlignment ??
                      MainAxisAlignment.start,
                  children: [
                    ...rowStateDetails.children.map(
                      (child) => app.WidgetsFormItem(
                        item: child,
                      ),
                    ),
                  ],
                )
              : app.ThemesBox().shrink;
        },
      );
}
