import 'dart:io';

import 'package:cache_video_player/player/video_player.dart' as cache;
import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
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
  cache.VideoPlayerController? _cacheVideoController;
  VideoPlayerController? _videoController;

  @override
  void dispose() {
    (AppDetails.isPhone ? _cacheVideoController : _videoController)?.dispose();
    super.dispose();
  }

  @override
  void initState() {
    super.initState();
    if (widget.path != null && widget.path!.isNotEmpty) {
      if (AppDetails.isPhone) {
        _cacheVideoController = widget.isNetwork
            ? cache.VideoPlayerController.network(widget.path!)
            : cache.VideoPlayerController.file(File(widget.path!))
          ..initialize().then(
            (_) {
              setState(() {});
            },
          );
      } else {
        _videoController = widget.isNetwork
            ? VideoPlayerController.network(widget.path!)
            : VideoPlayerController.file(File(widget.path!))
          ..initialize().then(
            (_) {
              setState(() {});
            },
          );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    if (widget.play && widget.showWidget) {
      if (AppDetails.isPhone) {
        _cacheVideoController?.play();
        _cacheVideoController?.setLooping(true);
      } else {
        _videoController?.play();
        _videoController?.setLooping(true);
      }
    } else {
      if (AppDetails.isPhone) {
        _cacheVideoController?.pause();
      } else {
        _videoController?.pause();
      }
    }

    if (widget.showWidget) {
      if (AppDetails.isPhone && _cacheVideoController != null) {
        if (_cacheVideoController!.value.isInitialized) {
          return Stack(
            alignment: Alignment.center,
            children: [
              AspectRatio(
                aspectRatio: _cacheVideoController!.value.aspectRatio,
                child: cache.VideoPlayer(
                  _cacheVideoController!,
                ),
              ),
              if (!widget.isNetwork)
                IconButton(
                  onPressed: () {
                    setState(
                      () {
                        _cacheVideoController!.value.isPlaying
                            ? _cacheVideoController!.pause()
                            : _cacheVideoController!.play();
                      },
                    );
                  },
                  icon: Icon(
                    _cacheVideoController!.value.isPlaying
                        ? Icons.pause
                        : Icons.play_arrow,
                  ),
                ),
            ],
          );
        }
      } else if (AppDetails.isWeb && _videoController != null) {
        if (_videoController!.value.isInitialized) {
          return Stack(
            alignment: Alignment.center,
            children: [
              AspectRatio(
                aspectRatio: _videoController!.value.aspectRatio,
                child: VideoPlayer(
                  _videoController!,
                ),
              ),
              if (!widget.isNetwork)
                IconButton(
                  onPressed: () {
                    setState(
                      () {
                        _videoController!.value.isPlaying
                            ? _videoController!.pause()
                            : _videoController!.play();
                      },
                    );
                  },
                  icon: Icon(
                    _videoController!.value.isPlaying
                        ? Icons.pause
                        : Icons.play_arrow,
                  ),
                ),
            ],
          );
        }
      }
      return const Center(child: CircularProgressIndicatorWidget());
    } else {
      return ThemeSizedBox.shrink;
    }
  }
}
