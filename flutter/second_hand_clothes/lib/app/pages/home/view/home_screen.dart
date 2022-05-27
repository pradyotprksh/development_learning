import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A screen which will be seen by user whenever a authenticated user is found.
///
/// This will be the page where authenticated user can start using the
/// application features.
class HomeScreen extends StatelessWidget {
  const HomeScreen({
    super.key,
  });

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData().scaffoldBackgroundColor,
        extendBodyBehindAppBar: true,
        appBar: AppBar(
          backgroundColor: Colors.transparent,
          foregroundColor: context.themeData().appBarTheme.foregroundColor,
          shadowColor: context.themeData().appBarTheme.shadowColor,
          title: Text(
            'Welcome, ',
            style: context.themeData().appBarTheme.titleTextStyle,
          ),
          actions: [
            IconButton(
              onPressed: () {
                Navigator.pushNamed(
                  context,
                  NavigatorsConstants().personaliseRoute,
                );
              },
              icon: Icon(
                Icons.settings,
                color: context.themeData().iconTheme.color,
              ),
            ),
          ],
        ),
        body: Stack(
          children: [
            Align(
              alignment: Alignment.topCenter,
              child: Container(
                width: MediaQuery.of(context).size.width,
                height: MediaQuery.of(context).size.height * 0.5,
                decoration: BoxDecoration(
                  gradient: LinearGradient(
                    begin: Alignment.topCenter,
                    end: Alignment.bottomCenter,
                    colors: [
                      context.themeData().primaryColorDark,
                      Colors.transparent,
                    ],
                  ),
                ),
              ),
            ),
            ListView(
              children: [],
            ),
          ],
        ),
      );
}
