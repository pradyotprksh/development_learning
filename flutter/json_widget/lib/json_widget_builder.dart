import 'package:flutter/material.dart';
import 'package:json_widget/app/widgets/json_error_widget.dart';
import 'package:json_widget/utils/options.dart';
import 'package:json_widget/utils/util_methods.dart';

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
    final error = jsonValidity.$2?.toString();

    if (isValidJson) {
      return const Placeholder();
    }

    return JsonErrorWidget(
      jsonErrorMessage: error ?? '',
    );
  }
}
