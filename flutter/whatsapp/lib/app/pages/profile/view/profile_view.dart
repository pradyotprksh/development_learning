import 'package:firebase_ui_auth/firebase_ui_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:whatsapp/app/app.dart';

class ProfileView extends StatelessWidget {
  const ProfileView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(),
        body: BlocBuilder<UserBloc, UserState>(
          builder: (_, userState) => ListView(
            children: [
              ThemeSizedBox.height20,
              ImagePickerWidget(
                image: (path) {},
                cropStyle: CropStyle.circle,
                aspectRatioPresets: const [
                  CropAspectRatioPreset.square,
                ],
                child: Align(
                  alignment: Alignment.center,
                  child: Stack(
                    alignment: Alignment.bottomRight,
                    children: [
                      CachedNetworkImageWidget(
                        imageUrl: userState.userDetails?.profileImage ?? '',
                        placeholder: CircleAvatar(
                          radius: 25,
                          backgroundColor: context.themeData.primaryColor,
                          backgroundImage: const AssetImage(
                            AssetsPath.defaultAvatar,
                          ),
                        ),
                        height: 150,
                        width: 150,
                        clipToCircle: true,
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
              ListTile(
                title: Text(
                  context.translator.userName,
                ),
                subtitle: Text(
                  userState.userDetails?.name ?? '',
                ),
                trailing: IconButton(
                  onPressed: () {},
                  icon: Icon(
                    Icons.edit,
                    color: context.themeData.iconTheme.color,
                  ),
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
                          context.navigator.pushNamed(Routes.emailVerification);
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
                        action: AuthAction.link,
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
            ],
          ),
        ),
      );
}
