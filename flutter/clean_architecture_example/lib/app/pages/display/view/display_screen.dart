import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';

/// A video/picture/gif at the center of the screen with height which is
/// half the user’s phone size and this comes
/// from the API : https://random.dog/woof.json
class DisplayScreen extends StatelessWidget {
  const DisplayScreen({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData().scaffoldBackgroundColor,
        extendBody: true,
        extendBodyBehindAppBar: true,
        floatingActionButton: BlocBuilder<DisplayBloc, DisplayState>(
          builder: (_, state) => (state.showFloatingActionButton)
              ? FloatingActionButton(
                  onPressed: () {
                    context.read<DisplayBloc>().add(const SaveUserImage());
                    context
                        .navigator()
                        .pushNamed(NavigatorsConstants().apiRoute);
                  },
                  backgroundColor: context
                      .themeData()
                      .floatingActionButtonTheme
                      .backgroundColor,
                  child: Icon(
                    Icons.navigate_next,
                    color: context.themeData().iconTheme.color,
                  ),
                )
              : const SizedBox.shrink(),
        ),
        body: SafeArea(
          child: BlocBuilder<DisplayBloc, DisplayState>(
            builder: (_, state) {
              final displayFiles = state.displayEntity;
              return Stack(
                children: [
                  if (state.displayStatus == PageStatus.loading)
                    const CenterCircularProgressbar(),
                  if (state.displayStatus == PageStatus.error)
                    RetryErrorWidget(
                      message: state.errorMessage ??
                          'Something went wrong, try again.',
                      onRetry: () {
                        context.read<DisplayBloc>().add(
                              const FetchFiles(),
                            );
                      },
                    ),
                  if (state.displayStatus == PageStatus.done &&
                      displayFiles != null)
                    FileWidget(
                      url: displayFiles.url,
                      fileType: displayFiles.getFileType(),
                    ),
                ],
              );
            },
          ),
        ),
      );
}
