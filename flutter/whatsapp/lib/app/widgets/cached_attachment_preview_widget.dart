import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
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
        child: CachedNetworkImageWidget(
          imageUrl: fileDetails.fileUrl,
          fit: BoxFit.cover,
          placeholder: const DefaultAttachmentWidget(),
          width: double.infinity,
          height: double.infinity,
          errorWidget: (fileDetails.fileType == 'pdf')
              ? const DefaultAttachmentWidget(
                  icon: Icons.picture_as_pdf,
                )
              : (fileDetails.fileType == 'txt')
                  ? const DefaultAttachmentWidget(
                      icon: Icons.text_snippet,
                    )
                  : const DefaultAttachmentWidget(),
        ),
      );
}
