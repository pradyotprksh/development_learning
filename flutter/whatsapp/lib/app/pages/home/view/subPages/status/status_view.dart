import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusView extends StatefulWidget {
  const StatusView({super.key});

  @override
  State<StatusView> createState() => _StatusViewState();
}

class _StatusViewState extends State<StatusView>
    with AutomaticKeepAliveClientMixin {
  @override
  Widget build(BuildContext context) {
    super.build(context);
    return Scaffold(
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
          builder: (_, statusState) => Column(
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
              ListView.builder(
                shrinkWrap: true,
                itemCount: statusState.otherStatus.length,
                itemBuilder: (_, index) {
                  final userDetails =
                      statusState.otherStatus[index].userDetails;
                  final statusDetails =
                      statusState.otherStatus[index].statusDetails;

                  return StreamBuilder<UserDetails?>(
                    stream: userDetails.stream,
                    builder: (_, snapshot) {
                      final userDetails = snapshot.data;
                      if (userDetails != null) {
                        return ListTile(
                          title: Text(
                            userDetails.name ?? '',
                          ),
                          subtitle: Text(
                            '${statusDetails.length}',
                          ),
                        );
                      } else {
                        return ThemeSizedBox.shrink;
                      }
                    },
                  );
                },
              ),
            ],
          ),
        ),
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}
