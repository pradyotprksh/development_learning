import 'package:flutter/material.dart';
import 'package:flutter_chat_types/flutter_chat_types.dart' show PreviewData;
import 'package:flutter_link_previewer/flutter_link_previewer.dart';
import 'package:whatsapp/app/app.dart';

class ShowLinkPreview extends StatefulWidget {
  const ShowLinkPreview({
    super.key,
    required this.link,
    required this.backgroundColor,
  });

  final String link;
  final Color backgroundColor;

  @override
  State<ShowLinkPreview> createState() => _ShowLinkPreviewState();
}

class _ShowLinkPreviewState extends State<ShowLinkPreview>
    with AutomaticKeepAliveClientMixin {
  PreviewData? _data;

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return ClipRRect(
      borderRadius: BorderRadius.circular(
        20,
      ),
      child: Card(
        color: widget.backgroundColor,
        shadowColor: context.themeData.scaffoldBackgroundColor,
        child: LinkPreview(
          linkStyle: context.themeData.textTheme.bodySmall?.copyWith(
            color: Colors.white,
            decoration: TextDecoration.underline,
          ),
          metadataTextStyle: context.themeData.textTheme.titleMedium?.copyWith(
            color: Colors.white,
          ),
          metadataTitleStyle: context.themeData.textTheme.titleSmall?.copyWith(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
          enableAnimation: true,
          onLinkPressed: (link) async {
            await AppUtilsMethods.openUrl(link);
          },
          onPreviewDataFetched: (data) {
            setState(() {
              _data = data;
            });
          },
          imageBuilder: (url) => CachedNetworkImageWidget(
            imageUrl: url,
            placeholder: Icon(
              Icons.link,
              color: context.themeData.iconTheme.color,
            ),
          ),
          previewData: _data,
          text: widget.link,
          width: double.infinity,
          openOnPreviewImageTap: true,
          openOnPreviewTitleTap: true,
          hideImage: true,
        ),
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}
