import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pinput/pinput.dart';
import 'package:whatsapp/app/app.dart';

class PinConfirmationView extends StatelessWidget {
  const PinConfirmationView({super.key});

  @override
  Widget build(BuildContext context) {
    final defaultPinTheme = PinTheme(
      width: context.mediaQuery.size.width / 8,
      height: 50,
      textStyle: context.themeData.textTheme.bodyLarge,
      decoration: BoxDecoration(
        border: Border.all(color: context.themeData.primaryColor),
        borderRadius: BorderRadius.circular(10),
        color: context.themeData.scaffoldBackgroundColor,
      ),
    );

    return WillPopScope(
      onWillPop: () => Future.value(false),
      child: Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          automaticallyImplyLeading: false,
          title: Text(
            context.translator.pinConfirmationTitle,
          ),
        ),
        body: Padding(
          padding: ThemeEdgeInsets.all15,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Spacer(),
              Text(
                context.translator.pinConfirmationLabel,
                style: context.themeData.textTheme.headlineSmall,
                textAlign: TextAlign.center,
              ),
              const Spacer(),
              Pinput(
                defaultPinTheme: defaultPinTheme,
                length: 8,
                obscureText: true,
                validator: (value) {
                  if (value !=
                      context.read<UserBloc>().state.userDetails?.pin) {
                    return context.translator.pinWrong;
                  }
                  context.navigator.pop(true);
                  return null;
                },
              ),
              const Spacer(),
              Text(
                context.translator.pinConfirmationNote,
                textAlign: TextAlign.center,
              ),
              ThemeSizedBox.height10,
              ElevatedButton(
                onPressed: () {},
                child: Text(
                  context.translator.verifyPhoneNumber,
                ),
              ),
              const Spacer(),
            ],
          ),
        ),
      ),
    );
  }
}
