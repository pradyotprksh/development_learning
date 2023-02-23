import 'package:flutter/material.dart';
import 'package:whatsapp/application/app/app.dart';

class LinearProgressIndicatorWidget extends StatelessWidget {
  const LinearProgressIndicatorWidget({
    super.key,
    this.value,
  });

  final double? value;

  @override
  Widget build(BuildContext context) => Card(
        color: Colors.white,
        child: Padding(
          padding: ThemeEdgeInsets.all10,
          child: LinearProgressIndicator(
            value: value,
          ),
        ),
      );
}
