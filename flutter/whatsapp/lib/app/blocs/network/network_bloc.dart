import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class NetworkBloc extends HydratedBloc<NetworkEvent, NetworkState> {
  NetworkBloc() : super(const NetworkState()) {
    on<ListenToFileSizeUploadEvent>(_fileSizeUploadEventListener);
    on<ListenToFileSizeDownloadEvent>(_fileSizeDownloadEventListener);
  }

  void _fileSizeUploadEventListener(
    ListenToFileSizeUploadEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.uploadFileSizeStream.stream,
      onData: (fileSize) => state.copyWith(
        newFileUploadSize: fileSize,
      ),
    );
  }

  void _fileSizeDownloadEventListener(
    ListenToFileSizeDownloadEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.downloadFileSizeStream.stream,
      onData: (fileSize) => state.copyWith(
        newFileDownloadSize: fileSize,
      ),
    );
  }

  @override
  NetworkState? fromJson(Map<String, dynamic> json) => NetworkState(
        totalUploadFileSize:
            json[AppConstants.totalUploadFileSize] as int? ?? 0,
        totalDownloadFileSize:
            json[AppConstants.totalDownloadFileSize] as int? ?? 0,
      );

  @override
  Map<String, dynamic>? toJson(NetworkState state) => <String, dynamic>{
        AppConstants.totalUploadFileSize: state.totalUploadFileSize,
        AppConstants.totalDownloadFileSize: state.totalDownloadFileSize,
      };
}
