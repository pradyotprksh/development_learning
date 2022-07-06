import 'package:flutter/material.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';

/// An error widget to be shown when some error occured and wants the
/// user to retry with a message.
class RetryErrorWidget extends StatelessWidget {
  const RetryErrorWidget({
    super.key,
    required this.message,
    required this.onRetry,
  });

  final String message;
  final Function() onRetry;

  @override
  Widget build(BuildContext context) => Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              message,
              style: context.themeData().textTheme.bodyMedium,
              textAlign: TextAlign.center,
            ),
            const SizedBox(
              height: 15,
            ),
            IconButton(
              onPressed: onRetry,
              icon: Icon(
                Icons.refresh,
                color: context.themeData().iconTheme.color,
              ),
            ),
          ],
        ),
      );
}
