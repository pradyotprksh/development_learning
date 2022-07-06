import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:video_player/video_player.dart';

/// A widget to show the video with the given [url].
class VideoWidget extends StatefulWidget {
  const VideoWidget({
    super.key,
    required this.url,
  });

  final String url;

  @override
  State<VideoWidget> createState() => _VideoWidgetState();
}

class _VideoWidgetState extends State<VideoWidget> {
  late VideoPlayerController _videoPlayerController;
  late Future<void> _initializeVideoPlayerFuture;

  @override
  void initState() {
    super.initState();
    _videoPlayerController = VideoPlayerController.network(widget.url);
    _initializeVideoPlayerFuture = _videoPlayerController.initialize();
    _videoPlayerController.setLooping(true);
  }

  @override
  void dispose() {
    _videoPlayerController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => FutureBuilder(
        future: _initializeVideoPlayerFuture,
        builder: (_, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            context.read<DisplayBloc>().add(
                  const FileLoaded(),
                );
            return VideoPlayer(
              _videoPlayerController..play(),
            );
          } else {
            return const CenterCircularProgressbar(
              message: 'Loading video...',
            );
          }
        },
      );
}
