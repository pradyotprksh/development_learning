import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class FileInformationDetails extends Equatable {
  const FileInformationDetails({
    required this.filePath,
    required this.isFromCamera,
    required this.isFromGallery,
    required this.isFromFileSystem,
    this.firestoreFilePath = '',
    this.fileType,
    this.fileSize = 0,
    this.fileName = '',
  });

  factory FileInformationDetails.fromMap(Map<String, dynamic>? json) =>
      FileInformationDetails(
        filePath: json?[FirestoreItemKey.filePath] as String? ?? '',
        isFromCamera: json?[FirestoreItemKey.isFromCamera] as bool? ?? false,
        isFromFileSystem:
            json?[FirestoreItemKey.isFromFileSystem] as bool? ?? false,
        isFromGallery: json?[FirestoreItemKey.isFromGallery] as bool? ?? false,
        fileType: json?[FirestoreItemKey.fileType] as String?,
        fileSize: json?[FirestoreItemKey.fileSize] as int? ?? 0,
        fileName: json?[FirestoreItemKey.fileName] as String? ?? '',
        firestoreFilePath:
            json?[FirestoreItemKey.firestoreFilePath] as String? ?? '',
      );

  final String filePath;
  final String firestoreFilePath;
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

  Map<String, dynamic> toMap() => <String, dynamic>{
        FirestoreItemKey.filePath: filePath,
        FirestoreItemKey.isFromCamera: isFromCamera,
        FirestoreItemKey.isFromFileSystem: isFromFileSystem,
        FirestoreItemKey.isFromGallery: isFromGallery,
        FirestoreItemKey.fileType: fileType,
        FirestoreItemKey.fileSize: fileSize,
        FirestoreItemKey.firestoreFilePath: firestoreFilePath,
        FirestoreItemKey.fileName: fileName,
      };
}
