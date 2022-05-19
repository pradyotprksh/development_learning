import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A widget for a single form item, based on the type a new widget will be
/// created and used.
///
/// This is used because a widget can have inner widgets as well so needs to
/// call this widget recursively.
class WidgetsFormItem extends StatelessWidget {
  /// [item] = Details of the item to be used to create a widget
  const WidgetsFormItem({
    required this.item,
    super.key,
  });

  final FormItem item;

  @override
  Widget build(BuildContext context) {
    switch (item.type) {
      case ItemType.label:
        return WidgetsFormText(
          textItemId: item.id,
        );
      case ItemType.textField:
        return WidgetsFormTextField(
          textFieldItemId: item.id,
        );
      case ItemType.button:
        return WidgetFormButtons(
          buttonItemId: item.id,
        );
      case ItemType.box:
        return ThemesBox().box(
          key: Key(item.id),
          height: item.style?.height,
          width: item.style?.width,
        );
      case ItemType.divider:
        return Divider(
          key: Key(item.id),
          color: context.themeData().dividerColor,
          indent: item.style?.indent?.start,
          endIndent: item.style?.indent?.end,
        );
      case ItemType.row:
        return WidgetsFormRow(
          rowItemId: item.id,
        );
      case ItemType.column:
        return WidgetsFormColumn(
          columnItemId: item.id,
        );
      case ItemType.unknown:
        return ThemesBox().shrink;
    }
  }
}
