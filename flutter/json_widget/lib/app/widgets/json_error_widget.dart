import 'package:flutter/material.dart';
import 'package:json_widget/app/app.dart';

/// A JSON error widget, which will be shown when the JSON passed to the
/// builder method is not a valid JSON.
///
/// For now we are returning a SizedBox with no space, so that it does
/// not show any widget.
class JsonErrorWidget extends StatelessWidget {
  /// [errorWidget] - if the user has given an option for error widget to be
  /// shown then use that instead of package error one.
  const JsonErrorWidget({
    super.key,
    this.errorWidget,
  });

  final Widget? errorWidget;

  @override
  Widget build(BuildContext context) => errorWidget ?? SizedBox.shrink(
        key: Key(WidgetKeys.jsonError.name),
      );
}
