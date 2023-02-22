import 'package:equatable/equatable.dart';

abstract class NetworkEvent extends Equatable {
  const NetworkEvent();

  @override
  List<Object?> get props => [];
}

class StartAllSizeListenersEvent extends NetworkEvent {
  const StartAllSizeListenersEvent();
}

class ListenToFileSizeUploadEvent extends NetworkEvent {
  const ListenToFileSizeUploadEvent();
}

class ListenToFileSizeDownloadEvent extends NetworkEvent {
  const ListenToFileSizeDownloadEvent();
}

class ListenToVideoCallSizeEvent extends NetworkEvent {
  const ListenToVideoCallSizeEvent();
}

class ListenToPhoneCallEvent extends NetworkEvent {
  const ListenToPhoneCallEvent();
}

class ListenToUserDocumentReadEvent extends NetworkEvent {
  const ListenToUserDocumentReadEvent();
}

class ListenToUserDocumentWriteEvent extends NetworkEvent {
  const ListenToUserDocumentWriteEvent();
}

class ListenToStatusDocumentReadEvent extends NetworkEvent {
  const ListenToStatusDocumentReadEvent();
}

class ListenToStatusDocumentWriteEvent extends NetworkEvent {
  const ListenToStatusDocumentWriteEvent();
}

class ListenToSecurityDocumentWriteEvent extends NetworkEvent {
  const ListenToSecurityDocumentWriteEvent();
}

class ListenToSavedMessageDocumentReadEvent extends NetworkEvent {
  const ListenToSavedMessageDocumentReadEvent();
}

class ListenToSavedMessageDocumentWriteEvent extends NetworkEvent {
  const ListenToSavedMessageDocumentWriteEvent();
}

class ToggleLessDataForCall extends NetworkEvent {
  const ToggleLessDataForCall();
}
