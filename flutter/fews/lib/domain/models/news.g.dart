// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'news.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

News _$NewsFromJson(Map<String, dynamic> json) => News(
      meta: Meta.fromJson(json['meta'] as Map<String, dynamic>),
      data: (json['data'] as List<dynamic>)
          .map((e) => NewsData.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$NewsToJson(News instance) => <String, dynamic>{
      'meta': instance.meta,
      'data': instance.data,
    };

NewsData _$NewsDataFromJson(Map<String, dynamic> json) => NewsData(
      uuid: json['uuid'] as String,
      title: json['title'] as String,
      description: json['description'] as String,
      keywords: json['keywords'] as String,
      snippet: json['snippet'] as String,
      url: json['url'] as String,
      imageUrl: json['imageUrl'] as String,
      language: json['language'] as String,
      publishedAt: DateTime.parse(json['publishedAt'] as String),
      source: json['source'] as String,
      categories: (json['categories'] as List<dynamic>)
          .map((e) => e as String)
          .toList(),
      relevanceScore: json['relevanceScore'],
    );

Map<String, dynamic> _$NewsDataToJson(NewsData instance) => <String, dynamic>{
      'uuid': instance.uuid,
      'title': instance.title,
      'description': instance.description,
      'keywords': instance.keywords,
      'snippet': instance.snippet,
      'url': instance.url,
      'imageUrl': instance.imageUrl,
      'language': instance.language,
      'publishedAt': instance.publishedAt.toIso8601String(),
      'source': instance.source,
      'categories': instance.categories,
      'relevanceScore': instance.relevanceScore,
    };

Meta _$MetaFromJson(Map<String, dynamic> json) => Meta(
      found: json['found'] as int,
      returned: json['returned'] as int,
      limit: json['limit'] as int,
      page: json['page'] as int,
    );

Map<String, dynamic> _$MetaToJson(Meta instance) => <String, dynamic>{
      'found': instance.found,
      'returned': instance.returned,
      'limit': instance.limit,
      'page': instance.page,
    };
