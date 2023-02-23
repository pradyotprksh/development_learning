import 'dart:io';

import 'package:flutter/material.dart';
import 'package:syncfusion_flutter_pdfviewer/pdfviewer.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/core/core.dart' as listener;

class PdfPreviewWidget extends StatefulWidget {
  const PdfPreviewWidget({
    super.key,
    this.url,
    this.path,
    this.size,
    this.onTap,
  });

  final String? url;
  final String? path;
  final double? size;
  final Function()? onTap;

  @override
  State<PdfPreviewWidget> createState() => _PdfPreviewWidgetState();
}

class _PdfPreviewWidgetState extends State<PdfPreviewWidget>
    with AutomaticKeepAliveClientMixin {
  @override
  Widget build(BuildContext context) {
    super.build(context);
    return GestureDetector(
      onTap: () {
        widget.onTap?.call();
      },
      child: AbsorbPointer(
        absorbing: true,
        child: (widget.url != null)
            ? SfPdfViewer.network(
                widget.url!,
                pageLayoutMode: PdfPageLayoutMode.continuous,
                canShowPaginationDialog: false,
                canShowPasswordDialog: false,
                canShowScrollHead: false,
                canShowHyperlinkDialog: false,
                canShowScrollStatus: false,
                onDocumentLoaded: (_) {
                  if (widget.size != null) {
                    NetworkListeners.listener.add(
                      listener.Listener(
                        ListenersFor.file,
                        ListenersType.read,
                        widget.size!,
                      ),
                    );
                  }
                },
              )
            : (widget.path != null)
                ? SfPdfViewer.file(
                    File(
                      widget.path!,
                    ),
                    pageLayoutMode: PdfPageLayoutMode.continuous,
                    canShowPaginationDialog: false,
                    canShowPasswordDialog: false,
                    canShowScrollHead: false,
                    canShowHyperlinkDialog: false,
                    canShowScrollStatus: false,
                  )
                : const DefaultAttachmentWidget(
                    icon: Icons.picture_as_pdf,
                  ),
      ),
    );
  }

  @override
  bool get wantKeepAlive => widget.url != null;
}
