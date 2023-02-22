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
  final bool useLessDataForCalls;

  double get totalUploadSize =>
      totalUploadFileSize +
      totalVideoCallSize +
      totalPhoneCallSize +
      totalUserDocumentWriteSize +
      totalStatusDocumentWriteSize +
      totalSecurityDocumentWriteSize +
      totalSavedMessageDocumentWriteSize;

  double get totalDownloadSize =>
      totalDownloadFileSize +
      totalVideoCallSize +
      totalPhoneCallSize +
      totalUserDocumentReadSize +
      totalStatusDocumentReadSize +
      totalSavedMessageDocumentReadSize;

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
      ];
}
