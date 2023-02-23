import 'package:equatable/equatable.dart';

class NetworkState extends Equatable {
  const NetworkState({
    this.totalUploadFileSize = 0,
    this.totalDownloadFileSize = 0,
    this.totalVideoCallSize = 0,
    this.totalPhoneCallSize = 0,
    this.totalUserDocumentReadSize = 0,
    this.totalUserDocumentWriteSize = 0,
    this.totalStatusDocumentReadSize = 0,
    this.totalStatusDocumentWriteSize = 0,
    this.totalSecurityDocumentWriteSize = 0,
    this.totalSavedMessageDocumentReadSize = 0,
    this.totalSavedMessageDocumentWriteSize = 0,
    this.totalCallsDocumentReadSize = 0,
    this.totalCallsDocumentWriteSize = 0,
    this.totalContactsDocumentReadSize = 0,
    this.totalContactsDocumentWriteSize = 0,
    this.totalDirectMessagesDocumentReadSize = 0,
    this.totalDirectMessagesDocumentWriteSize = 0,
    this.totalGroupMessagesMessagesDocumentReadSize = 0,
    this.totalGroupMessagesMessagesDocumentWriteSize = 0,
    this.totalSingleMessagesMessagesDocumentReadSize = 0,
    this.totalSingleMessagesMessagesDocumentWriteSize = 0,
    this.useLessDataForCalls = false,
  });

  final double totalUploadFileSize;
  final double totalDownloadFileSize;
  final double totalVideoCallSize;
  final double totalPhoneCallSize;
  final double totalUserDocumentReadSize;
  final double totalUserDocumentWriteSize;
  final double totalStatusDocumentReadSize;
  final double totalStatusDocumentWriteSize;
  final double totalSecurityDocumentWriteSize;
  final double totalSavedMessageDocumentReadSize;
  final double totalSavedMessageDocumentWriteSize;
  final double totalCallsDocumentReadSize;
  final double totalCallsDocumentWriteSize;
  final double totalContactsDocumentReadSize;
  final double totalContactsDocumentWriteSize;
  final double totalDirectMessagesDocumentReadSize;
  final double totalDirectMessagesDocumentWriteSize;
  final double totalGroupMessagesMessagesDocumentReadSize;
  final double totalGroupMessagesMessagesDocumentWriteSize;
  final double totalSingleMessagesMessagesDocumentReadSize;
  final double totalSingleMessagesMessagesDocumentWriteSize;
  final bool useLessDataForCalls;

  double get totalUploadSize =>
      totalUploadFileSize +
      totalVideoCallSize +
      totalPhoneCallSize +
      totalUserDocumentWriteSize +
      totalStatusDocumentWriteSize +
      totalSecurityDocumentWriteSize +
      totalSavedMessageDocumentWriteSize +
      totalCallsDocumentWriteSize +
      totalContactsDocumentWriteSize +
      totalDirectMessagesDocumentWriteSize +
      totalGroupMessagesMessagesDocumentWriteSize +
      totalSingleMessagesMessagesDocumentWriteSize;

  double get totalDownloadSize =>
      totalDownloadFileSize +
      totalVideoCallSize +
      totalPhoneCallSize +
      totalUserDocumentReadSize +
      totalStatusDocumentReadSize +
      totalSavedMessageDocumentReadSize +
      totalCallsDocumentReadSize +
      totalContactsDocumentReadSize +
      totalDirectMessagesDocumentReadSize +
      totalGroupMessagesMessagesDocumentReadSize +
      totalSingleMessagesMessagesDocumentReadSize;

  NetworkState copyWith({
    double totalUploadFileSize = 0,
    double totalDownloadFileSize = 0,
    double totalVideoCallSize = 0,
    double totalPhoneCallSize = 0,
    double totalUserDocumentReadSize = 0,
    double totalUserDocumentWriteSize = 0,
    double totalStatusDocumentReadSize = 0,
    double totalStatusDocumentWriteSize = 0,
    double totalSecurityDocumentWriteSize = 0,
    double totalSavedMessageDocumentReadSize = 0,
    double totalSavedMessageDocumentWriteSize = 0,
    double totalCallsDocumentReadSize = 0,
    double totalCallsDocumentWriteSize = 0,
    double totalContactsDocumentReadSize = 0,
    double totalContactsDocumentWriteSize = 0,
    double totalDirectMessagesDocumentReadSize = 0,
    double totalDirectMessagesDocumentWriteSize = 0,
    double totalGroupMessagesMessagesDocumentReadSize = 0,
    double totalGroupMessagesMessagesDocumentWriteSize = 0,
    double totalSingleMessagesMessagesDocumentReadSize = 0,
    double totalSingleMessagesMessagesDocumentWriteSize = 0,
    bool? useLessDataForCalls,
  }) =>
      NetworkState(
        totalUploadFileSize: totalUploadFileSize + this.totalUploadFileSize,
        totalDownloadFileSize:
            totalDownloadFileSize + this.totalDownloadFileSize,
        totalVideoCallSize: totalVideoCallSize + this.totalVideoCallSize,
        totalPhoneCallSize: totalPhoneCallSize + this.totalPhoneCallSize,
        totalUserDocumentReadSize:
            totalUserDocumentReadSize + this.totalUserDocumentReadSize,
        totalUserDocumentWriteSize:
            totalUserDocumentWriteSize + this.totalUserDocumentWriteSize,
        totalStatusDocumentReadSize:
            totalStatusDocumentReadSize + this.totalStatusDocumentReadSize,
        totalStatusDocumentWriteSize:
            totalStatusDocumentWriteSize + this.totalStatusDocumentWriteSize,
        totalSecurityDocumentWriteSize: totalSecurityDocumentWriteSize +
            this.totalSecurityDocumentWriteSize,
        totalSavedMessageDocumentReadSize: totalSavedMessageDocumentReadSize +
            this.totalSavedMessageDocumentReadSize,
        totalSavedMessageDocumentWriteSize: totalSavedMessageDocumentWriteSize +
            this.totalSavedMessageDocumentWriteSize,
        totalCallsDocumentReadSize:
            totalCallsDocumentReadSize + this.totalCallsDocumentReadSize,
        totalCallsDocumentWriteSize:
            totalCallsDocumentWriteSize + this.totalCallsDocumentWriteSize,
        totalContactsDocumentReadSize:
            totalContactsDocumentReadSize + this.totalContactsDocumentReadSize,
        totalContactsDocumentWriteSize: totalContactsDocumentWriteSize +
            this.totalContactsDocumentWriteSize,
        totalDirectMessagesDocumentReadSize:
            totalDirectMessagesDocumentReadSize +
                this.totalDirectMessagesDocumentReadSize,
        totalDirectMessagesDocumentWriteSize:
            totalDirectMessagesDocumentWriteSize +
                this.totalDirectMessagesDocumentWriteSize,
        totalGroupMessagesMessagesDocumentReadSize:
            totalGroupMessagesMessagesDocumentReadSize +
                this.totalGroupMessagesMessagesDocumentReadSize,
        totalGroupMessagesMessagesDocumentWriteSize:
            totalGroupMessagesMessagesDocumentWriteSize +
                this.totalGroupMessagesMessagesDocumentWriteSize,
        totalSingleMessagesMessagesDocumentReadSize:
            totalSingleMessagesMessagesDocumentReadSize +
                this.totalSingleMessagesMessagesDocumentReadSize,
        totalSingleMessagesMessagesDocumentWriteSize:
            totalSingleMessagesMessagesDocumentWriteSize +
                this.totalSingleMessagesMessagesDocumentWriteSize,
        useLessDataForCalls: useLessDataForCalls ?? this.useLessDataForCalls,
      );

  @override
  List<Object?> get props => [
        totalUploadFileSize,
        totalDownloadFileSize,
        totalVideoCallSize,
        totalPhoneCallSize,
        useLessDataForCalls,
        totalUserDocumentReadSize,
        totalUserDocumentWriteSize,
        totalStatusDocumentReadSize,
        totalStatusDocumentWriteSize,
        totalSecurityDocumentWriteSize,
        totalSavedMessageDocumentReadSize,
        totalSavedMessageDocumentWriteSize,
        totalCallsDocumentReadSize,
        totalCallsDocumentWriteSize,
        totalContactsDocumentReadSize,
        totalContactsDocumentWriteSize,
        totalDirectMessagesDocumentReadSize,
        totalDirectMessagesDocumentWriteSize,
        totalGroupMessagesMessagesDocumentReadSize,
        totalGroupMessagesMessagesDocumentWriteSize,
        totalSingleMessagesMessagesDocumentReadSize,
        totalSingleMessagesMessagesDocumentWriteSize,
      ];
}
