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

  void _openPageBasedOnSelection(BuildContext context, MenuItems value) {
    switch (value) {
      case MenuItems.newGroup:
        break;
      case MenuItems.newBroadcast:
        break;
      case MenuItems.linkedDevices:
        break;
      case MenuItems.starredMessages:
        break;
      case MenuItems.payments:
        break;
      case MenuItems.statusPrivacy:
        break;
      case MenuItems.clearCallLog:
        break;
      case MenuItems.settings:
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
              PopupMenuButton<MenuItems>(
                onSelected: (item) {
                  _openPageBasedOnSelection(context, item);
                },
                color: context.themeData.popupMenuTheme.color,
                itemBuilder: (_) => [
                  PopupMenuItem(
                    value: MenuItems.newGroup,
                    child: Text(
                      context.translator.newGroup,
                    ),
                  ),
                  PopupMenuItem(
                    value: MenuItems.newBroadcast,
                    child: Text(
                      context.translator.newBroadcast,
                    ),
                  ),
                  PopupMenuItem(
                    value: MenuItems.linkedDevices,
                    child: Text(
                      context.translator.linkedDevices,
                    ),
                  ),
                  PopupMenuItem(
                    value: MenuItems.starredMessages,
                    child: Text(
                      context.translator.starredMessages,
                    ),
                  ),
                  PopupMenuItem(
                    value: MenuItems.payments,
                    child: Text(
                      context.translator.payments,
                    ),
                  ),
                  PopupMenuItem(
                    value: MenuItems.statusPrivacy,
                    child: Text(
                      context.translator.statusPrivacy,
                    ),
                  ),
                  PopupMenuItem(
                    value: MenuItems.clearCallLog,
                    child: Text(
                      context.translator.clearCallLog,
                    ),
                  ),
                  PopupMenuItem(
                    value: MenuItems.settings,
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
