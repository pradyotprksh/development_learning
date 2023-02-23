import 'dart:io';

import 'package:cache_video_player/player/video_player.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class VideoWidget extends StatefulWidget {
  const VideoWidget({
    super.key,
    required this.path,
    this.isNetwork = false,
    this.play = false,
    this.showWidget = true,
  });

  final String? path;
  final bool isNetwork;
  final bool play;
  final bool showWidget;

  @override
  State<VideoWidget> createState() => _VideoWidgetState();
}

class _VideoWidgetState extends State<VideoWidget> {
  VideoPlayerController? _controller;

  @override
  void dispose() {
    _controller?.dispose();
    super.dispose();
  }

  @override
  void initState() {
    super.initState();
    if (widget.path != null && widget.path!.isNotEmpty) {
      _controller = widget.isNetwork
          ? VideoPlayerController.network(widget.path!)
          : VideoPlayerController.file(File(widget.path!))
        ..initialize().then(
          (_) {
            setState(() {});
          },
        );
    }
  }

  @override
  Widget build(BuildContext context) {
    if (widget.play && widget.showWidget) {
      _controller?.play();
      _controller?.setLooping(true);
    } else {
      _controller?.pause();
    }

    return _controller != null &&
            _controller!.value.isInitialized &&
            widget.showWidget
        ? Stack(
            alignment: Alignment.center,
            children: [
              AspectRatio(
                aspectRatio: _controller!.value.aspectRatio,
                child: VideoPlayer(
                  _controller!,
                ),
              ),
              if (!widget.isNetwork)
                IconButton(
                  onPressed: () {
                    setState(() {
                      _controller!.value.isPlaying
                          ? _controller!.pause()
                          : _controller!.play();
                    });
                  },
                  icon: Icon(
                    _controller!.value.isPlaying
                        ? Icons.pause
                        : Icons.play_arrow,
                  ),
                ),
            ],
          )
        : widget.showWidget
            ? const Center(child: CircularProgressIndicatorWidget())
            : ThemeSizedBox.shrink;
  }
}
