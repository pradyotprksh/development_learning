import 'package:equatable/equatable.dart';

class NetworkState extends Equatable {
  const NetworkState({
    this.totalUploadFileSize = 0,
    this.totalDownloadFileSize = 0,
  });

  final int totalUploadFileSize;
  final int totalDownloadFileSize;

  NetworkState copyWith({
    int newFileUploadSize = 0,
    int newFileDownloadSize = 0,
  }) =>
      NetworkState(
        totalUploadFileSize: newFileUploadSize + totalUploadFileSize,
        totalDownloadFileSize: newFileDownloadSize + totalDownloadFileSize,
      );

  @override
  List<Object?> get props => [
        totalUploadFileSize,
        totalDownloadFileSize,
      ];
}
