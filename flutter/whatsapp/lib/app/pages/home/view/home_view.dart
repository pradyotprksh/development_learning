import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class HomeView extends StatefulWidget {
  const HomeView({super.key});

  @override
  State<HomeView> createState() => _HomeViewState();
}

class _HomeViewState extends State<HomeView>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(vsync: this, length: 3);
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  void _openPageBasedOnSelection(BuildContext context, HomeMenuItems value) {
    switch (value) {
      case HomeMenuItems.newGroup:
        break;
      case HomeMenuItems.newBroadcast:
        break;
      case HomeMenuItems.linkedDevices:
        break;
      case HomeMenuItems.starredMessages:
        break;
      case HomeMenuItems.payments:
        break;
      case HomeMenuItems.statusPrivacy:
        break;
      case HomeMenuItems.clearCallLog:
        break;
      case HomeMenuItems.settings:
        context.navigator.pushNamed(Routes.settings);
        break;
    }
  }

  @override
  Widget build(BuildContext context) => BlocListener<UserBloc, UserState>(
        listener: (_, userState) {
          if (userState is UserDataNotAvailable) {
            context.navigator.pushNamedAndRemoveUntil(
              Routes.userDetails,
              (route) => false,
            );
          }
        },
        child: Scaffold(
          backgroundColor: context.themeData.scaffoldBackgroundColor,
          appBar: AppBar(
            title: Text(
              context.translator.applicationName,
            ),
            actions: [
              IconButton(
                onPressed: () {
                  context.navigator.pushNamed(Routes.addStatusCamera);
                },
                icon: const Icon(
                  Icons.camera,
                ),
              ),
              IconButton(
                onPressed: () {},
                icon: const Icon(
                  Icons.search,
                ),
              ),
              PopupMenuButton<HomeMenuItems>(
                onSelected: (item) {
                  _openPageBasedOnSelection(context, item);
                },
                color: context.themeData.popupMenuTheme.color,
                itemBuilder: (_) => [
                  PopupMenuItem(
                    value: HomeMenuItems.newGroup,
                    child: Text(
                      context.translator.newGroup,
                    ),
                  ),
                  PopupMenuItem(
                    value: HomeMenuItems.newBroadcast,
                    child: Text(
                      context.translator.newBroadcast,
                    ),
                  ),
                  PopupMenuItem(
                    value: HomeMenuItems.linkedDevices,
                    child: Text(
                      context.translator.linkedDevices,
                    ),
                  ),
                  PopupMenuItem(
                    value: HomeMenuItems.starredMessages,
                    child: Text(
                      context.translator.starredMessages,
                    ),
                  ),
                  PopupMenuItem(
                    value: HomeMenuItems.payments,
                    child: Text(
                      context.translator.payments,
                    ),
                  ),
                  PopupMenuItem(
                    value: HomeMenuItems.statusPrivacy,
                    child: Text(
                      context.translator.statusPrivacy,
                    ),
                  ),
                  PopupMenuItem(
                    value: HomeMenuItems.clearCallLog,
                    child: Text(
                      context.translator.clearCallLog,
                    ),
                  ),
                  PopupMenuItem(
                    value: HomeMenuItems.settings,
                    child: Text(
                      context.translator.settings,
                    ),
                  ),
                ],
                icon: const Icon(
                  Icons.more_vert,
                ),
              ),
            ],
            bottom: TabBar(
              controller: _tabController,
              tabs: [
                Tab(
                  text: context.translator.chats,
                ),
                Tab(
                  text: context.translator.status,
                ),
                Tab(
                  text: context.translator.calls,
                ),
              ],
            ),
          ),
          body: TabBarView(
            controller: _tabController,
            children: const [
              ChatsView(),
              StatusView(),
              CallsView(),
            ],
          ),
        ),
      );
}
