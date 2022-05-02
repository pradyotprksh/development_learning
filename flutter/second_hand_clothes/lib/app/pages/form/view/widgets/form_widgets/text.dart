import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A widget for text item type, which will be used to show a label
class WidgetsFormText extends StatelessWidget {
  /// [textItem] = Item details for the text widget
  const WidgetsFormText({
    required this.textItem,
    Key? key,
  }) : super(key: key);

  final FormItem textItem;

  @override
  Widget build(BuildContext context) => Text(
        context.localizationValues().mapLocalization[textItem.text] ?? '',
        key: Key(textItem.id),
        style: FormUtilsSomeMethod().getTextStyle(
          context,
          textItem.style?.style,
        ),
        textAlign: textItem.style?.textAlignment,
      );
}
