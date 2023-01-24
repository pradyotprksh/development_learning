import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';
import 'package:whatsapp/domain/models/status_user.dart';

class StatusDetailsWidget extends StatelessWidget {
  const StatusDetailsWidget({
    required this.statusDetails,
    super.key,
  });

  final UserWithSingleStatusDetails statusDetails;

  @override
  Widget build(BuildContext context) => Scaffold(
        body: Column(
          children: [
            ThemeSizedBox.height30,
            IconButton(
              onPressed: () {
                context.navigator.pop();
              },
              icon: const Icon(
                Icons.arrow_back,
              ),
              padding: ThemeEdgeInsets.all15,
            ),
            ThemeSizedBox.height30,
          ],
        ),
      );
}
