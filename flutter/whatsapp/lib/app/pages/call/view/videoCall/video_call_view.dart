import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/device/device.dart';

class VideoCallView extends StatefulWidget {
  const VideoCallView({super.key, required this.callDetails});

  final CallDetailsArguments callDetails;

  @override
  State<VideoCallView> createState() => _VideoCallViewState();
}

class _VideoCallViewState extends State<VideoCallView> {
  late CameraController controller;

  @override
  void initState() {
    controller =
        CameraController(DeviceCameras.getCamera(), ResolutionPreset.max);
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
  Widget build(BuildContext context) => Stack(
        children: [
          if (!controller.value.isInitialized) Container(),
          if (controller.value.isInitialized)
            if (widget.callDetails.userDetails.length == 1)
              SizedBox(
                height: context.mediaQuery.size.height,
                width: context.mediaQuery.size.width,
                child: CameraPreview(
                  controller,
                ),
              ),
          if (controller.value.isInitialized)
            if (widget.callDetails.userDetails.length > 1)
              GridView.builder(
                padding: ThemeEdgeInsets.leftTopRight15Bottom150,
                itemCount: widget.callDetails.userDetails.length,
                gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
                  maxCrossAxisExtent: 200,
                  mainAxisExtent: context.mediaQuery.size.height * 0.3,
                  crossAxisSpacing: 15,
                  mainAxisSpacing: 15,
                ),
                shrinkWrap: true,
                itemBuilder: (_, index) => _getCameraWidget(
                  widget.callDetails.userDetails[index]?.name ?? '',
                ),
              ),
          if (controller.value.isInitialized)
            Align(
              alignment: Alignment.bottomRight,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.end,
                mainAxisSize: MainAxisSize.min,
                children: [
                  Container(
                    height: context.mediaQuery.size.height * 0.2,
                    width: context.mediaQuery.size.width * 0.3,
                    padding: ThemeEdgeInsets.all15,
                    child: _getCameraWidget(
                      null,
                    ),
                  ),
                  const CallFeatureWidget(),
                ],
              ),
            ),
        ],
      );

  Widget _getCameraWidget(String? name) => ClipRRect(
        borderRadius: const BorderRadius.all(
          Radius.circular(
            10,
          ),
        ),
        child: CameraPreview(
          controller,
          child: (name != null)
              ? Align(
                  alignment: Alignment.bottomCenter,
                  child: ListTile(
                    title: Text(
                      name,
                      maxLines: 1,
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                )
              : ThemeSizedBox.shrink,
        ),
      );
}
