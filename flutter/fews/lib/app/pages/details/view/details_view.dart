import 'package:cached_network_image/cached_network_image.dart';
import 'package:fews/app/app.dart';
import 'package:flutter/material.dart';

class DetailsView extends StatelessWidget {
  const DetailsView({
    super.key,
    required this.imageUrl,
  });

  final String imageUrl;

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        extendBodyBehindAppBar: true,
        appBar: AppBar(
          backgroundColor: Colors.transparent,
          elevation: 0,
          leading: CloseButton(
            color: context.themeData.iconTheme.color,
          ),
        ),
        body: InteractiveViewer(
          minScale: 0.1,
          maxScale: double.infinity,
          child: CachedNetworkImage(
            imageUrl: imageUrl,
            width: double.infinity,
            height: double.infinity,
            fit: BoxFit.contain,
            errorWidget: (_, __, dynamic ___) => Icon(
              Icons.error,
              color: context.themeData.colorScheme.error,
            ),
          ),
        ),
      );
}
