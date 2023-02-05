import 'package:flutter/material.dart';
import 'package:whatsapp/app/utils/utils.dart';

class NewGroupView extends StatelessWidget {
  const NewGroupView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.newGroup,
          ),
          actions: [
            IconButton(
              onPressed: () {},
              icon: const Icon(
                Icons.search,
              ),
            ),
          ],
        ),
      );
}
