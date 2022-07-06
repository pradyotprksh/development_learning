import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// A widget to show the file on the screen
class FileWidget extends StatelessWidget {
  const FileWidget({
    super.key,
    required this.fileType,
    required this.url,
  });

  final FileType fileType;
  final String url;

  @override
  Widget build(BuildContext context) => Center(
        child: SizedBox(
          width: context.mediaQuery().size.width,
          height: context.mediaQuery().size.height / 2,
          child: (fileType == FileType.image)
              ? CachedNetworkImage(
                  imageUrl: url,
                  height: double.infinity,
                  width: double.infinity,
                  imageBuilder: (context, imageProvider) {
                    context.read<DisplayBloc>().add(
                          const FileLoaded(),
                        );
                    return Container(
                      decoration: BoxDecoration(
                        image: DecorationImage(
                          image: imageProvider,
                          fit: BoxFit.cover,
                        ),
                      ),
                    );
                  },
                  placeholder: (context, url) =>
                      const CenterCircularProgressbar(
                    message: 'Loading image/gif...',
                  ),
                  errorWidget: (_, __, dynamic ___) => Center(
                    child: Icon(
                      Icons.error,
                      color: context.themeData().errorColor,
                    ),
                  ),
                )
              : VideoWidget(
                  url: url,
                ),
        ),
      );
}
