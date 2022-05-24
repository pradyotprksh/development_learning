import 'package:flutter/material.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A widget which will be used to show a loading indicator stating that
/// some background process is currently running and user
/// needs to wait.
class WidgetsCircularLoadingIndicator extends StatelessWidget {
  /// [message] = An optional message value which will be used to show a
  /// message below the indicator, if required.
  const WidgetsCircularLoadingIndicator({
    this.message,
    super.key,
  });

  final String? message;

  @override
  Widget build(BuildContext context) => SizedBox(
        height: double.infinity,
        width: double.infinity,
        child: Center(
          child: Card(
            color: context.themeData().cardColor,
            elevation: 3,
            shadowColor: context.themeData().shadowColor,
            shape: const RoundedRectangleBorder(
              borderRadius: BorderRadius.all(
                Radius.circular(
                  10,
                ),
              ),
            ),
            child: Padding(
              padding: ThemesEdgeInsets().all15,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                mainAxisSize: MainAxisSize.min,
                children: [
                  CircularProgressIndicator(
                    valueColor: AlwaysStoppedAnimation<Color>(
                      context.themeData().indicatorColor,
                    ),
                  ),
                  if (message != null && message != '')
                    ThemesBox().height15,
                  if (message != null && message != '')
                    Text(
                      message!,
                      style: context.themeData().textTheme.bodyText1,
                      textAlign: TextAlign.center,
                    ),
                ],
              ),
            ),
          ),
        ),
      );
}
