// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'display_entity.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

DisplayEntity _$DisplayEntityFromJson(Map<String, dynamic> json) =>
    DisplayEntity(
      fileSizeBytes: json['fileSizeBytes'] as int,
      url: json['url'] as String,
    );

Map<String, dynamic> _$DisplayEntityToJson(DisplayEntity instance) =>
    <String, dynamic>{
      'fileSizeBytes': instance.fileSizeBytes,
      'url': instance.url,
    };
