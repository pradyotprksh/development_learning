import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class LinearProgressIndicatorWidget extends StatelessWidget {
  const LinearProgressIndicatorWidget({super.key});

  @override
  Widget build(BuildContext context) => const Card(
        color: Colors.white,
        child: Padding(
          padding: ThemeEdgeInsets.all10,
          child: LinearProgressIndicator(),
        ),
      );
}
