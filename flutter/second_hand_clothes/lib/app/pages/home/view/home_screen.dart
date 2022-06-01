import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
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
  Widget build(BuildContext context) =>
      BlocListener<AuthorizationBloc, AuthorizationState>(
        listener: (_, state) {
          if (state.noUserDetails) {
            UtilsLogger().log('No User Details Found');
          }
        },
        child: Scaffold(
          backgroundColor: context.themeData().scaffoldBackgroundColor,
          extendBodyBehindAppBar: true,
          appBar: AppBar(
            backgroundColor: Colors.transparent,
            foregroundColor: context.themeData().appBarTheme.foregroundColor,
            shadowColor: context.themeData().appBarTheme.shadowColor,
            title: BlocBuilder<AuthorizationBloc, AuthorizationState>(
              buildWhen: (previous, current) =>
                  previous.userDetails != current.userDetails,
              builder: (_, state) {
                var welcomeMessage = '';
                if (state.userDetails?.displayName == null) {
                  welcomeMessage = 'Welcome';
                } else {
                  welcomeMessage = 'Welcome, ${state.userDetails?.displayName}';
                }
                return Text(
                  welcomeMessage,
                  style: context.themeData().appBarTheme.titleTextStyle,
                );
              },
            ),
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
                children: const [],
              ),
            ],
          ),
        ),
      );
}
