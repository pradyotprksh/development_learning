import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:logger/logger.dart';
import 'package:second_hand_clothes/app/app.dart' as app;

/// A widget for showing column for the form based on the item details.
class WidgetsFormColumn extends StatelessWidget {
  /// [columnItemId] = Items id for the row
  const WidgetsFormColumn({
    required this.columnItemId,
    super.key,
  });

  final String columnItemId;

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<app.FormBloc, app.FormState>(
        buildWhen: (previous, current) {
          final previousColumnStateDetails =
              previous.formColumnDetails?.firstWhere(
            (element) => element.itemId == columnItemId,
          );
          final currentColumnStateDetails =
              current.formColumnDetails?.firstWhere(
            (element) => element.itemId == columnItemId,
          );

          return previousColumnStateDetails != currentColumnStateDetails;
        },
        builder: (_, formState) {
          final columnStateDetails = formState.formColumnDetails?.firstWhere(
            (element) => element.itemId == columnItemId,
          );

          app.UtilsLogger().log(
            'Creating ${columnStateDetails?.itemId} column',
            logLevel: Level.info,
          );

          return (columnStateDetails != null)
              ? Column(
                  key: Key(columnItemId),
                  children: [
                    ...columnStateDetails.children.map(
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
