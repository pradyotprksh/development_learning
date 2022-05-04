import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A widget to show a circular progress indicator.
class WidgetsCircularProgressIndicator extends StatelessWidget {
  /// [height] = Height of the indicator
  ///
  /// [width] = Width of the indicator
  const WidgetsCircularProgressIndicator({
    this.height,
    this.width,
    Key? key,
  }) : super(key: key);

  /// Show a small indicator with [height] = 15 and [width] = 15.
  const WidgetsCircularProgressIndicator.small({Key? key})
      : this(
          height: 15,
          width: 15,
          key: key,
        );

  final double? height;
  final double? width;

  @override
  Widget build(BuildContext context) => SizedBox(
        height: height,
        width: width,
        child: CircularProgressIndicator(
          color: context.themeData().indicatorColor,
        ),
      );
}
