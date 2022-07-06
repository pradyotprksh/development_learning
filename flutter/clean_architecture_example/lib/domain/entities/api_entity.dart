import 'dart:convert';

import 'package:json_annotation/json_annotation.dart';

part 'api_entity.g.dart';

List<ApiEntity> apiEntityListFromJson(String str) => List<ApiEntity>.from(
      (json.decode(str) as List<dynamic>).map<dynamic>(
        (dynamic x) => ApiEntity.fromJson(
          x as Map<String, dynamic>,
        ),
      ),
    );

@JsonSerializable()
class ApiEntity {
  ApiEntity({
    required this.userId,
    required this.id,
    required this.title,
    required this.body,
  });

  factory ApiEntity.fromJson(Map<String, dynamic> json) =>
      _$ApiEntityFromJson(json);

  int userId;
  int id;
  String title;
  String body;

  Map<String, dynamic> toJson() => _$ApiEntityToJson(this);
}
