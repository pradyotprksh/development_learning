import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:screenshot_callback/screenshot_callback.dart';
import 'package:whatsapp/app/app.dart';

class HomeView extends StatefulWidget {
  const HomeView({super.key});

  @override
  State<HomeView> createState() => _HomeViewState();
}

class _HomeViewState extends State<HomeView>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;
  final _screenshotCallback = ScreenshotCallback();

  @override
  void initState() {
    super.initState();
    _tabController = TabController(vsync: this, length: 3);
    _screenshotCallback.addListener(
      () {
        context.read<UtilitiesBloc>().add(
              ScreenshotTaken(
                context.routeSettings?.name,
                context.routeSettings?.arguments,
              ),
            );
      },
    );
  }

  @override
  void dispose() {
    _tabController.dispose();
    _screenshotCallback.dispose();
    super.dispose();
  }

  void _openPageBasedOnSelection(BuildContext context, HomeMenuItems value) {
    switch (value) {
      case HomeMenuItems.newGroup:
        context.navigator.pushNamed(Routes.newGroup);
        break;
      case HomeMenuItems.newBroadcast:
        break;
      case HomeMenuItems.linkedDevices:
        break;
      case HomeMenuItems.savedMessages:
        context.navigator.pushNamed(Routes.savedMessages);
        break;
      case HomeMenuItems.payments:
        break;
      case HomeMenuItems.statusPrivacy:
        break;
      case HomeMenuItems.clearCallLog:
        context.read<HomeBloc>().add(const DeleteCallLogs());
        break;
      case HomeMenuItems.settings:
        context.navigator.pushNamed(Routes.settings);
        break;
    }
  }

  @override
  Widget build(BuildContext context) => WillPopScope(
        onWillPop: () async {
          if (_tabController.index != 0) {
            _tabController.animateTo(0);
            return Future.value(false);
          }
          return Future.value(true);
        },
        child: MultiBlocListener(
          listeners: [
            BlocListener<HomeBloc, HomeState>(
              listener: (_, homeState) async {
                if (homeState.askForPinConfirmation) {
                  final homeBloc = context.read<HomeBloc>();
                  final isVerified = await context.navigator
                      .pushNamed(Routes.pinConfirmation) as bool;
                  if (isVerified) {
                    homeBloc.add(const PinVerified());
                  }
                }
              },
            ),
            BlocListener<AuthenticationBloc, AuthenticationState>(
              listener: (_, authenticationState) {
                if (authenticationState.authenticationState ==
                    AuthenticationStatus.unauthenticated) {
                  context.navigator.pushNamedAndRemoveUntil(
                    Routes.authenticateRoute,
                    (route) => false,
                  );
                } else {
                  final userBloc = context.read<UserBloc>();
                  if (userBloc.state.userDetails == null) {
                    userBloc.add(const FetchUserDetails());
                  }
                }
              },
            ),
            BlocListener<UserBloc, UserState>(
              listener: (_, userState) {
                if (userState is UserDataNotAvailable) {
                  context.navigator.pushNamedAndRemoveUntil(
                    Routes.userDetails,
                    (route) => false,
                  );
                }
                if (userState is UserDetailsAvailable) {
                  context.read<HomeBloc>().add(
                        AskForPinConfirmation(
                          userState.userDetails?.lastPinConfirmationTimeStamp,
                        ),
                      );
                }
              },
            ),
            BlocListener<UtilitiesBloc, UtilitiesState>(
              listener: (_, utilitiesState) {
                if (!utilitiesState.isNetworkAvailable()) {
                  context.replaceAndShowSnackBar(
                    context.translator.noInternet,
                    null,
                  );
                } else {
                  context.clearSnackBars();
                }
              },
            ),
          ],
          child: BlocBuilder<HomeBloc, HomeState>(
            buildWhen: (previousState, currentState) => false,
            builder: (_, homeState) => Scaffold(
              backgroundColor: context.themeData.scaffoldBackgroundColor,
              appBar: AppBar(
                title: Text(
                  context.translator.applicationName,
                ),
                actions: [
                  if (AppDetails.isPhone)
                    IconButton(
                      onPressed: () {
                        context.navigator.pushNamed(Routes.addStatusCamera);
                      },
                      icon: const Icon(
                        Icons.camera,
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
                        value: HomeMenuItems.savedMessages,
                        child: Text(
                          context.translator.savedMessages,
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
          ),
        ),
      );
}
