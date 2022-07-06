import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';

/// Screen 2 is a list view with several posts as a ListTile in UI
/// (title: title, subtitle:body).
/// (A POST has two things: a title and a body)
/// These posts will come from the API:
/// http://jsonplaceholder.typicode.com/posts
class ApiScreen extends StatelessWidget {
  const ApiScreen({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData().scaffoldBackgroundColor,
        appBar: AppBar(
          backgroundColor: context.themeData().appBarTheme.backgroundColor,
        ),
        body: SafeArea(
          child: BlocBuilder<ApiBloc, ApiState>(
            builder: (_, state) {
              final apiEntity = state.apiEntity;
              return Stack(
                children: [
                  if (state.apiStatus == PageStatus.loading)
                    const CenterCircularProgressbar(),
                  if (state.apiStatus == PageStatus.error)
                    RetryErrorWidget(
                      message: state.errorMessage ??
                          'Something went wrong, try again.',
                      onRetry: () {
                        context.read<ApiBloc>().add(
                              const FetchDetails(),
                            );
                      },
                    ),
                  if (state.apiStatus == PageStatus.done && apiEntity != null)
                    ListView.separated(
                      itemCount: apiEntity.length,
                      padding: const EdgeInsets.all(0),
                      separatorBuilder: (_, __) => const Divider(),
                      itemBuilder: (_, index) {
                        final item = apiEntity[index];
                        return ListTile(
                          title: Text(
                            item.title,
                            style: context.themeData().textTheme.titleMedium,
                          ),
                          subtitle: Text(
                            item.title,
                            style: context.themeData().textTheme.caption,
                          ),
                        );
                      },
                    ),
                ],
              );
            },
          ),
        ),
      );
}
