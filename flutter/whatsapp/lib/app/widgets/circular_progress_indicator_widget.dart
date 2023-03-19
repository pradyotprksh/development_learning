import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

@WidgetTests()
class CircularProgressIndicatorWidget extends StatelessWidget {
  const CircularProgressIndicatorWidget({super.key});

  @override
  Widget build(BuildContext context) => const Center(
        child: Card(
          color: Colors.white,
          child: Padding(
            padding: ThemeEdgeInsets.all15,
            child: CircularProgressIndicator(),
          ),
        ),
      );
}
