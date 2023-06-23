import 'package:equatable/equatable.dart';
import 'package:json_annotation/json_annotation.dart';

part 'news.g.dart';

@JsonSerializable()
class News extends Equatable {
  const News({
    required this.meta,
    required this.data,
  });

  factory News.fromJson(Map<String, dynamic> json) => _$NewsFromJson(json);

  final Meta meta;
  final List<NewsData> data;

  Map<String, dynamic> toJson() => _$NewsToJson(this);

  @override
  List<Object?> get props => [
        meta,
        data,
      ];
}

@JsonSerializable()
class NewsData extends Equatable {
  const NewsData({
    required this.uuid,
    required this.title,
    required this.description,
    required this.keywords,
    required this.snippet,
    required this.url,
    required this.imageUrl,
    required this.language,
    required this.source,
    required this.categories,
  });

  factory NewsData.fromJson(Map<String, dynamic> json) =>
      _$NewsDataFromJson(json);

  final String? uuid;
  final String? title;
  final String? description;
  final String? keywords;
  final String? snippet;
  final String? url;
  @JsonKey(name: 'image_url')
  final String? imageUrl;
  final String? language;
  final String? source;
  final List<String>? categories;

  Map<String, dynamic> toJson() => _$NewsDataToJson(this);

  @override
  List<Object?> get props => [
        uuid,
        title,
        description,
        keywords,
        snippet,
        url,
        imageUrl,
        language,
        source,
        categories,
      ];
}

@JsonSerializable()
class Meta extends Equatable {
  const Meta({
    required this.found,
    required this.returned,
    required this.limit,
    required this.page,
  });

  factory Meta.fromJson(Map<String, dynamic> json) => _$MetaFromJson(json);

  final int? found;
  final int? returned;
  final int? limit;
  final int? page;

  Map<String, dynamic> toJson() => _$MetaToJson(this);

  @override
  List<Object?> get props => [
        found,
        returned,
        limit,
        page,
      ];
}
