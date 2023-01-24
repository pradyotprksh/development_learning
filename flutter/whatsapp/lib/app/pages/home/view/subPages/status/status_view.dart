import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get/get.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusView extends StatelessWidget {
  const StatusView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
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
                      );
                    } else {
                      context.navigator.pushNamed(Routes.addStatusCamera);
                    }
                  },
                  leading: Stack(
                    alignment: Alignment.bottomRight,
                    children: [
                      Obx(
                        () => StatusProfileImageWidget(
                          profileImage:
                              userState.userDetails?.value?.profileImage ?? '',
                          totalStatusCount: statusState
                                  .currentUserStatus?.statusDetails.length ??
                              0,
                          readStatusCount: statusState
                                  .currentUserStatus?.statusDetails.length ??
                              0,
                        ),
                      ),
                      if (statusState
                              .currentUserStatus?.statusDetails.isEmpty ==
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

                    return Obx(
                      () {
                        if (userDetails.value != null &&
                            statusDetails.isNotEmpty) {
                          return ListTile(
                            onTap: () {
                              _openStatusSheet(
                                context,
                                statusState.otherStatus[index],
                              );
                            },
                            leading: StatusProfileImageWidget(
                              profileImage:
                                  userDetails.value?.profileImage ?? '',
                              totalStatusCount: statusDetails.length,
                              readStatusCount: statusDetails.length,
                            ),
                            title: Text(
                              userDetails.value?.name ?? '',
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

  void _openStatusSheet(
    BuildContext context,
    UserWithSingleStatusDetails userWithSingleStatusDetails,
  ) {
    showModalBottomSheet<void>(
      context: context,
      isScrollControlled: true,
      builder: (_) => StatusDetailsWidget(
        statusDetails: userWithSingleStatusDetails,
      ),
    );
  }
}
