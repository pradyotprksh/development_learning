import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class StatusView extends StatelessWidget {
  const StatusView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        floatingActionButton: Wrap(
          direction: Axis.vertical,
          children: [
            FloatingActionButton(
              onPressed: () {
                context.navigator.pushNamed(Routes.addStatus);
              },
              mini: true,
              heroTag: Icons.edit.toString(),
              child: const Icon(Icons.edit),
            ),
            ThemeSizedBox.height10,
            FloatingActionButton(
              onPressed: () {},
              heroTag: Icons.camera.toString(),
              child: const Icon(Icons.camera),
            ),
          ],
        ),
        body: BlocBuilder<UserBloc, UserState>(
          builder: (_, userState) => BlocBuilder<StatusBloc, StatusState>(
            builder: (_, statusState) => ListView(
              children: [
                ListTile(
                  onTap: () {},
                  leading: Stack(
                    alignment: Alignment.bottomRight,
                    children: [
                      CachedNetworkImageWidget(
                        imageUrl: userState.userDetails?.profileImage ?? '',
                        placeholder: CircleAvatar(
                          radius: 20,
                          backgroundColor: context.themeData.primaryColor,
                          backgroundImage: const AssetImage(
                            AssetsPath.defaultAvatar,
                          ),
                        ),
                        height: 40,
                        width: 40,
                        clipToCircle: true,
                      ),
                      Icon(
                        Icons.add_circle,
                        size: 15,
                        color: context.themeData.primaryColor,
                      )
                    ],
                  ),
                  title: Text(
                    context.translator.myStatus,
                  ),
                  subtitle: Text(
                    context.translator.tapToAddStatus,
                  ),
                ),
                const Divider(),
              ],
            ),
          ),
        ),
      );
}
