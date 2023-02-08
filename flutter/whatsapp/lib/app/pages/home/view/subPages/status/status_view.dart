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
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      floatingActionButton: Wrap(
        direction: Axis.vertical,
        children: [
          FloatingActionButton(
            onPressed: () {
              context.navigator.pushNamed(Routes.addStatusText);
            },
            mini: true,
            heroTag: Icons.edit.toString(),
            child: const Icon(Icons.edit),
          ),
          ThemeSizedBox.height10,
          FloatingActionButton(
            onPressed: () {
              context.navigator.pushNamed(Routes.addStatusCamera);
            },
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
                onTap: () {
                  if (statusState.currentUserStatus != null) {
                    _openStatusSheet(
                      context,
                      statusState.currentUserStatus!,
                      userState.userDetails,
                      true,
                    );
                  } else {
                    context.navigator.pushNamed(Routes.addStatusCamera);
                  }
                },
                leading: Stack(
                  alignment: Alignment.bottomRight,
                  children: [
                    StatusProfileImageWidget(
                      profileImage: userState.userDetails?.profileImage ?? '',
                      totalStatusCount:
                          statusState.currentUserStatus?.statusDetails.length ??
                              0,
                      readStatusCount:
                          statusState.currentUserStatus?.statusDetails.length ??
                              0,
                      userId: statusState.currentUserStatus?.userId ?? '',
                    ),
                    if (statusState.currentUserStatus?.statusDetails.isEmpty ==
                        true)
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
                    stream: userDetails,
                    builder: (_, snapshot) {
                      final userDetails = snapshot.data;
                      if (userDetails != null && statusDetails.isNotEmpty) {
                        return ListTile(
                          onTap: () {
                            _openStatusSheet(
                              context,
                              statusState.otherStatus[index],
                              userDetails,
                              false,
                            );
                          },
                          leading: StatusProfileImageWidget(
                            profileImage: userDetails.profileImage ?? '',
                            totalStatusCount: statusDetails.length,
                            readStatusCount: statusDetails.length,
                            userId: userDetails.userId,
                          ),
                          title: Text(
                            userDetails.name ?? '',
                          ),
                          subtitle: Text(
                            AppUtilsMethods.timeAgo(
                              statusDetails.first.createdOnTimeStamp,
                              context,
                            ),
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

  void _openStatusSheet(
    BuildContext context,
    UserWithSingleStatusDetails userWithSingleStatusDetails,
    UserDetails? userDetails,
    bool isCurrentUser,
  ) {
    showModalBottomSheet<void>(
      context: context,
      isScrollControlled: true,
      builder: (_) => StatusDetailsWidget(
        statusDetails: userWithSingleStatusDetails,
        userDetails: userDetails,
        isCurrentUser: isCurrentUser,
        isSeen: (statusId) {
          context.read<StatusBloc>().add(
                MarkStatusAsSeen(
                  statusId,
                ),
              );
        },
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}
