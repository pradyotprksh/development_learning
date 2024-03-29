import 'package:cached_network_image/cached_network_image.dart';
import 'package:fews/app/app.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class HomeView extends StatefulWidget {
  const HomeView({super.key});

  @override
  State<HomeView> createState() => _HomeViewState();
}

class _HomeViewState extends State<HomeView> {
  final ScrollController _scrollController = ScrollController();

  @override
  void initState() {
    super.initState();
    _scrollController.addListener(_checkEndReached);
  }

  @override
  void dispose() {
    _scrollController
      ..removeListener(_checkEndReached)
      ..dispose();
    super.dispose();
  }

  void _checkEndReached() {
    if (_scrollController.position.pixels >=
        _scrollController.position.maxScrollExtent) {}
  }

  @override
  Widget build(BuildContext context) => BlocListener<HomeBloc, HomeState>(
        listener: (_, homeState) {
          if (homeState.pageState == PageState.error) {
            context.replaceAndShowSnackBar(
              homeState.errorMessage ?? context.translator.unknownError,
              SnackBarAction(
                label: context.translator.retry,
                onPressed: () {
                  context.read<HomeBloc>().add(
                        GetNews(
                          pageNumber: homeState.pageNumber,
                          language: context.localizations.languageCode,
                        ),
                      );
                },
                textColor: context.themeData.snackBarTheme.actionTextColor,
              ),
            );
          }
        },
        child: Scaffold(
          backgroundColor: context.themeData.scaffoldBackgroundColor,
          appBar: AppBar(
            title: Text(
              context.translator.applicationName,
            ),
            backgroundColor: context.themeData.appBarTheme.backgroundColor,
            actions: [
              IconButton(
                onPressed: () {
                  context.read<HomeBloc>().add(
                        GetNews(
                          language: context.localizations.languageCode,
                        ),
                      );
                },
                icon: Icon(
                  Icons.refresh,
                  color: context.themeData.appBarTheme.iconTheme?.color,
                ),
              ),
            ],
          ),
          body: BlocBuilder<HomeBloc, HomeState>(
            builder: (_, homeState) => Stack(
              children: [
                NotificationListener<ScrollEndNotification>(
                  onNotification: (notification) {
                    if (notification.metrics.extentAfter == 0) {
                      context.read<HomeBloc>().add(
                            UpdatePage(
                              language: context.localizations.languageCode,
                            ),
                          );
                    }
                    return true;
                  },
                  child: GridView.builder(
                    itemCount: homeState.newsData.length,
                    padding: ThemeEdgeInsets.top15Bottom15,
                    gridDelegate:
                        const SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: 2,
                    ),
                    itemBuilder: (_, index) {
                      final newsDetails = homeState.newsData[index];

                      return Padding(
                        padding: ThemeEdgeInsets.all10,
                        child: InkWell(
                          onTap: () {
                            context.navigator.pushNamed(
                              Routes.detailsRoute,
                              arguments: newsDetails.imageUrl,
                            );
                          },
                          child: GridTile(
                            header: GridTileBar(
                              backgroundColor: Colors.black45,
                              title: Text(
                                newsDetails.title ?? '',
                              ),
                            ),
                            footer: GridTileBar(
                              backgroundColor: Colors.black45,
                              title: Text(
                                newsDetails.description ?? '',
                              ),
                            ),
                            child: CachedNetworkImage(
                              imageUrl: newsDetails.imageUrl ?? '',
                              height: 100,
                              width: 100,
                              fit: BoxFit.cover,
                              errorWidget: (_, __, dynamic ___) => Icon(
                                Icons.error,
                                color: context.themeData.colorScheme.error,
                              ),
                            ),
                          ),
                        ),
                      );
                    },
                  ),
                ),
                if (homeState.pageState == PageState.loading)
                  const CircularProgressIndicatorWidget()
              ],
            ),
          ),
        ),
      );
}
