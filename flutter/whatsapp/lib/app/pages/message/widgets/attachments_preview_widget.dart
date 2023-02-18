import 'dart:io';

import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class AttachmentsPreviewWidget extends StatelessWidget {
  const AttachmentsPreviewWidget({
    super.key,
    required this.attachments,
  });

  final List<FileInformationDetails> attachments;

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
              return Row(
                children: [
                  if (fileDetails.isFromCamera || fileDetails.isFromGallery)
                    Image.file(
                      File(
                        fileDetails.filePath,
                      ),
                    ),
                  if (fileDetails.isFromFileSystem)
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
                      child: const Center(
                        child: Icon(
                          Icons.file_present,
                        ),
                      ),
                    ),
                  ThemeSizedBox.width5,
                ],
              );
            },
          ),
        ),
      );
}
