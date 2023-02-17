import 'package:equatable/equatable.dart';

class FileInformation extends Equatable {
  const FileInformation({
    required this.filePath,
    required this.isFromCamera,
    required this.isFromGallery,
    required this.isFromFileSystem,
    this.fileType,
    this.fileSize = 0,
    this.fileName = '',
  });

  final String filePath;
  final bool isFromCamera;
  final bool isFromFileSystem;
  final bool isFromGallery;
  final String? fileType;
  final int fileSize;
  final String fileName;

  @override
  List<Object?> get props => [
        filePath,
        isFromCamera,
        isFromFileSystem,
        isFromGallery,
        fileType,
        fileSize,
        fileName,
      ];
}
