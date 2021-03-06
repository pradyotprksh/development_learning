import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A Widget to show an error note with a refresh button, this will be used
/// when there is an error and a refresh is required.
class WidgetsErrorRefresh extends StatelessWidget {
  /// [onRefresh] = A click event which will be called when the refresh
  /// button is clicked.
  const WidgetsErrorRefresh({
    required this.onRefresh,
    super.key,
  });

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
                  context.localizationValues().formRefreshMessage,
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
