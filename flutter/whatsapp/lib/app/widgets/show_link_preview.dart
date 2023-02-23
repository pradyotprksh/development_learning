import 'package:flutter/material.dart';
// ignore: depend_on_referenced_packages
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

class _ShowLinkPreviewState extends State<ShowLinkPreview> {
  PreviewData? _data;

  @override
  Widget build(BuildContext context) => Padding(
        padding: ThemeEdgeInsets.top5Bottom5,
        child: ClipRRect(
          borderRadius: BorderRadius.circular(
            20,
          ),
          child: Container(
            color: widget.backgroundColor,
            child: LinkPreview(
              enableAnimation: true,
              onLinkPressed: (_) async {
                await AppUtilsMethods.openUrl(widget.link);
              },
              onPreviewDataFetched: (data) {
                setState(() {
                  _data = data;
                });
              },
              previewData: _data,
              text: widget.link,
              width: double.infinity,
            ),
          ),
        ),
      );
}
