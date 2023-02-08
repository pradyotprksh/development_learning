import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

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
                  arguments: <String, String>{
                    Keys.qrCodeData: AppUtilsMethods.getUserQrCode(
                      userDetails?.userId ?? '',
                    ),
                    Keys.imageUrl: userDetails?.profileImage ?? '',
                    Keys.placeHolderPath: AssetsPath.defaultAvatar,
                  },
                );
              },
              icon: const Icon(
                Icons.qr_code,
              ),
            ),
            IconButton(
              onPressed: () {},
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
                leading: CachedNetworkImageWidget(
                  imageUrl: userState.userDetails?.profileImage ?? '',
                  placeholder: CircleAvatar(
                    radius: 25,
                    backgroundColor: context.themeData.primaryColor,
                    backgroundImage: const AssetImage(
                      AssetsPath.defaultAvatar,
                    ),
                  ),
                  height: 50,
                  width: 50,
                  clipToCircle: true,
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
                Icons.notifications,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.notificationsAndSounds,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.security,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.privacyAndSecurity,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.pie_chart,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.dataAndStorage,
              ),
            ),
            ListTile(
              onTap: () {
                context.navigator.pushNamed(Routes.personaliseRoute);
              },
              leading: Icon(
                Icons.chat,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.chatSettings,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.devices,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.devices,
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
                context.translator.language,
              ),
            ),
            const Divider(),
          ],
        ),
      );
}
