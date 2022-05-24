import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A widget for form screen to show all the items on the ui, based on the
/// type got from the form details.
class WidgetsFormItems extends StatelessWidget {
  /// [formItems] = List of items available in the form.
  const WidgetsFormItems({
    required this.formItems,
    this.itemOrientation,
    super.key,
  });

  final List<FormItem> formItems;
  final Axis? itemOrientation;

  @override
  Widget build(BuildContext context) => ListView(
        scrollDirection: itemOrientation ?? Axis.vertical,
        padding: ThemesEdgeInsets().left20Top40Right20Bottom40,
        children: [
          ...formItems.map(
            (item) => WidgetsFormItem(
              item: item,
            ),
          ),
        ],
      );
}
