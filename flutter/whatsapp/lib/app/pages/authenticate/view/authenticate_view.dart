import 'package:firebase_ui_auth/firebase_ui_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class AuthenticateView extends StatelessWidget {
  const AuthenticateView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        body: SafeArea(
          child: BlocConsumer<UserBloc, UserState>(
            listener: (_, userState) {
              if (userState is UserDetailsAvailable) {
                context.navigator.pushNamedAndRemoveUntil(
                  Routes.homeRoute,
                  (route) => false,
                );
              } else if (userState is UserDataNotAvailable) {
                context.navigator.pushNamedAndRemoveUntil(
                  Routes.userDetails,
                  (route) => false,
                );
              }
            },
            builder: (_, userState) => Stack(
              children: [
                if (userState is FetchingUserDetails)
                  const Center(
                    child: CircularProgressIndicator(),
                  ),
                SignInScreen(
                  providers: [
                    PhoneAuthProvider(),
                    EmailAuthProvider(),
                  ],
                  actions: [
                    AuthStateChangeAction<SignedIn>(
                      (context, state) {
                        context.read<AuthenticationBloc>().add(
                              UserAuthenticatedEvent(
                                firebaseUserDetails: state.user,
                              ),
                            );
                        context.read<UserBloc>().add(const FetchUserDetails());
                      },
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      );
}
