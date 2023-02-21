import 'package:firebase_ui_auth/firebase_ui_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:whatsapp/app/app.dart';

class ProfileView extends StatelessWidget {
  ProfileView({super.key});

  void _submit(
    BuildContext context,
  ) async {
    var isFormValid = _formKey.currentState?.validate() == true;
    if (isFormValid) {
      final currentUsername = context.read<UserBloc>().state.userDetails?.name;
      final editedUsername = _userNameController.text;
      if (currentUsername != editedUsername) {
        context.read<ProfileBloc>().add(
              UpdateUserName(
                editedUsername,
              ),
            );
      } else {
        context.read<ProfileBloc>().add(
              const EnableDisableUserNameForm(),
            );
      }
    }
  }

  final _userNameFocusNode = FocusNode();
  final _formKey = GlobalKey<FormState>();
  final _userNameController = TextEditingController();

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(),
        body: BlocBuilder<UserBloc, UserState>(
          builder: (_, userState) {
            _userNameController.text = userState.userDetails?.name ?? '';
            return ListView(
              children: [
                ThemeSizedBox.height20,
                ImagePickerWidget(
                  image: (path) {
                    context.read<ProfileBloc>().add(
                          UpdateUserProfileImage(path),
                        );
                  },
                  cropStyle: CropStyle.circle,
                  aspectRatioPresets: const [
                    CropAspectRatioPreset.square,
                  ],
                  child: Align(
                    alignment: Alignment.center,
                    child: Stack(
                      alignment: Alignment.bottomRight,
                      children: [
                        BlocBuilder<ProfileBloc, ProfileState>(
                          builder: (_, profileState) => UserImageWidget(
                            profileImage:
                                userState.userDetails?.profileImage ?? '',
                            userId: userState.userDetails?.userId ?? '',
                            isOnline: null,
                            currentMood: null,
                            size: 150,
                            enableAction: false,
                          ),
                        ),
                        Container(
                          padding: ThemeEdgeInsets.all10,
                          decoration: BoxDecoration(
                            color: context.themeData.primaryColor,
                            borderRadius: BorderRadius.circular(
                              50,
                            ),
                          ),
                          child: const Icon(
                            Icons.edit,
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
                ThemeSizedBox.height20,
                Padding(
                  padding: ThemeEdgeInsets.all15,
                  child: BlocBuilder<ProfileBloc, ProfileState>(
                    builder: (_, profileState) {
                      if (profileState.enableUsernameEdit) {
                        _userNameFocusNode.nextFocus();
                      } else {
                        _userNameFocusNode.unfocus();
                      }

                      return Row(
                        children: [
                          Flexible(
                            child: Form(
                              key: _formKey,
                              child: TextFormField(
                                controller: _userNameController,
                                focusNode: _userNameFocusNode,
                                enabled: profileState.enableUsernameEdit,
                                textInputAction: TextInputAction.done,
                                onFieldSubmitted: (value) {
                                  _submit(context);
                                },
                                validator: (userName) =>
                                    AppUtilsMethods.lengthShouldBeGraterThan(
                                  userName,
                                  5,
                                  context.translator.userNameInvalid,
                                ),
                              ),
                            ),
                          ),
                          ThemeSizedBox.width10,
                          IconButton(
                            onPressed: () {
                              context.read<ProfileBloc>().add(
                                    const EnableDisableUserNameForm(),
                                  );
                            },
                            icon: Icon(
                              profileState.enableUsernameEdit
                                  ? Icons.keyboard_arrow_down
                                  : Icons.edit,
                              color: context.themeData.iconTheme.color,
                            ),
                          ),
                        ],
                      );
                    },
                  ),
                ),
                ListTile(
                  onTap: () async {
                    final profileBloc = context.read<ProfileBloc>();
                    final emoji =
                        await ShowEmojiBottomSheet.showEmojiBottomSheet(
                      context,
                      false,
                    );
                    if (emoji != null) {
                      profileBloc.add(
                        UpdateCurrentMood(emoji.emoji),
                      );
                    }
                  },
                  title: Text(
                    context.translator.howAreYouFeelingToday,
                  ),
                  subtitle: Text(
                    userState.userDetails?.currentMood ?? '',
                  ),
                ),
                ListTile(
                  title: Text(
                    context.translator.emailAddress,
                  ),
                  subtitle: Text(
                    userState.userDetails?.emailId ?? '',
                  ),
                  trailing: userState.userDetails?.isEmailVerified == true
                      ? Icon(
                          Icons.verified,
                          color: context.themeData.primaryColor,
                        )
                      : ElevatedButton(
                          onPressed: () {
                            context.navigator
                                .pushNamed(Routes.emailVerification);
                          },
                          child: Text(
                            context.translator.verificationNeeded,
                          ),
                        ),
                ),
                ListTile(
                  title: Text(
                    context.translator.phoneNumber,
                  ),
                  subtitle: Text(
                    userState.userDetails?.phoneNumber ?? '',
                  ),
                  trailing: userState.userDetails?.isPhoneNumberVerified == true
                      ? Icon(
                          Icons.verified,
                          color: context.themeData.primaryColor,
                        )
                      : PhoneVerificationButton(
                          label: context.translator.verificationNeeded,
                          action: AuthAction.none,
                        ),
                ),
                if (userState.userDetails?.isEmailVerified != true ||
                    userState.userDetails?.isPhoneNumberVerified != true)
                  Padding(
                    padding: ThemeEdgeInsets.all15,
                    child: Text(
                      context.translator.verificationNote,
                      style: context.themeData.textTheme.labelSmall,
                    ),
                  ),
                const Divider(),
                Padding(
                  padding: ThemeEdgeInsets.all15,
                  child: ElevatedButton(
                    onPressed: () {
                      context.navigator.pushNamed(Routes.firebaseProfile);
                    },
                    child: Text(
                      context.translator.moreOptions,
                    ),
                  ),
                ),
              ],
            );
          },
        ),
      );
}
