import 'dart:io';

import 'package:flutter/material.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/domain/domain.dart';

class AttachmentsPreviewWidget extends StatelessWidget {
  const AttachmentsPreviewWidget({
    super.key,
    required this.attachments,
    this.uploadingAttachment,
  });

  final List<FileInformationDetails> attachments;
  final FileInformationDetails? uploadingAttachment;

  @override
  Widget build(BuildContext context) => Padding(
        padding: ThemeEdgeInsets.all10,
        child: SizedBox(
          height: 50,
          child: ListView.builder(
            scrollDirection: Axis.horizontal,
            itemCount: attachments.length,
            itemBuilder: (_, index) {
              final fileDetails = attachments[index];
              final isUploading = uploadingAttachment == fileDetails;
              return Row(
                children: [
                  if (fileDetails.isFromCamera)
                    Stack(
                      alignment: Alignment.center,
                      children: [
                        Image.file(
                          File(
                            fileDetails.filePath,
                          ),
                        ),
                        if (isUploading) const CircularProgressIndicator(),
                      ],
                    ),
                  if (fileDetails.isFromFileSystem || fileDetails.isFromGallery)
                    Stack(
                      alignment: Alignment.center,
                      children: [
                        Container(
                          width: 50,
                          decoration: const BoxDecoration(
                            color: Colors.amberAccent,
                            borderRadius: BorderRadius.all(
                              Radius.circular(
                                10,
                              ),
                            ),
                          ),
                          child: (fileDetails.fileType == 'pdf')
                              ? PdfPreviewWidget(
                                  path: fileDetails.filePath,
                                )
                              : const Center(
                                  child: Icon(
                                    Icons.file_present,
                                  ),
                                ),
                        ),
                        if (isUploading) const CircularProgressIndicator(),
                      ],
                    ),
                  ThemeSizedBox.width5,
                ],
              );
            },
          ),
        ),
      );
}
