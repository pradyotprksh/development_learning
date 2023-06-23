import 'package:fews/app/app.dart';
import 'package:flutter/material.dart';

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
