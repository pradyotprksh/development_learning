import 'package:flutter/material.dart';
import 'package:whatsapp/application/app/app.dart';

class SearchView extends StatelessWidget {
  const SearchView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.search,
          ),
        ),
      );
}
