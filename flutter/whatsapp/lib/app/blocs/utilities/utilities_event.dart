import 'package:equatable/equatable.dart';

abstract class UtilitiesEvent extends Equatable {
  const UtilitiesEvent();

  @override
  List<Object?> get props => [];
}

class InitiateConnectivityCheck extends UtilitiesEvent {
  const InitiateConnectivityCheck();
}

class ScreenshotTaken extends UtilitiesEvent {
  const ScreenshotTaken(this.name, this.arguments);

  final String? name;
  final Object? arguments;
}

class MessageCopyForwardSaveEvent extends UtilitiesEvent {
  const MessageCopyForwardSaveEvent({
    required this.messageId,
    this.directMessageId,
    this.groupId,
    this.isCopied = false,
    this.isForwarded = false,
    this.isSaved = false,
  });

  final String messageId;
  final String? directMessageId;
  final String? groupId;
  final bool isCopied;
  final bool isForwarded;
  final bool isSaved;
}
