// ignore_for_file: depend_on_referenced_packages
import 'dart:io';

import 'package:json_annotation/json_annotation.dart';
import 'package:mime/mime.dart';
import 'package:path/path.dart';

part 'display_entity.g.dart';

enum FileType { image, video }

@JsonSerializable()
class DisplayEntity {
  DisplayEntity({
    required this.fileSizeBytes,
    required this.url,
  });

  factory DisplayEntity.fromJson(Map<String, dynamic> json) =>
      _$DisplayEntityFromJson(json);

  int fileSizeBytes;
  String url;

  Map<String, dynamic> toJson() => _$DisplayEntityToJson(this);

  FileType getFileType() {
    final file = File(url);
    final filename = basename(file.path);
    final mimeType = lookupMimeType(filename) ?? '';
    if (!mimeType.contains('image')) {
      return FileType.video;
    }
    return FileType.image;
  }
}
