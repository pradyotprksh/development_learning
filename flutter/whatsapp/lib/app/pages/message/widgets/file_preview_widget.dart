import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class FilePreviewWidget extends StatelessWidget {
  const FilePreviewWidget({
    super.key,
    required this.fileDetails,
  });

  final FileInformationDetails fileDetails;

  @override
  Widget build(BuildContext context) {
    NetworkListeners.downloadFileSizeStream.add(fileDetails.fileSize);

    return GestureDetector(
      onLongPress: () {
        MessageUtilsMethods.showFileDetails(context, fileDetails);
      },
      child: Image.network(
        fileDetails.fileUrl,
        fit: BoxFit.cover,
        errorBuilder: (_, __, ___) {
          if (fileDetails.fileType == 'pdf') {
            return const DefaultAttachmentWidget(
              icon: Icons.picture_as_pdf,
            );
          }
          if (fileDetails.fileType == 'txt') {
            return const DefaultAttachmentWidget(
              icon: Icons.text_snippet,
            );
          }
          return const DefaultAttachmentWidget();
        },
      ),
    );
  }
}
