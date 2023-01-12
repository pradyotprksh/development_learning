import 'package:firebase_ui_auth/firebase_ui_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class UserDetailsView extends StatelessWidget {
  UserDetailsView({super.key});

  String? _userNameValidator(String? userName, String errorMessage) {
    if (userName != null) {
      if (userName.length < 5) {
        return errorMessage;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  String? _emailValidator(String? emailAddress, String errorMessage) {
    if (emailAddress != null) {
      if (!emailAddress.isValidEmailAddress()) {
        return errorMessage;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  void _submit(bool validatePhoneNumber) {
    var isFormValid = _formKey.currentState?.validate() == true;
    var isPhoneNumberValid =
        _phoneInputKey.currentState?.formKey.currentState?.validate() == true ||
            validatePhoneNumber;

    if (isFormValid && isPhoneNumberValid) {}
  }

  final _formKey = GlobalKey<FormState>();
  final _phoneInputKey = GlobalKey<PhoneInputState>();

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<UserDetailsBloc, UserDetailsState>(
          builder: (_, userState) =>
              Scaffold(
                backgroundColor: context.themeData.scaffoldBackgroundColor,
                extendBody: true,
                extendBodyBehindAppBar: true,
                appBar: AppBar(
                  backgroundColor:
                  context.themeData.appBarTheme.backgroundColor,
                  elevation: context.themeData.appBarTheme.elevation,
                ),
                body: SafeArea(
                  child: Form(
                    key: _formKey,
                    onWillPop: () async => false,
                    child: Stack(
                      children: [
                        ListView(
                          padding: ThemeEdgeInsets.all15,
                          children: [
                            GestureDetector(
                              onTap: () {},
                              child: CircleAvatar(
                                radius: 80,
                                backgroundColor: context.themeData.primaryColor,
                                backgroundImage:
                                const AssetImage(AssetsPath.defaultAvatar),
                              ),
                            ),
                            ThemeSizedBox.height10,
                            Text(
                              context.translator.profilePicture,
                              textAlign: TextAlign.center,
                            ),
                            ThemeSizedBox.height20,
                            TextFormField(
                              autofocus: true,
                              decoration: InputDecoration(
                                label: Text(context.translator.userName),
                              ),
                              validator: (userName) =>
                                  _userNameValidator(
                                    userName,
                                    context.translator.userNameInvalid,
                                  ),
                            ),
                            if (!userState.isEmailAddressAvailable)
                              ThemeSizedBox.height10,
                            if (!userState.isEmailAddressAvailable)
                              TextFormField(
                                decoration: InputDecoration(
                                  label: Text(context.translator.emailAddress),
                                ),
                                validator: (emailAddress) =>
                                    _emailValidator(
                                      emailAddress,
                                      context.translator.invalidEmailAddress,
                                    ),
                              ),
                            if (!userState.isPhoneNumberAvailable)
                              ThemeSizedBox.height10,
                            if (!userState.isPhoneNumberAvailable)
                              PhoneInput(
                                key: _phoneInputKey,
                              ),
                            ThemeSizedBox.height20,
                            ElevatedButton(
                              onPressed: () {
                                _submit(!userState.isPhoneNumberAvailable);
                              },
                              child: Text(context.translator.save),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
              ));
}
