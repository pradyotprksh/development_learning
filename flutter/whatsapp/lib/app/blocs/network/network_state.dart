import 'package:equatable/equatable.dart';

class NetworkState extends Equatable {
  const NetworkState({
    this.totalUploadFileSize = 0,
    this.totalDownloadFileSize = 0,
    this.totalVideoCallSize = 0,
    this.totalPhoneCallSize = 0,
    this.totalUserDocumentReadSize = 0,
    this.totalUserDocumentWriteSize = 0,
    this.useLessDataForCalls = false,
  });

  // Storage service
  final double totalUploadFileSize;

  // Cached network image | Other file size
  final double totalDownloadFileSize;

  // Video call - 81260 bytes for 1 second
  final double totalVideoCallSize;

  // Phone call - 1000 bytes for 1 second
  final double totalPhoneCallSize;
  final double totalUserDocumentReadSize;
  final double totalUserDocumentWriteSize;
  final bool useLessDataForCalls;

  double get totalUploadSize =>
      totalUploadFileSize +
      totalVideoCallSize +
      totalPhoneCallSize +
      totalUserDocumentReadSize;

  double get totalDownloadSize =>
      totalDownloadFileSize +
      totalVideoCallSize +
      totalPhoneCallSize +
      totalUserDocumentWriteSize;

  NetworkState copyWith({
    double newFileUploadSize = 0,
    double newFileDownloadSize = 0,
    double newVideoCallSize = 0,
    double newPhoneCallSize = 0,
    double newUserDocumentReadSize = 0,
    double newUserDocumentWriteSize = 0,
    bool? useLessDataForCalls,
  }) =>
      NetworkState(
        totalUploadFileSize: newFileUploadSize + totalUploadFileSize,
        totalDownloadFileSize: newFileDownloadSize + totalDownloadFileSize,
        totalVideoCallSize: newVideoCallSize + totalVideoCallSize,
        totalPhoneCallSize: newPhoneCallSize + totalPhoneCallSize,
        totalUserDocumentReadSize:
            newUserDocumentReadSize + totalUserDocumentReadSize,
        totalUserDocumentWriteSize:
            newUserDocumentWriteSize + totalUserDocumentWriteSize,
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
      ];
}
