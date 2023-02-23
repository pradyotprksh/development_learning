import 'package:flutter/material.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/core/core.dart' as listener;
import 'package:whatsapp/application/domain/domain.dart';

class CachedAttachmentPreviewWidget extends StatelessWidget {
  const CachedAttachmentPreviewWidget({
    super.key,
    required this.fileDetails,
  });

  final FileInformationDetails fileDetails;

  @override
  Widget build(BuildContext context) => GestureDetector(
        onLongPress: () {
          MessageUtilsMethods.showFileDetails(context, fileDetails);
        },
        child: (fileDetails.fileType == 'pdf')
            ? PdfPreviewWidget(
                url: fileDetails.fileUrl,
                size: fileDetails.size,
                onTap: () {
                  context.navigator.pushNamed(
                    Routes.pdfView,
                    arguments: fileDetails,
                  );
                },
              )
            : (fileDetails.fileType == 'txt')
                ? const DefaultAttachmentWidget(
                    icon: Icons.text_snippet,
                  )
                : CachedNetworkImageWidget(
                    imageUrl: fileDetails.fileUrl,
                    fit: BoxFit.cover,
                    placeholder: const DefaultAttachmentWidget(),
                    width: double.infinity,
                    height: double.infinity,
                    failed: () {
                      NetworkListeners.listener.add(
                        listener.Listener(
                          ListenersFor.file,
                          ListenersType.read,
                          fileDetails.size,
                        ),
                      );
                    },
                    errorWidget: const DefaultAttachmentWidget(),
                  ),
      );
}
