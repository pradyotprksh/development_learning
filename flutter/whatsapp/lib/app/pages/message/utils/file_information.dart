class FileInformation {
  FileInformation({
    required this.filePath,
    required this.isFromCamera,
    required this.isFromGallery,
    required this.isFromFileSystem,
    this.fileType,
    this.fileSize = 0,
  });

  final String filePath;
  final bool isFromCamera;
  final bool isFromFileSystem;
  final bool isFromGallery;
  final String? fileType;
  final int fileSize;
}
