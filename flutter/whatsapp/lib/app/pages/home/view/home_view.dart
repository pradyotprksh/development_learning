import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/app/pages/home/view/subPages/sub_pages.dart';

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
              IconButton(
                onPressed: () {},
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
