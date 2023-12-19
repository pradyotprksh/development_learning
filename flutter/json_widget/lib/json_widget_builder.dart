import 'package:flutter/material.dart';
import 'package:json_widget/app/app.dart';
import 'package:json_widget/core/core.dart';

class JsonWidgetBuilder extends StatefulWidget {
  const JsonWidgetBuilder({
    super.key,
    required this.jsonString,
    this.options
  });

  final String jsonString;
  final Options? options;

  @override
  State<JsonWidgetBuilder> createState() => _JsonWidgetBuilderState();
}

class _JsonWidgetBuilderState extends State<JsonWidgetBuilder> {
  @override
  Widget build(BuildContext context) {
    final jsonValidity = UtilMethods.isAValidJson(widget.jsonString);
    final isValidJson = jsonValidity.$1;

    if (isValidJson) {
      return const Placeholder();
    }

    return JsonErrorWidget(
      errorWidget: widget.options?.onAnyErrorWidget,
    );
  }
}
