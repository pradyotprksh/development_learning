import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class NetworkBloc extends HydratedBloc<NetworkEvent, NetworkState> {
  NetworkBloc() : super(const NetworkState()) {
    on<StartAllSizeListenersEvent>(_startAllSizeListeners);
    on<ListenToFileSizeUploadEvent>(_fileSizeUploadEventListener);
    on<ListenToFileSizeDownloadEvent>(_fileSizeDownloadEventListener);
    on<ListenToVideoCallSizeEvent>(_videoCallSizeEventListener);
    on<ListenToPhoneCallEvent>(_phoneCallSizeEventListener);
    on<ToggleLessDataForCall>(_toggleLessDataForCallEvent);
    on<ListenToUserDocumentReadEvent>(_userDocumentReadSizeEventListener);
    on<ListenToUserDocumentWriteEvent>(_userDocumentWriteSizeEventListener);
    on<ListenToStatusDocumentReadEvent>(_statusDocumentReadSizeEventListener);
    on<ListenToStatusDocumentWriteEvent>(_statusDocumentWriteSizeEventListener);
    on<ListenToSecurityDocumentWriteEvent>(
        _securityDocumentWriteSizeEventListener);
    on<ListenToSavedMessageDocumentReadEvent>(
        _savedMessageDocumentReadSizeEventListener);
    on<ListenToSavedMessageDocumentWriteEvent>(
        _savedMessageDocumentWriteSizeEventListener);
  }

  void _startAllSizeListeners(
    StartAllSizeListenersEvent event,
    Emitter<NetworkState> emit,
  ) {
    add(const ListenToFileSizeUploadEvent());
    add(const ListenToFileSizeDownloadEvent());
    add(const ListenToVideoCallSizeEvent());
    add(const ListenToPhoneCallEvent());
    add(const ListenToUserDocumentReadEvent());
    add(const ListenToUserDocumentWriteEvent());
    add(const ListenToStatusDocumentReadEvent());
    add(const ListenToStatusDocumentWriteEvent());
    add(const ListenToSecurityDocumentWriteEvent());
    add(const ListenToSavedMessageDocumentReadEvent());
    add(const ListenToSavedMessageDocumentWriteEvent());
  }

  void _fileSizeUploadEventListener(
    ListenToFileSizeUploadEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.uploadFileSizeStream.stream,
      onData: (fileSize) => state.copyWith(
        totalUploadFileSize: fileSize,
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
        totalDownloadFileSize: fileSize,
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
          totalVideoCallSize: fileSize - difference,
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
          totalPhoneCallSize: fileSize - difference,
        );
      },
    );
  }

  void _userDocumentReadSizeEventListener(
    ListenToUserDocumentReadEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.userDocumentReadSizeStream.stream,
      onData: (size) => state.copyWith(
        totalUserDocumentReadSize: size,
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
        totalUserDocumentWriteSize: size,
      ),
    );
  }

  void _statusDocumentReadSizeEventListener(
    ListenToStatusDocumentReadEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.statusDocumentReadSizeStream.stream,
      onData: (size) => state.copyWith(
        totalStatusDocumentReadSize: size,
      ),
    );
  }

  void _statusDocumentWriteSizeEventListener(
    ListenToStatusDocumentWriteEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.statusDocumentWriteSizeStream.stream,
      onData: (size) => state.copyWith(
        totalStatusDocumentWriteSize: size,
      ),
    );
  }

  void _securityDocumentWriteSizeEventListener(
    ListenToSecurityDocumentWriteEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.securityDocumentWriteSizeStream.stream,
      onData: (size) => state.copyWith(
        totalSecurityDocumentWriteSize: size,
      ),
    );
  }

  void _savedMessageDocumentReadSizeEventListener(
    ListenToSavedMessageDocumentReadEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.savedMessageDocumentReadSizeStream.stream,
      onData: (size) => state.copyWith(
        totalSavedMessageDocumentReadSize: size,
      ),
    );
  }

  void _savedMessageDocumentWriteSizeEventListener(
    ListenToSavedMessageDocumentWriteEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.savedMessageDocumentWriteSizeStream.stream,
      onData: (size) => state.copyWith(
        totalSavedMessageDocumentWriteSize: size,
      ),
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
        totalUserDocumentReadSize:
            json[AppConstants.totalUserDocumentReadSize] as double? ?? 0,
        totalUserDocumentWriteSize:
            json[AppConstants.totalUserDocumentWriteSize] as double? ?? 0,
        totalStatusDocumentReadSize:
            json[AppConstants.totalStatusDocumentReadSize] as double? ?? 0,
        totalStatusDocumentWriteSize:
            json[AppConstants.totalStatusDocumentWriteSize] as double? ?? 0,
        totalSecurityDocumentWriteSize:
            json[AppConstants.totalSecurityDocumentWriteSize] as double? ?? 0,
        totalSavedMessageDocumentReadSize:
            json[AppConstants.totalSavedMessageDocumentReadSize] as double? ??
                0,
        totalSavedMessageDocumentWriteSize:
            json[AppConstants.totalSavedMessageDocumentWriteSize] as double? ??
                0,
      );

  @override
  Map<String, dynamic>? toJson(NetworkState state) => <String, dynamic>{
        AppConstants.totalUploadFileSize: state.totalUploadFileSize,
        AppConstants.totalDownloadFileSize: state.totalDownloadFileSize,
        AppConstants.totalVideoCallSize: state.totalVideoCallSize,
        AppConstants.totalPhoneCallSize: state.totalPhoneCallSize,
        AppConstants.totalUserDocumentReadSize: state.totalUserDocumentReadSize,
        AppConstants.totalUserDocumentWriteSize:
            state.totalUserDocumentWriteSize,
        AppConstants.totalStatusDocumentReadSize:
            state.totalStatusDocumentReadSize,
        AppConstants.totalStatusDocumentWriteSize:
            state.totalStatusDocumentWriteSize,
        AppConstants.totalSecurityDocumentWriteSize:
            state.totalSecurityDocumentWriteSize,
        AppConstants.totalSavedMessageDocumentReadSize:
            state.totalSavedMessageDocumentReadSize,
        AppConstants.totalSavedMessageDocumentWriteSize:
            state.totalSavedMessageDocumentWriteSize,
      };

  void _toggleLessDataForCallEvent(
    ToggleLessDataForCall event,
    Emitter<NetworkState> emit,
  ) {
    emit(
      state.copyWith(useLessDataForCalls: !state.useLessDataForCalls),
    );
  }
}
