import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A screen to give the user an option to authenticate themselves and let
/// them use the application.
class AuthenticationScreen extends StatelessWidget {
  const AuthenticationScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData().backgroundColor,
        extendBody: true,
        extendBodyBehindAppBar: true,
        body: ListView(
          padding: ThemesEdgeInsets().left20Top40Right20Bottom40,
          children: [
            Text(
              context.localizationValues().hello,
              style: context.themeData().textTheme.headline2,
              textAlign: TextAlign.center,
            ),
            ThemesBox().height15,
            Text(
              context.localizationValues().newUserMessage,
              style: context.themeData().textTheme.labelLarge,
              textAlign: TextAlign.center,
            ),
            ThemesBox().height50,
            BlocBuilder<AuthenticationBloc, AuthenticationState>(
              buildWhen: (previous, current) =>
                  previous.emailAddress != current.emailAddress,
              builder: (_, authenticationState) => TextField(
                decoration: InputDecoration(
                  prefixIcon: Icon(
                    Icons.alternate_email,
                    color: context.themeData().iconTheme.color,
                  ),
                  floatingLabelBehavior: FloatingLabelBehavior.auto,
                  labelText: context.localizationValues().emailAddressLabel,
                  hintText: context.localizationValues().emailAddressHint,
                  errorText: authenticationState.emailAddress.invalid
                      ? context.localizationValues().emailAddressError
                      : null,
                ),
                keyboardType: TextInputType.emailAddress,
                textInputAction: TextInputAction.next,
                autofocus: true,
                onChanged: (emailAddress) =>
                    context.read<AuthenticationBloc>().add(
                          EmailAddressChangeEvent(
                            emailAddress,
                          ),
                        ),
              ),
            ),
            ThemesBox().height30,
            BlocBuilder<AuthenticationBloc, AuthenticationState>(
              buildWhen: (previous, current) =>
                  previous.password != current.password,
              builder: (_, authenticationState) => TextField(
                decoration: InputDecoration(
                  prefixIcon: Icon(
                    Icons.password,
                    color: context.themeData().iconTheme.color,
                  ),
                  floatingLabelBehavior: FloatingLabelBehavior.auto,
                  labelText: context.localizationValues().passwordLabel,
                  hintText: context.localizationValues().passwordHint,
                  errorText: authenticationState.password.invalid
                      ? context.localizationValues().passwordError
                      : null,
                ),
                keyboardType: TextInputType.text,
                obscureText: true,
                obscuringCharacter: '*',
                textInputAction: TextInputAction.done,
                maxLength: 16,
                onChanged: (password) => context.read<AuthenticationBloc>().add(
                      PasswordChangeEvent(
                        password,
                      ),
                    ),
              ),
            ),
            ThemesBox().height50,
            BlocBuilder<AuthenticationBloc, AuthenticationState>(
              buildWhen: (previous, current) =>
                  previous.formStatus != current.formStatus,
              builder: (_, authenticationState) => ElevatedButton(
                onPressed:
                    authenticationState.formStatus.isValidated ? () {} : null,
                child: Text(
                  context.localizationValues().authenticateButtonTitle,
                  style: context.themeData().textTheme.button,
                ),
              ),
            ),
            ThemesBox().height30,
            Divider(
              color: context.themeData().dividerColor,
              indent: 30,
              endIndent: 30,
            ),
            ThemesBox().height30,
            WidgetsPrimaryNoteBox(
              child: Text(
                context.localizationValues().authenticateOtherOptionNote,
                style: context.themeData().textTheme.caption,
                textAlign: TextAlign.center,
              ),
            ),
          ],
        ),
      );
}
