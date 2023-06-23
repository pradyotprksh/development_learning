import 'package:json_annotation/json_annotation.dart';

part 'news.g.dart';

@JsonSerializable()
class News {
  News({
    required this.meta,
    required this.data,
  });

  factory News.fromJson(Map<String, dynamic> json) => _$NewsFromJson(json);

  Meta meta;
  List<NewsData> data;

  Map<String, dynamic> toJson() => _$NewsToJson(this);
}

@JsonSerializable()
class NewsData {
  NewsData({
    required this.uuid,
    required this.title,
    required this.description,
    required this.keywords,
    required this.snippet,
    required this.url,
    required this.imageUrl,
    required this.language,
    required this.publishedAt,
    required this.source,
    required this.categories,
    this.relevanceScore,
  });

  factory NewsData.fromJson(Map<String, dynamic> json) =>
      _$NewsDataFromJson(json);

  String uuid;
  String title;
  String description;
  String keywords;
  String snippet;
  String url;
  String imageUrl;
  String language;
  DateTime publishedAt;
  String source;
  List<String> categories;
  dynamic relevanceScore;

  Map<String, dynamic> toJson() => _$NewsDataToJson(this);
}

@JsonSerializable()
class Meta {
  Meta({
    required this.found,
    required this.returned,
    required this.limit,
    required this.page,
  });

  factory Meta.fromJson(Map<String, dynamic> json) => _$MetaFromJson(json);

  int found;
  int returned;
  int limit;
  int page;

  Map<String, dynamic> toJson() => _$MetaToJson(this);
}
