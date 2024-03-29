import 'dart:io';

import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class AttachmentOptionsWidget extends StatefulWidget {
  const AttachmentOptionsWidget({super.key});

  @override
  State<AttachmentOptionsWidget> createState() =>
      _AttachmentOptionsWidgetState();
}

class _AttachmentOptionsWidgetState extends State<AttachmentOptionsWidget> {
  late CameraController controller;

  @override
  void initState() {
    if (AppDetails.isPhone) {
      controller =
          CameraController(DeviceCameras.getBackCamera(), ResolutionPreset.max);
      controller.initialize().then((_) {
        setState(() {});
      });
    }
    super.initState();
  }

  @override
  void dispose() {
    if (AppDetails.isPhone) {
      controller.dispose();
    }
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          backgroundColor: Colors.transparent,
          leading: const CloseButton(),
          elevation: 0,
        ),
        body: GridView.count(
          padding: ThemeEdgeInsets.all10,
          crossAxisCount: 3,
          crossAxisSpacing: 5,
          mainAxisSpacing: 5,
          shrinkWrap: true,
          children: [
            if (AppDetails.isPhone)
              GestureDetector(
                onTap: () async {
                  final navigator = context.navigator;
                  final path =
                      await MessageUtilsMethods.startCameraFilePicker(context);
                  if (path != null) {
                    final fileDetails = File(path);
                    final fileSize = await fileDetails.length();
                    navigator.pop(
                      [
                        FileInformationDetails(
                          filePath: path,
                          isFromFileSystem: false,
                          isFromCamera: true,
                          isFromGallery: false,
                          fileSize: fileSize.toDouble(),
                          fileName: fileDetails.name ?? '',
                        ),
                      ],
                    );
                  }
                },
                child: ClipRRect(
                  borderRadius: const BorderRadius.all(
                    Radius.circular(
                      10,
                    ),
                  ),
                  child: SizedBox(
                    width: 120,
                    height: 150,
                    child: CameraPreview(
                      controller,
                    ),
                  ),
                ),
              ),
            GestureDetector(
              onTap: () async {
                final navigator = context.navigator;
                final path =
                    await MessageUtilsMethods.startGalleryFilePicker(context);
                if (path != null) {
                  final fileDetails = File(path);
                  final fileSize = await fileDetails.length();
                  navigator.pop(
                    [
                      FileInformationDetails(
                        filePath: path,
                        isFromFileSystem: false,
                        isFromCamera: true,
                        isFromGallery: false,
                        fileSize: fileSize.toDouble(),
                        fileName: fileDetails.name ?? '',
                      ),
                    ],
                  );
                }
              },
              child: Container(
                decoration: const BoxDecoration(
                  color: Colors.redAccent,
                  borderRadius: BorderRadius.all(
                    Radius.circular(
                      10,
                    ),
                  ),
                ),
                child: const Center(
                  child: Icon(
                    Icons.image,
                  ),
                ),
              ),
            ),
            GestureDetector(
              onTap: () async {
                final navigator = context.navigator;
                final details = await MessageUtilsMethods.getFilesFromFile();
                if (details != null) {
                  navigator.pop(
                    details,
                  );
                }
              },
              child: const DefaultAttachmentWidget(),
            ),
          ],
        ),
      );
}
