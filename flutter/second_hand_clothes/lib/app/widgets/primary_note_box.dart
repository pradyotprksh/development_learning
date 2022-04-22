import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A note box widget with background and border color matches the primary
/// color of the application.
///
/// A round border of 10 is also added, and the background color has an alpha
/// of 10.
///
/// A padding of 10 from all end is added in the widget.
class WidgetsPrimaryNoteBox extends StatelessWidget {
  /// [child] = Child widget which is required inside the box.
  const WidgetsPrimaryNoteBox({
    required this.child,
    Key? key,
  }) : super(key: key);

  final Widget child;

  @override
  Widget build(BuildContext context) => Container(
    decoration: BoxDecoration(
      color: context.themeData().primaryColor.withAlpha(
        10,
      ),
      border: Border.all(
        color: context.themeData().primaryColor,
      ),
      borderRadius: const BorderRadius.all(
        Radius.circular(10),
      ),
    ),
    padding: ThemesEdgeInsets().all10,
    child: child,
  );
}
