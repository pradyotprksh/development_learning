import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

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
            (item) {
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
                case ItemType.unknown:
                  return ThemesBox().shrink;
              }
            },
          ),
        ],
      );
}
