import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/core/core.dart' as listener;
import 'package:whatsapp/domain/domain.dart';

class CachedAttachmentPreviewWidget extends StatelessWidget {
  const CachedAttachmentPreviewWidget({
    super.key,
    required this.fileDetails,
    this.showOnlyImage = false,
  });

  final FileInformationDetails fileDetails;
  final bool showOnlyImage;

  @override
  Widget build(BuildContext context) => GestureDetector(
        onLongPress: () {
          MessageUtilsMethods.showFileDetails(context, fileDetails);
        },
        child: GestureDetector(
          onTap: () {
            if (AppDetails.isWeb) {
              AppUtilsMethods.openUrl(fileDetails.fileUrl);
            } else {}
          },
          child: ClipRRect(
            borderRadius: const BorderRadius.all(
              Radius.circular(
                10,
              ),
            ),
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
                    showPreview: false,
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
          ),
        ),
      );
}
