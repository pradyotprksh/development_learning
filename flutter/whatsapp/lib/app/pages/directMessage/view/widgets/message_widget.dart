import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/models/single_message.dart';

class MessageWidget extends StatelessWidget {
  const MessageWidget({
    super.key,
    required this.message,
  });

  final SingleMessageDetails message;

  @override
  Widget build(BuildContext context) {
    if (message.isSystemMessage) {
      return Padding(
        padding: ThemeEdgeInsets.all15,
        child: Text(
          message.message,
          textAlign: TextAlign.center,
          style: context.themeData.textTheme.bodySmall,
        ),
      );
    } else {
      return ThemeSizedBox.shrink;
    }
  }
}
