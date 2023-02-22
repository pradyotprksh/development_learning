import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class NetworkBloc extends HydratedBloc<NetworkEvent, NetworkState> {
  NetworkBloc() : super(const NetworkState()) {
    on<StartAllSizeListenersEvent>(_startAllSizeListeners);
    on<ToggleLessDataForCall>(_toggleLessDataForCallEvent);
  }

  void _startAllSizeListeners(
    StartAllSizeListenersEvent event,
    Emitter<NetworkState> emit,
  ) async {
    await emit.forEach(
      NetworkListeners.listener.stream,
      onData: (listener) {
        var writeSize = 0.0;
        var readSize = 0.0;

        switch (listener.listenersType) {
          case ListenersType.read:
            readSize = listener.size;
            break;
          case ListenersType.write:
            writeSize = listener.size;
            break;
        }

        switch (listener.listenersFor) {
          case ListenersFor.file:
            return state.copyWith(
              totalDownloadFileSize: readSize,
              totalUploadFileSize: writeSize,
            );
          case ListenersFor.videoCall:
            return state.copyWith(
              totalVideoCallSize: readSize,
            );
          case ListenersFor.phoneCall:
            return state.copyWith(
              totalPhoneCallSize: readSize,
            );
          case ListenersFor.user:
            return state.copyWith(
              totalUserDocumentReadSize: readSize,
              totalUserDocumentWriteSize: writeSize,
            );
          case ListenersFor.status:
            return state.copyWith(
              totalStatusDocumentReadSize: readSize,
              totalStatusDocumentWriteSize: writeSize,
            );
          case ListenersFor.security:
            return state.copyWith(
              totalSecurityDocumentWriteSize: writeSize,
            );
          case ListenersFor.savedMessage:
            return state.copyWith(
              totalSavedMessageDocumentReadSize: readSize,
              totalSavedMessageDocumentWriteSize: writeSize,
            );
          case ListenersFor.calls:
            return state.copyWith(
              totalCallsDocumentReadSize: readSize,
              totalCallsDocumentWriteSize: writeSize,
            );
          case ListenersFor.contacts:
            state.copyWith(
              totalContactsDocumentReadSize: readSize,
              totalContactsDocumentWriteSize: writeSize,
            );
            break;
          case ListenersFor.directMessages:
            return state.copyWith(
              totalDirectMessagesDocumentReadSize: readSize,
              totalDirectMessagesDocumentWriteSize: writeSize,
            );
          case ListenersFor.groupMessages:
            return state.copyWith(
              totalGroupMessagesMessagesDocumentReadSize: readSize,
              totalGroupMessagesMessagesDocumentWriteSize: writeSize,
            );
          case ListenersFor.singleMessage:
            return state.copyWith(
              totalSingleMessagesMessagesDocumentReadSize: readSize,
              totalSingleMessagesMessagesDocumentWriteSize: writeSize,
            );
        }
        return state;
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
        totalCallsDocumentReadSize:
            json[AppConstants.totalCallsDocumentReadSize] as double? ?? 0,
        totalCallsDocumentWriteSize:
            json[AppConstants.totalCallsDocumentWriteSize] as double? ?? 0,
        totalContactsDocumentReadSize:
            json[AppConstants.totalContactsDocumentReadSize] as double? ?? 0,
        totalContactsDocumentWriteSize:
            json[AppConstants.totalContactsDocumentWriteSize] as double? ?? 0,
        totalDirectMessagesDocumentReadSize:
            json[AppConstants.totalDirectMessagesDocumentReadSize] as double? ??
                0,
        totalDirectMessagesDocumentWriteSize:
            json[AppConstants.totalDirectMessagesDocumentWriteSize]
                    as double? ??
                0,
        totalGroupMessagesMessagesDocumentReadSize:
            json[AppConstants.totalGroupMessagesMessagesDocumentReadSize]
                    as double? ??
                0,
        totalGroupMessagesMessagesDocumentWriteSize:
            json[AppConstants.totalGroupMessagesMessagesDocumentWriteSize]
                    as double? ??
                0,
        totalSingleMessagesMessagesDocumentReadSize:
            json[AppConstants.totalSingleMessagesMessagesDocumentReadSize]
                    as double? ??
                0,
        totalSingleMessagesMessagesDocumentWriteSize:
            json[AppConstants.totalSingleMessagesMessagesDocumentWriteSize]
                    as double? ??
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
        AppConstants.totalCallsDocumentReadSize:
            state.totalCallsDocumentReadSize,
        AppConstants.totalCallsDocumentWriteSize:
            state.totalCallsDocumentWriteSize,
        AppConstants.totalContactsDocumentReadSize:
            state.totalContactsDocumentReadSize,
        AppConstants.totalContactsDocumentWriteSize:
            state.totalContactsDocumentWriteSize,
        AppConstants.totalDirectMessagesDocumentReadSize:
            state.totalDirectMessagesDocumentReadSize,
        AppConstants.totalDirectMessagesDocumentWriteSize:
            state.totalDirectMessagesDocumentWriteSize,
        AppConstants.totalGroupMessagesMessagesDocumentReadSize:
            state.totalGroupMessagesMessagesDocumentReadSize,
        AppConstants.totalGroupMessagesMessagesDocumentWriteSize:
            state.totalGroupMessagesMessagesDocumentWriteSize,
        AppConstants.totalSingleMessagesMessagesDocumentReadSize:
            state.totalSingleMessagesMessagesDocumentReadSize,
        AppConstants.totalSingleMessagesMessagesDocumentWriteSize:
            state.totalSingleMessagesMessagesDocumentWriteSize,
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
