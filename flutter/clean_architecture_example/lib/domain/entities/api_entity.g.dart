// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'api_entity.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ApiEntity _$ApiEntityFromJson(Map<String, dynamic> json) => ApiEntity(
      userId: json['userId'] as int,
      id: json['id'] as int,
      title: json['title'] as String,
      body: json['body'] as String,
    );

Map<String, dynamic> _$ApiEntityToJson(ApiEntity instance) => <String, dynamic>{
      'userId': instance.userId,
      'id': instance.id,
      'title': instance.title,
      'body': instance.body,
    };
