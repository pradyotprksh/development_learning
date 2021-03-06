import 'dart:convert';

import 'package:stackexchange_api/core.dart';

class Sites extends Result {
  Sites({
    required this.items,
    required this.hasMore,
    required this.quotaMax,
    required this.quotaRemaining,
  });

  factory Sites.fromJson(String str) => Sites.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory Sites.fromMap(Map<String, dynamic> json) => Sites(
        items: List<SiteItem>.from(
          (json['items'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => SiteItem.fromMap(
              x as Map<String, dynamic>,
            ),
          ),
        ),
        hasMore: json['has_more'] as bool? ?? false,
        quotaMax: json['quota_max'] as int? ?? -1,
        quotaRemaining: json['quota_remaining'] as int? ?? -1,
      );

  final List<SiteItem> items;
  final bool hasMore;
  final int quotaMax;
  final int quotaRemaining;

  Sites copyWith({
    List<SiteItem>? items,
    bool? hasMore,
    int? quotaMax,
    int? quotaRemaining,
  }) =>
      Sites(
        items: items ?? this.items,
        hasMore: hasMore ?? this.hasMore,
        quotaMax: quotaMax ?? this.quotaMax,
        quotaRemaining: quotaRemaining ?? this.quotaRemaining,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'items': List<dynamic>.from(
          items.map<dynamic>(
            (x) => x.toMap(),
          ),
        ),
        'has_more': hasMore,
        'quota_max': quotaMax,
        'quota_remaining': quotaRemaining,
      };
}

class SiteItem {
  SiteItem({
    required this.aliases,
    required this.styling,
    required this.relatedSites,
    required this.markdownExtensions,
    required this.launchDate,
    required this.openBetaDate,
    required this.siteState,
    required this.highResolutionIconUrl,
    required this.faviconUrl,
    required this.iconUrl,
    required this.audience,
    required this.siteUrl,
    required this.apiSiteParameter,
    required this.logoUrl,
    required this.name,
    required this.siteType,
    required this.twitterAccount,
    required this.closedBetaDate,
  });

  factory SiteItem.fromJson(String str) => SiteItem.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory SiteItem.fromMap(Map<String, dynamic> json) => SiteItem(
        aliases: List<String>.from(
          (json['aliases'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => x as String,
          ),
        ),
        styling: Styling.fromMap(
          json['styling'] as Map<String, dynamic>? ?? <String, dynamic>{},
        ),
        relatedSites: List<RelatedSite>.from(
          (json['related_sites'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => RelatedSite.fromMap(
              x as Map<String, dynamic>,
            ),
          ),
        ),
        markdownExtensions: List<String>.from(
          (json['markdown_extensions'] as List<dynamic>? ?? <dynamic>[])
              .map<String>(
            (dynamic x) => x as String,
          ),
        ),
        launchDate: json['launch_date'] as int? ?? -1,
        openBetaDate: json['open_beta_date'] as int? ?? -1,
        siteState: json['site_state'] as String? ?? '',
        highResolutionIconUrl:
            json['high_resolution_icon_url'] as String? ?? '',
        faviconUrl: json['favicon_url'] as String? ?? '',
        iconUrl: json['icon_url'] as String? ?? '',
        audience: json['audience'] as String? ?? '',
        siteUrl: json['site_url'] as String? ?? '',
        apiSiteParameter: json['api_site_parameter'] as String? ?? '',
        logoUrl: json['logo_url'] as String? ?? '',
        name: json['name'] as String? ?? '',
        siteType: json['site_type'] as String? ?? '',
        twitterAccount: json['twitter_account'] as String? ?? '',
        closedBetaDate: json['closed_beta_date'] as int? ?? -1,
      );

  final List<String> aliases;
  final Styling styling;
  final List<RelatedSite> relatedSites;
  final List<String> markdownExtensions;
  final int launchDate;
  final int openBetaDate;
  final String siteState;
  final String highResolutionIconUrl;
  final String faviconUrl;
  final String iconUrl;
  final String audience;
  final String siteUrl;
  final String apiSiteParameter;
  final String logoUrl;
  final String name;
  final String siteType;
  final String twitterAccount;
  final int closedBetaDate;

  SiteItem copyWith({
    List<String>? aliases,
    Styling? styling,
    List<RelatedSite>? relatedSites,
    List<String>? markdownExtensions,
    int? launchDate,
    int? openBetaDate,
    String? siteState,
    String? highResolutionIconUrl,
    String? faviconUrl,
    String? iconUrl,
    String? audience,
    String? siteUrl,
    String? apiSiteParameter,
    String? logoUrl,
    String? name,
    String? siteType,
    String? twitterAccount,
    int? closedBetaDate,
  }) =>
      SiteItem(
        aliases: aliases ?? this.aliases,
        styling: styling ?? this.styling,
        relatedSites: relatedSites ?? this.relatedSites,
        markdownExtensions: markdownExtensions ?? this.markdownExtensions,
        launchDate: launchDate ?? this.launchDate,
        openBetaDate: openBetaDate ?? this.openBetaDate,
        siteState: siteState ?? this.siteState,
        highResolutionIconUrl:
            highResolutionIconUrl ?? this.highResolutionIconUrl,
        faviconUrl: faviconUrl ?? this.faviconUrl,
        iconUrl: iconUrl ?? this.iconUrl,
        audience: audience ?? this.audience,
        siteUrl: siteUrl ?? this.siteUrl,
        apiSiteParameter: apiSiteParameter ?? this.apiSiteParameter,
        logoUrl: logoUrl ?? this.logoUrl,
        name: name ?? this.name,
        siteType: siteType ?? this.siteType,
        twitterAccount: twitterAccount ?? this.twitterAccount,
        closedBetaDate: closedBetaDate ?? this.closedBetaDate,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'aliases': List<dynamic>.from(
          aliases.map<dynamic>(
            (x) => x,
          ),
        ),
        'styling': styling.toMap(),
        'related_sites': List<dynamic>.from(
          relatedSites.map<dynamic>(
            (x) => x.toMap(),
          ),
        ),
        'markdown_extensions': List<dynamic>.from(
          markdownExtensions.map<dynamic>(
            (x) => x,
          ),
        ),
        'launch_date': launchDate,
        'open_beta_date': openBetaDate,
        'site_state': siteState,
        'high_resolution_icon_url': highResolutionIconUrl,
        'favicon_url': faviconUrl,
        'icon_url': iconUrl,
        'audience': audience,
        'site_url': siteUrl,
        'api_site_parameter': apiSiteParameter,
        'logo_url': logoUrl,
        'name': name,
        'site_type': siteType,
        'twitter_account': twitterAccount,
        'closed_beta_date': closedBetaDate,
      };
}
