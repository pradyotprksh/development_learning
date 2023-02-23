import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/core/core.dart' as listener;

class CachedNetworkImageWidget extends StatelessWidget {
  const CachedNetworkImageWidget({
    super.key,
    required this.imageUrl,
    required this.placeholder,
    this.showProgressIndicator = false,
    this.clipToCircle = false,
    this.errorWidget = ThemeSizedBox.shrink,
    this.tag,
    this.width,
    this.height,
    this.fit,
    this.failed,
  });

  final String imageUrl;
  final Widget placeholder;
  final Widget errorWidget;
  final double? width;
  final double? height;
  final bool showProgressIndicator;
  final bool clipToCircle;
  final String? tag;
  final BoxFit? fit;
  final Function()? failed;

  @override
  Widget build(BuildContext context) => Stack(
        alignment: Alignment.center,
        children: [
          if (imageUrl.isNotEmpty)
            ClipRRect(
              borderRadius: clipToCircle && height != null
                  ? BorderRadius.circular(height ?? 0)
                  : BorderRadius.zero,
              child: CachedNetworkImage(
                imageUrl: imageUrl,
                width: width,
                height: height,
                fit: fit,
                progressIndicatorBuilder: (_, __, progress) {
                  NetworkListeners.listener.add(
                    listener.Listener(
                      ListenersFor.file,
                      ListenersType.read,
                      progress.downloaded.toDouble(),
                    ),
                  );
                  return placeholder;
                },
                errorWidget: (_, __, dynamic ___) {
                  failed?.call();
                  return errorWidget;
                },
              ),
            ),
          if (imageUrl.isEmpty) placeholder,
          if (showProgressIndicator)
            SizedBox(
              height: height,
              width: width,
              child: const CircularProgressIndicator(),
            ),
        ],
      );
}
