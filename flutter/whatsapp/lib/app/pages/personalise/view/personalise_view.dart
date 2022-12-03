import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class PersonaliseView extends StatelessWidget {
  const PersonaliseView({super.key});

  @override
  Widget build(BuildContext context) => BlocBuilder<ThemeBloc, ThemeState>(
        builder: (_, state) => Scaffold(
          backgroundColor: context.themeData.scaffoldBackgroundColor,
        ),
      );
}
