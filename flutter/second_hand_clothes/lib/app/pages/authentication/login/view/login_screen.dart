import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A screen to give the user an option to login themselves and let
/// them use the application.
class LoginScreen extends StatelessWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) => BlocListener<LoginBloc, LoginState>(
        listenWhen: (previous, current) =>
            previous.formStatus != current.formStatus,
        listener: (_, loginState) {
          if (loginState.formStatus == FormzStatus.submissionFailure) {
            context.replaceAndShowSnackBar(
              loginState.errorMessage ??
                  context.localizationValues().somethingWentWrong,
              SnackBarAction(
                label: context.localizationValues().okayButton,
                textColor: context.themeData().snackBarTheme.actionTextColor,
                onPressed: () {
                  context.clearSnackBars();
                },
              ),
            );
          }
        },
        child: Scaffold(
          backgroundColor: context.themeData().backgroundColor,
          extendBody: true,
          extendBodyBehindAppBar: true,
          body: ListView(
            padding: ThemesEdgeInsets().left20Top40Right20Bottom40,
            children: [
              Text(
                context.localizationValues().helloAgain,
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
              BlocBuilder<LoginBloc, LoginState>(
                buildWhen: (previous, current) =>
                    previous.emailAddress != current.emailAddress,
                builder: (_, loginState) => TextField(
                  decoration: InputDecoration(
                    prefixIcon: Icon(
                      Icons.alternate_email,
                      color: context.themeData().iconTheme.color,
                    ),
                    floatingLabelBehavior: FloatingLabelBehavior.auto,
                    labelText: context.localizationValues().emailAddressLabel,
                    hintText: context.localizationValues().emailAddressHint,
                    errorText: loginState.emailAddress.invalid
                        ? context.localizationValues().emailAddressError
                        : null,
                  ),
                  keyboardType: TextInputType.emailAddress,
                  textInputAction: TextInputAction.next,
                  autofocus: true,
                  onChanged: (emailAddress) => context.read<LoginBloc>().add(
                        EmailAddressChangeLoginEvent(
                          emailAddress,
                        ),
                      ),
                ),
              ),
              ThemesBox().height30,
              BlocBuilder<LoginBloc, LoginState>(
                buildWhen: (previous, current) =>
                    previous.password != current.password,
                builder: (_, loginState) => TextField(
                  decoration: InputDecoration(
                    prefixIcon: Icon(
                      Icons.password,
                      color: context.themeData().iconTheme.color,
                    ),
                    floatingLabelBehavior: FloatingLabelBehavior.auto,
                    labelText: context.localizationValues().passwordLabel,
                    hintText: context.localizationValues().passwordHint,
                    errorText: loginState.password.invalid
                        ? context.localizationValues().passwordError
                        : null,
                  ),
                  keyboardType: TextInputType.text,
                  obscureText: true,
                  obscuringCharacter: '*',
                  textInputAction: TextInputAction.done,
                  maxLength: 16,
                  onChanged: (password) => context.read<LoginBloc>().add(
                        PasswordChangeLoginEvent(
                          password,
                        ),
                      ),
                ),
              ),
              ThemesBox().height50,
              BlocBuilder<LoginBloc, LoginState>(
                buildWhen: (previous, current) =>
                    previous.formStatus != current.formStatus,
                builder: (_, loginState) => ElevatedButton(
                  onPressed: (loginState.formStatus.isValidated &&
                          loginState.emailAddress.value.isNotEmpty &&
                          loginState.password.value.isNotEmpty)
                      ? () {
                          if (loginState.formStatus !=
                              FormzStatus.submissionInProgress) {
                            context.read<LoginBloc>().add(
                                  const SubmitFormLoginEvent(),
                                );
                          }
                        }
                      : null,
                  child: (loginState.formStatus ==
                          FormzStatus.submissionInProgress)
                      ? CircularProgressIndicator(
                          color: context.themeData().indicatorColor,
                        )
                      : Text(
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
        ),
      );
}
