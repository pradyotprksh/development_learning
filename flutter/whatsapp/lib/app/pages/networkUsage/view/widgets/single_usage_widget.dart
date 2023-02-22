import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class SingleUsageWidget extends StatelessWidget {
  const SingleUsageWidget({
    super.key,
    required this.icon,
    required this.title,
    required this.upload,
    required this.download,
    required this.totalValue,
  });

  final IconData icon;
  final String title;
  final double upload;
  final double download;
  final double totalValue;

  @override
  Widget build(BuildContext context) => ListTile(
        leading: Icon(
          icon,
          color: context.themeData.iconTheme.color,
        ),
        title: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(title),
            Row(
              children: [
                Icon(
                  Icons.arrow_upward,
                  color: context.themeData.iconTheme.color,
                  size: 15,
                ),
                ThemeSizedBox.width5,
                Text(
                  upload.convertToComputerSize(),
                ),
                ThemeSizedBox.width10,
                Icon(
                  Icons.arrow_downward,
                  color: context.themeData.iconTheme.color,
                  size: 15,
                ),
                ThemeSizedBox.width5,
                Text(
                  download.convertToComputerSize(),
                ),
              ],
            ),
          ],
        ),
        subtitle: ClipRRect(
          borderRadius: const BorderRadius.all(
            Radius.circular(
              10,
            ),
          ),
          child: LinearProgressIndicator(
            value: ((upload + download) == 0 || totalValue == 0)
                ? 0
                : (upload + download) / totalValue,
            backgroundColor: context.themeData.highlightColor,
          ),
        ),
      );
}
