import 'package:cached_network_image/cached_network_image.dart';
import 'package:fews/app/app.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class HomeView extends StatelessWidget {
  const HomeView({super.key});

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
                  context.read<HomeBloc>().add(const GetNews());
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
                ListView.builder(
                  itemCount: homeState.newsData.length,
                  padding: ThemeEdgeInsets.top15Bottom15,
                  itemBuilder: (_, index) {
                    final newsDetails = homeState.newsData[index];

                    return ListTile(
                      onTap: () {},
                      leading: CachedNetworkImage(
                        imageUrl: newsDetails.imageUrl ?? '',
                        height: 100,
                        width: 100,
                        fit: BoxFit.cover,
                        errorWidget: (_, __, dynamic ___) => Icon(
                          Icons.error,
                          color: context.themeData.colorScheme.error,
                        ),
                      ),
                      title: Text(
                        newsDetails.title ?? '',
                        style: context.themeData.textTheme.headlineMedium,
                      ),
                      subtitle: Text(
                        newsDetails.description ?? '',
                        style: context.themeData.textTheme.bodyMedium,
                      ),
                    );
                  },
                ),
                if (homeState.pageState == PageState.loading)
                  const CircularProgressIndicatorWidget()
              ],
            ),
          ),
        ),
      );
}
