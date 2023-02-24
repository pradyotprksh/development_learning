import 'package:firebase_ui_auth/firebase_ui_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:whatsapp/app/app.dart';

class UserDetailsView extends StatelessWidget {
  UserDetailsView({super.key});

  String? _pinValidator(String? pin, String errorMessage) {
    if (pin != null) {
      if (pin.length != 8) {
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
  ) async {
    final userDetailsBloc = context.read<UserDetailsBloc>();

    var isFormValid = _formKey.currentState?.validate() == true;
    var isPhoneNumberValid = !getTextFieldPhoneNumber ||
        _phoneInputKey.currentState?.formKey.currentState?.validate() == true;

    if (isFormValid && isPhoneNumberValid) {
      final userName = _userNameController.text;
      final pin = _pinController.text;
      final emailAddress = getTextFieldEmailAddress
          ? _emailAddressController.text
          : currentState.emailAddress;
      final phoneNumber = getTextFieldPhoneNumber
          ? _phoneInputKey.currentState?.phoneNumber
          : currentState.phoneNumber;

      userDetailsBloc.add(
        UserDetailsFormEvent(
          userName: userName,
          emailAddress: emailAddress,
          phoneNumber: phoneNumber,
          pin: pin,
          profilePic: currentState.profilePicImage,
        ),
      );
    }
  }

  final _formKey = GlobalKey<FormState>();
  final _phoneInputKey = GlobalKey<PhoneInputState>();
  final _userNameController = TextEditingController();
  final _emailAddressController = TextEditingController();
  final _pinController = TextEditingController();

  @override
  Widget build(BuildContext context) =>
      BlocConsumer<UserDetailsBloc, UserDetailsState>(
        listener: (_, userState) {
          if (userState.pageState == PageState.success) {
            context.navigator.pushNamedAndRemoveUntil(
              Routes.homeRoute,
              (route) => false,
            );
          }
        },
        builder: (_, userState) => Scaffold(
          backgroundColor: context.themeData.scaffoldBackgroundColor,
          extendBody: true,
          extendBodyBehindAppBar: true,
          appBar: AppBar(
            backgroundColor: context.themeData.appBarTheme.backgroundColor,
            elevation: context.themeData.appBarTheme.elevation,
          ),
          body: SafeArea(
            child: Form(
              key: _formKey,
              onWillPop: () async => false,
              child: Stack(
                alignment: Alignment.center,
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
                        child: Center(
                          child: UserImageWidget(
                            profileImage: userState.profilePicImage,
                            currentMood: null,
                            isOnline: null,
                            userId: '',
                            enableAction: false,
                            size: 160,
                            showProgressIndicator:
                                userState.imageUploadStatus ==
                                    ImageUploadStatus.uploading,
                            useAvatarAsProfile: false,
                          ),
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
                            AppUtilsMethods.lengthShouldBeGraterThan(
                          userName,
                          5,
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
                      ThemeSizedBox.height10,
                      const Divider(),
                      ThemeSizedBox.height10,
                      Text(
                        context.translator.pinTitle,
                        textAlign: TextAlign.center,
                      ),
                      ThemeSizedBox.height10,
                      PinInputWidget(
                        validator: (value) => _pinValidator(
                          value,
                          context.translator.invalidPin,
                        ),
                        pinController: _pinController,
                      ),
                      ThemeSizedBox.height10,
                      Text(
                        context.translator.pinNote,
                        textAlign: TextAlign.center,
                        style: context.themeData.textTheme.bodySmall,
                      ),
                      ThemeSizedBox.height10,
                      const Divider(),
                      ThemeSizedBox.height20,
                      ElevatedButton(
                        onPressed: (userState.pageState == PageState.loading)
                            ? null
                            : () {
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
                  if (userState.pageState == PageState.loading)
                    const CircularProgressIndicatorWidget(),
                ],
              ),
            ),
          ),
        ),
      );
}
