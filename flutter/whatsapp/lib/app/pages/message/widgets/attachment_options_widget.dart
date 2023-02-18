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
    controller =
        CameraController(DeviceCameras.getBackCamera(), ResolutionPreset.max);
    controller.initialize().then((_) {
      setState(() {});
    });
    super.initState();
  }

  @override
  void dispose() {
    controller.dispose();
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
            GestureDetector(
              onTap: () async {
                final navigator = context.navigator;
                final path =
                    await MessageUtilsMethods.startCameraFilePicker(context);
                if (path != null) {
                  navigator.pop(
                    [
                      FileInformationDetails(
                        filePath: path,
                        isFromFileSystem: false,
                        isFromCamera: true,
                        isFromGallery: false,
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
                  navigator.pop(
                    [
                      FileInformationDetails(
                        filePath: path,
                        isFromFileSystem: false,
                        isFromCamera: true,
                        isFromGallery: false,
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
                final details = await MessageUtilsMethods.pickFileFromStorage();
                if (details != null) {
                  final files = details.files
                      .map(
                        (e) => FileInformationDetails(
                          filePath: e.path ?? '',
                          isFromFileSystem: true,
                          isFromCamera: false,
                          isFromGallery: false,
                          fileSize: e.size,
                          fileType: e.extension,
                          fileName: e.name,
                        ),
                      )
                      .toList()
                    ..removeWhere((element) => element.filePath.isEmpty);
                  navigator.pop(
                    files,
                  );
                }
              },
              child: Container(
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
            ),
          ],
        ),
      );
}
