import 'package:fews/app/app.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class HomeView extends StatelessWidget {
  const HomeView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.applicationName,
          ),
          backgroundColor: context.themeData.appBarTheme.backgroundColor,
        ),
        body: BlocBuilder<HomeBloc, HomeState>(
          builder: (_, homeState) => Stack(
            children: [
              if (homeState.pageState == PageState.loading)
                const CircularProgressIndicatorWidget()
            ],
          ),
        ),
      );
}
