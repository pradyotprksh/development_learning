import 'dart:io';

import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
import 'package:whatsapp/app/app.dart';

class VideoWidget extends StatefulWidget {
  const VideoWidget({
    super.key,
    required this.path,
    this.isNetwork = false,
  });

  final String path;
  final bool isNetwork;

  @override
  State<VideoWidget> createState() => _VideoWidgetState();
}

class _VideoWidgetState extends State<VideoWidget> {
  late VideoPlayerController _controller;

  @override
  void initState() {
    super.initState();
    _controller = widget.isNetwork
        ? VideoPlayerController.network(widget.path)
        : VideoPlayerController.file(File(widget.path))
      ..initialize().then(
        (_) {
          setState(() {
            if (widget.isNetwork) {
              _controller.play();
            }
          });
        },
      );
  }

  @override
  Widget build(BuildContext context) => _controller.value.isInitialized
      ? Stack(
          alignment: Alignment.center,
          children: [
            AspectRatio(
              aspectRatio: _controller.value.aspectRatio,
              child: VideoPlayer(
                _controller,
              ),
            ),
            if (!widget.isNetwork)
              IconButton(
                onPressed: () {
                  setState(() {
                    _controller.value.isPlaying
                        ? _controller.pause()
                        : _controller.play();
                  });
                },
                icon: Icon(
                  _controller.value.isPlaying ? Icons.pause : Icons.play_arrow,
                ),
              ),
          ],
        )
      : const Center(child: CircularProgressIndicatorWidget());
}