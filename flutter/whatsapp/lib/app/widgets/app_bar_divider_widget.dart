import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class AppBarDividerWidget extends StatelessWidget {
  const AppBarDividerWidget({
    super.key,
    this.thickness,
  });

  final double? thickness;

  @override
  Widget build(BuildContext context) => Divider(
        color: context.themeData.primaryColor.withAlpha(10),
        thickness: thickness,
      );
}
