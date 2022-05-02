import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A Widget to show an error note with a refresh button, this will be used
/// when there is an error and a refresh is required.
class WidgetsErrorRefresh extends StatelessWidget {
  /// [onRefresh] = A click event which will be called when the refresh
  /// button is clicked.
  const WidgetsErrorRefresh({
    required this.onRefresh,
    Key? key,
  }) : super(key: key);

  final Function() onRefresh;

  @override
  Widget build(BuildContext context) => Padding(
        padding: ThemesEdgeInsets().left20Right20,
        child: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              WidgetsPrimaryNoteBox(
                child: Text(
                  'Might be some issue with form. Please try again after sometime or refresh the page.',
                  style: context.themeData().textTheme.caption,
                  textAlign: TextAlign.center,
                ),
              ),
              ThemesBox().height15,
              IconButton(
                icon: Icon(
                  Icons.refresh,
                  color: context.themeData().iconTheme.color,
                ),
                onPressed: onRefresh,
              ),
            ],
          ),
        ),
      );
}
