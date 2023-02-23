import 'package:flutter/material.dart';
import 'package:syncfusion_flutter_pdfviewer/pdfviewer.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/core/core.dart' as listener;
import 'package:whatsapp/application/domain/domain.dart';

class PdfView extends StatelessWidget {
  const PdfView({super.key});

  @override
  Widget build(BuildContext context) {
    final fileDetails =
        context.routeSettings?.arguments as FileInformationDetails;

    return Scaffold(
      appBar: AppBar(
        title: Text(
          fileDetails.fileName,
        ),
      ),
      body: SfPdfViewer.network(
        fileDetails.fileUrl,
        onDocumentLoaded: (_) {
          NetworkListeners.listener.add(
            listener.Listener(
              ListenersFor.file,
              ListenersType.read,
              fileDetails.size,
            ),
          );
        },
      ),
    );
  }
}
