import 'package:firebase_ui_auth/firebase_ui_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:image_cropper/image_cropper.dart';
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

  void _submit(
    bool getTextFieldEmailAddress,
    bool getTextFieldPhoneNumber,
    UserDetailsState currentState,
    BuildContext context,
  ) {
    var isFormValid = _formKey.currentState?.validate() == true;
    var isPhoneNumberValid = !getTextFieldPhoneNumber ||
        _phoneInputKey.currentState?.formKey.currentState?.validate() == true;

    if (isFormValid && isPhoneNumberValid) {
      final userName = _userNameController.text;
      final emailAddress = getTextFieldEmailAddress
          ? _emailAddressController.text
          : currentState.emailAddress;
      final phoneNumber = getTextFieldPhoneNumber
          ? _phoneInputKey.currentState?.phoneNumber
          : currentState.phoneNumber;

      context.read<UserDetailsBloc>().add(
            UserDetailsFormEvent(
              userName: userName,
              emailAddress: emailAddress,
              phoneNumber: phoneNumber,
              profilePic: currentState.profilePicImage,
            ),
          );
    }
  }

  final _formKey = GlobalKey<FormState>();
  final _phoneInputKey = GlobalKey<PhoneInputState>();
  final _userNameController = TextEditingController();
  final _emailAddressController = TextEditingController();

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<UserDetailsBloc, UserDetailsState>(
          builder: (_, userState) => Scaffold(
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
                            ImagePickerWidget(
                              image: (path) {
                                context.read<UserDetailsBloc>().add(
                                      UploadProfileImage(
                                        imagePath: path,
                                      ),
                                    );
                              },
                              cropStyle: CropStyle.circle,
                              aspectRatioPresets: const [
                                CropAspectRatioPreset.square,
                              ],
                              child: CachedNetworkImageWidget(
                                imageUrl: userState.profilePicImage,
                                placeholder: CircleAvatar(
                                  radius: 80,
                                  backgroundColor:
                                      context.themeData.primaryColor,
                                  backgroundImage: const AssetImage(
                                    AssetsPath.defaultAvatar,
                                  ),
                                ),
                                height: 160,
                                width: 160,
                                showProgressIndicator:
                                    userState.imageUploadStatus ==
                                        ImageUploadStatus.uploading,
                                clipToCircle: true,
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
                              validator: (userName) => _userNameValidator(
                                userName,
                                context.translator.userNameInvalid,
                              ),
                              keyboardType: TextInputType.text,
                              controller: _userNameController,
                            ),
                            if (!userState.isEmailAddressAvailable)
                              ThemeSizedBox.height10,
                            if (!userState.isEmailAddressAvailable)
                              TextFormField(
                                decoration: InputDecoration(
                                  label: Text(context.translator.emailAddress),
                                ),
                                validator: (emailAddress) => _emailValidator(
                                  emailAddress,
                                  context.translator.invalidEmailAddress,
                                ),
                                keyboardType: TextInputType.emailAddress,
                                controller: _emailAddressController,
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
                                _submit(
                                  !userState.isEmailAddressAvailable,
                                  !userState.isPhoneNumberAvailable,
                                  userState,
                                  context,
                                );
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
