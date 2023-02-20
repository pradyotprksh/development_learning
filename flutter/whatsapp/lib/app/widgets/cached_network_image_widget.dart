import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/core/core.dart';

class CachedNetworkImageWidget extends StatelessWidget {
  const CachedNetworkImageWidget({
    super.key,
    required this.imageUrl,
    required this.placeholder,
    required this.width,
    required this.height,
    this.showProgressIndicator = false,
    this.clipToCircle = false,
    this.tag,
  });

  final String imageUrl;
  final Widget placeholder;
  final double width;
  final double height;
  final bool showProgressIndicator;
  final bool clipToCircle;
  final String? tag;

  @override
  Widget build(BuildContext context) => Stack(
        alignment: Alignment.center,
        children: [
          if (imageUrl.isNotEmpty)
            ClipRRect(
              borderRadius: clipToCircle
                  ? BorderRadius.circular(height)
                  : BorderRadius.zero,
              child: CachedNetworkImage(
                imageUrl: imageUrl,
                width: width,
                height: height,
                progressIndicatorBuilder: (_, __, progress) {
                  UtilsLogger.errorLog(progress.progress);
                  NetworkListeners.downloadFileSizeStream
                      .add(progress.downloaded);
                  return placeholder;
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
