import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/core/core.dart' as listener;
import 'package:whatsapp/domain/domain.dart';

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
                  if (AppDetails.isWeb) {
                    AppUtilsMethods.openUrl(fileDetails.fileUrl);
                  } else {
                    context.navigator.pushNamed(
                      Routes.pdfView,
                      arguments: fileDetails,
                    );
                  }
                },
              )
            : (fileDetails.fileType == 'txt')
                ? const DefaultAttachmentWidget(
                    icon: Icons.text_snippet,
                  )
                : GestureDetector(
                    onTap: () {
                      if (AppDetails.isWeb) {
                        AppUtilsMethods.openUrl(fileDetails.fileUrl);
                      } else {}
                    },
                    child: CachedNetworkImageWidget(
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
                  ),
      );
}
