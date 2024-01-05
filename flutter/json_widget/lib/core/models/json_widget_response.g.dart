// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'json_widget_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

JsonWidgetResponse _$JsonWidgetResponseFromJson(Map<String, dynamic> json) =>
    JsonWidgetResponse(
      pages: (json['pages'] as List<dynamic>?)
          ?.map((e) => Page.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$JsonWidgetResponseToJson(JsonWidgetResponse instance) =>
    <String, dynamic>{
      'pages': instance.pages,
    };

Page _$PageFromJson(Map<String, dynamic> json) => Page(
      id: json['id'] as String?,
      title: json['title'] as String?,
      subtitle: json['subtitle'] as String?,
      route: json['route'] as String?,
    );

Map<String, dynamic> _$PageToJson(Page instance) => <String, dynamic>{
      'id': instance.id,
      'title': instance.title,
      'subtitle': instance.subtitle,
      'route': instance.route,
    };
