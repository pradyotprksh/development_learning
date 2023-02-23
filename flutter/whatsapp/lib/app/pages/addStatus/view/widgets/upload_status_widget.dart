import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class UploadStatusWidget extends StatelessWidget {
  const UploadStatusWidget({
    super.key,
    required this.onPressed,
  });

  final void Function() onPressed;

  @override
  Widget build(BuildContext context) => Container(
        color: Colors.black12,
        child: Padding(
          padding: ThemeEdgeInsets.all10,
          child: Row(
            children: [
              const Spacer(),
              FloatingActionButton(
                onPressed: onPressed,
                mini: true,
                heroTag: Icons.send.toString(),
                child: const Icon(
                  Icons.send,
                ),
              ),
            ],
          ),
        ),
      );
}
