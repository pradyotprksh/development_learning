import 'package:flutter/material.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';

/// A progress indicator which will be shown on top of the screen and
/// in the center.
class CenterCircularProgressbar extends StatelessWidget {
  const CenterCircularProgressbar({
    super.key,
    this.message,
  });

  final String? message;

  @override
  Widget build(BuildContext context) => SizedBox(
        height: double.infinity,
        width: double.infinity,
        child: Center(
          child: Card(
            color: Colors.white,
            elevation: 3,
            shape: const RoundedRectangleBorder(
              borderRadius: BorderRadius.all(
                Radius.circular(
                  10,
                ),
              ),
            ),
            child: Padding(
              padding: const EdgeInsets.all(15),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                mainAxisSize: MainAxisSize.min,
                children: [
                  CircularProgressIndicator(
                    valueColor: AlwaysStoppedAnimation<Color>(
                      context.themeData().progressIndicatorTheme.color ??
                          context.themeData().primaryColor,
                    ),
                  ),
                  if (message != null)
                    const SizedBox(
                      height: 15,
                    ),
                  if (message != null)
                    Text(
                      message!,
                      style: context
                          .themeData()
                          .textTheme
                          .bodyMedium
                          ?.copyWith(color: Colors.black),
                      textAlign: TextAlign.center,
                    )
                ],
              ),
            ),
          ),
        ),
      );
}
