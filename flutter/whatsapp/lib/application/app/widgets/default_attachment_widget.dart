import 'package:flutter/material.dart';

class DefaultAttachmentWidget extends StatelessWidget {
  const DefaultAttachmentWidget({
    super.key,
    this.icon = Icons.file_present,
  });

  final IconData icon;

  @override
  Widget build(BuildContext context) => Container(
        decoration: const BoxDecoration(
          color: Colors.amberAccent,
          borderRadius: BorderRadius.all(
            Radius.circular(
              10,
            ),
          ),
        ),
        child: Center(
          child: Icon(
            icon,
          ),
        ),
      );
}
