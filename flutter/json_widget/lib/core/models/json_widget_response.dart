import 'dart:convert';

import 'package:equatable/equatable.dart';
import 'package:json_annotation/json_annotation.dart';

part 'json_widget_response.g.dart';

JsonWidgetResponse jsonWidgetResponseFromJson(String str) =>
    JsonWidgetResponse.fromJson(
      json.decode(
        str,
      ),
    );

String jsonWidgetResponseToJson(JsonWidgetResponse data) => json.encode(
      data.toJson(),
    );

@JsonSerializable()
class JsonWidgetResponse extends Equatable {
  const JsonWidgetResponse({
    required this.pages,
  });

  factory JsonWidgetResponse.fromJson(Map<String, dynamic> json) =>
      _$JsonWidgetResponseFromJson(json);

  final List<Page>? pages;
  static const String pagesKey = 'pages';

  JsonWidgetResponse copyWith({
    List<Page>? pages,
  }) =>
      JsonWidgetResponse(
        pages: pages ?? this.pages,
      );

  Map<String, dynamic> toJson() => _$JsonWidgetResponseToJson(this);

  @override
  String toString() => '$pages, ';

  @override
  List<Object?> get props => [
        pages,
      ];
}

@JsonSerializable()
class Page extends Equatable {
  const Page({
    required this.id,
    required this.title,
    required this.subtitle,
    required this.route,
  });

  factory Page.fromJson(Map<String, dynamic> json) => _$PageFromJson(json);

  final String? id;
  static const String idKey = 'id';

  final String? title;
  static const String titleKey = 'title';

  final String? subtitle;
  static const String subtitleKey = 'subtitle';

  final String? route;
  static const String routeKey = 'route';

  Page copyWith({
    String? id,
    String? title,
    String? subtitle,
    String? route,
  }) =>
      Page(
        id: id ?? this.id,
        title: title ?? this.title,
        subtitle: subtitle ?? this.subtitle,
        route: route ?? this.route,
      );

  Map<String, dynamic> toJson() => _$PageToJson(this);

  @override
  String toString() => '$id, $title, $subtitle, $route, ';

  @override
  List<Object?> get props => [
        id,
        title,
        subtitle,
        route,
      ];
}

/*
{
	"pages": [
		{
			"id": "8JF1PIbWSIx7bbttue8R",
			"title": "Settings",
			"subtitle": "Select the settings which suits you well",
			"route": "/settings"
		}
	]
}*/
