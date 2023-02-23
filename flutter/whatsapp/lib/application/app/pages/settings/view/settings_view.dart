import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';

class SettingsView extends StatelessWidget {
  const SettingsView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          actions: [
            IconButton(
              onPressed: () {
                final userDetails = context.read<UserBloc>().state.userDetails;
                context.navigator.pushNamed(
                  Routes.qrCode,
                  arguments: {
                    Keys.qrCodeData: AppUtilsMethods.getUserQrCode(
                      userDetails?.userId ?? '',
                    ),
                    Keys.userDetails: userDetails,
                  },
                );
              },
              icon: const Icon(
                Icons.qr_code,
              ),
            ),
            IconButton(
              onPressed: () {
                context.navigator.pushNamed(Routes.search);
              },
              icon: const Icon(
                Icons.search,
              ),
            ),
          ],
        ),
        body: ListView(
          children: [
            BlocBuilder<UserBloc, UserState>(
              builder: (_, userState) => ListTile(
                onTap: () {
                  final userId = userState.userDetails?.userId;
                  if (userId != null) {
                    context.navigator.pushNamed(
                      Routes.profile,
                      arguments: {
                        Keys.userId: userId,
                      },
                    );
                  }
                },
                contentPadding: ThemeEdgeInsets.all15,
                leading: UserImageWidget(
                  profileImage: userState.userDetails?.profileImage ?? '',
                  userId: userState.userDetails?.userId ?? '',
                  currentMood: userState.userDetails?.currentMood,
                  isOnline: userState.userDetails?.isOnline,
                  size: 50,
                  enableAction: false,
                  useAvatarAsProfile:
                      userState.userDetails?.useAvatarAsProfile ?? false,
                  avatarDetails: userState.userDetails?.avatarDetails,
                ),
                title: Text(
                  userState.userDetails?.name ?? '',
                ),
              ),
            ),
            const Divider(),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.key,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.account,
              ),
              subtitle: Text(
                context.translator.accountSubtitle,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.lock,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.privacy,
              ),
              subtitle: Text(
                context.translator.privacySubtitle,
              ),
            ),
            ListTile(
              onTap: () {
                context.navigator.pushNamed(Routes.avatar);
              },
              leading: Icon(
                Icons.face,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.avatar,
              ),
              subtitle: Text(
                context.translator.avatarSubtitle,
              ),
            ),
            ListTile(
              onTap: () {
                context.navigator.pushNamed(Routes.personaliseRoute);
              },
              leading: Icon(
                Icons.message,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.chats,
              ),
              subtitle: Text(
                context.translator.chatsSubtitle,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.notifications,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.notifications,
              ),
              subtitle: Text(
                context.translator.notificationsSubtitle,
              ),
            ),
            ListTile(
              onTap: () {
                context.navigator.pushNamed(Routes.storageData);
              },
              leading: Icon(
                Icons.pie_chart,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.storageData,
              ),
              subtitle: Text(
                context.translator.storageDataSubtitle,
              ),
            ),
            ListTile(
              onTap: () {
                context.navigator.pushNamed(Routes.personaliseRoute);
              },
              leading: Icon(
                Icons.language,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.appLanguage,
              ),
            ),
            ListTile(
              onTap: () {
                context.navigator.pushNamed(Routes.help);
              },
              leading: Icon(
                Icons.help,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.help,
              ),
              subtitle: Text(
                context.translator.helpSubtitle,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.people,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.inviteAFriend,
              ),
            ),
          ],
        ),
      );
}
