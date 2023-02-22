import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class NetworkBloc extends HydratedBloc<NetworkEvent, NetworkState> {
  NetworkBloc() : super(const NetworkState()) {
    on<ListenToFileSizeUploadEvent>(_fileSizeUploadEventListener);
    on<ListenToFileSizeDownloadEvent>(_fileSizeDownloadEventListener);
    on<ListenToVideoCallSizeEvent>(_videoCallSizeEventListener);
    on<ListenToPhoneCallEvent>(_phoneCallSizeEventListener);
    on<ToggleLessDataForCall>(_toggleLessDataForCallEvent);
    on<ListenToUserDocumentReadEvent>(_userDocumentReadSizeEventListener);
    on<ListenToUserDocumentWriteEvent>(_userDocumentWriteSizeEventListener);
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

  void _videoCallSizeEventListener(
    ListenToVideoCallSizeEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.videoCallSizeStream.stream,
      onData: (fileSize) {
        final difference =
            state.useLessDataForCalls ? AppConstants.lessVideoCallSizeBytes : 0;

        return state.copyWith(
          newVideoCallSize: fileSize - difference,
        );
      },
    );
  }

  void _phoneCallSizeEventListener(
    ListenToPhoneCallEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.phoneCallSizeStream.stream,
      onData: (fileSize) {
        final difference =
            state.useLessDataForCalls ? AppConstants.lessPhoneCallSizeBytes : 0;

        return state.copyWith(
          newPhoneCallSize: fileSize - difference,
        );
      },
    );
  }

  @override
  NetworkState? fromJson(Map<String, dynamic> json) => NetworkState(
        totalUploadFileSize:
            json[AppConstants.totalUploadFileSize] as double? ?? 0,
        totalDownloadFileSize:
            json[AppConstants.totalDownloadFileSize] as double? ?? 0,
        totalVideoCallSize:
            json[AppConstants.totalVideoCallSize] as double? ?? 0,
        totalPhoneCallSize:
            json[AppConstants.totalPhoneCallSize] as double? ?? 0,
      );

  @override
  Map<String, dynamic>? toJson(NetworkState state) => <String, dynamic>{
        AppConstants.totalUploadFileSize: state.totalUploadFileSize,
        AppConstants.totalDownloadFileSize: state.totalDownloadFileSize,
        AppConstants.totalVideoCallSize: state.totalVideoCallSize,
        AppConstants.totalPhoneCallSize: state.totalPhoneCallSize,
      };

  void _toggleLessDataForCallEvent(
    ToggleLessDataForCall event,
    Emitter<NetworkState> emit,
  ) {
    emit(
      state.copyWith(useLessDataForCalls: !state.useLessDataForCalls),
    );
  }

  void _userDocumentReadSizeEventListener(
    ListenToUserDocumentReadEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.userDocumentReadSizeStream.stream,
      onData: (size) => state.copyWith(
        newUserDocumentReadSize: size,
      ),
    );
  }

  void _userDocumentWriteSizeEventListener(
    ListenToUserDocumentWriteEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.userDocumentWriteSizeStream.stream,
      onData: (size) => state.copyWith(
        newUserDocumentWriteSize: size,
      ),
    );
  }
}
