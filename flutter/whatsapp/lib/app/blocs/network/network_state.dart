import 'package:equatable/equatable.dart';

class NetworkState extends Equatable {
  const NetworkState({
    this.totalUploadFileSize = 0,
    this.totalDownloadFileSize = 0,
    this.totalVideoCallSize = 0,
    this.totalPhoneCallSize = 0,
  });

  // Storage service
  final int totalUploadFileSize;

  // Cached network image | Other file size
  final int totalDownloadFileSize;

  // Video call - 81260 bytes for 1 second
  final int totalVideoCallSize;

  // Phone call - 1000 bytes for 1 second
  final int totalPhoneCallSize;

  int get totalUploadSize =>
      totalUploadFileSize + totalVideoCallSize + totalPhoneCallSize;

  int get totalDownloadSize =>
      totalDownloadFileSize + totalVideoCallSize + totalPhoneCallSize;

  NetworkState copyWith({
    int newFileUploadSize = 0,
    int newFileDownloadSize = 0,
    int newVideoCallSize = 0,
    int newPhoneCallSize = 0,
  }) =>
      NetworkState(
        totalUploadFileSize: newFileUploadSize + totalUploadFileSize,
        totalDownloadFileSize: newFileDownloadSize + totalDownloadFileSize,
        totalVideoCallSize: newVideoCallSize + totalVideoCallSize,
        totalPhoneCallSize: newPhoneCallSize + totalPhoneCallSize,
      );

  @override
  List<Object?> get props => [
        totalUploadFileSize,
        totalDownloadFileSize,
        totalVideoCallSize,
        totalPhoneCallSize,
      ];
}
