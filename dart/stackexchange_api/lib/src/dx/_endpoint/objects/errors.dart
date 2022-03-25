import 'dart:convert';

import 'package:stackexchange_api/core.dart';

class Errors extends Result {
  Errors({
    required this.items,
    required this.hasMore,
    required this.quotaMax,
    required this.quotaRemaining,
  });

  factory Errors.fromJson(String str) => Errors.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory Errors.fromMap(Map<String, dynamic> json) => Errors(
        items: List<ErrorItem>.from(
          (json['items'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => ErrorItem.fromMap(
              x as Map<String, dynamic>,
            ),
          ),
        ),
        hasMore: json['has_more'] as bool? ?? false,
        quotaMax: json['quota_max'] as int? ?? -1,
        quotaRemaining: json['quota_remaining'] as int? ?? -1,
      );

  final List<ErrorItem> items;

  final bool hasMore;

  final int quotaMax;

  final int quotaRemaining;

  Errors copyWith({
    List<ErrorItem>? items,
    bool? hasMore,
    int? quotaMax,
    int? quotaRemaining,
  }) =>
      Errors(
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
