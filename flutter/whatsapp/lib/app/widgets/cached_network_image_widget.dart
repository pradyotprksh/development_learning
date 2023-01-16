import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

class CachedNetworkImageWidget extends StatelessWidget {
  const CachedNetworkImageWidget({
    super.key,
    required this.imageUrl,
    required this.placeholder,
    required this.width,
    required this.height,
    this.showProgressIndicator = false,
    this.clipToCircle = false,
  });

  final String imageUrl;
  final Widget placeholder;
  final double width;
  final double height;
  final bool showProgressIndicator;
  final bool clipToCircle;

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
                placeholder: (_, __) => placeholder,
                width: width,
                height: height,
              ),
            ),
          if (imageUrl.isEmpty) placeholder,
          if (showProgressIndicator)
            SizedBox(
              height: height * 2,
              width: width * 2,
              child: const CircularProgressIndicator(),
            ),
        ],
      );
}
