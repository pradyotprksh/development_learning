import 'dart:convert';

import 'package:stackexchange_api/core.dart';

class RelatedSite extends Result {
  RelatedSite({
    required this.relation,
    required this.apiSiteParameter,
    required this.siteUrl,
    required this.name,
  });

  factory RelatedSite.fromJson(String str) => RelatedSite.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory RelatedSite.fromMap(Map<String, dynamic> json) => RelatedSite(
        relation: json['relation'] as String? ?? '',
        apiSiteParameter: json['api_site_parameter'] as String? ?? '',
        siteUrl: json['site_url'] as String? ?? '',
        name: json['name'] as String? ?? '',
      );

  final String relation;
  final String apiSiteParameter;
  final String siteUrl;
  final String name;

  RelatedSite copyWith({
    String? relation,
    String? apiSiteParameter,
    String? siteUrl,
    String? name,
  }) =>
      RelatedSite(
        relation: relation ?? this.relation,
        apiSiteParameter: apiSiteParameter ?? this.apiSiteParameter,
        siteUrl: siteUrl ?? this.siteUrl,
        name: name ?? this.name,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'relation': relation,
        'api_site_parameter': apiSiteParameter,
        'site_url': siteUrl,
        'name': name,
      };
}
