import 'package:flutter/material.dart';
import 'package:whatsapp/domain/domain.dart';

class FilePreviewWidget extends StatelessWidget {
  const FilePreviewWidget({
    super.key,
    required this.fileDetails,
  });

  final FileInformationDetails fileDetails;

  @override
  Widget build(BuildContext context) => Image.network(
        fileDetails.fileUrl,
        fit: BoxFit.cover,
        errorBuilder: (_, __, ___) => Container(
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
      );
}
