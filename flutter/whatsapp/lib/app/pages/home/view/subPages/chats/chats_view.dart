import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class ChatsView extends StatelessWidget {
  const ChatsView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            context.navigator.pushNamed(Routes.selectContact);
          },
          child: const Icon(Icons.chat),
        ),
      );
}
