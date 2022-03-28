import 'dart:convert';

import 'package:stackexchange_api/core.dart';

class Notifications extends Result {
  Notifications({
    required this.items,
    required this.hasMore,
    required this.quotaMax,
    required this.quotaRemaining,
  });

  factory Notifications.fromJson(String str) => Notifications.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory Notifications.fromMap(Map<String, dynamic> json) => Notifications(
        items: List<NotificationItem>.from(
          (json['items'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => NotificationItem.fromMap(
              x as Map<String, dynamic>,
            ),
          ),
        ),
        hasMore: json['has_more'] as bool? ?? false,
        quotaMax: json['quota_max'] as int? ?? -1,
        quotaRemaining: json['quota_remaining'] as int? ?? -1,
      );

  final List<NotificationItem> items;
  final bool hasMore;
  final int quotaMax;
  final int quotaRemaining;

  Notifications copyWith({
    List<NotificationItem>? items,
    bool? hasMore,
    int? quotaMax,
    int? quotaRemaining,
  }) =>
      Notifications(
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

class NotificationItem {
  NotificationItem({
    required this.site,
    required this.isUnread,
    required this.creationDate,
    required this.notificationType,
    required this.body,
  });

  factory NotificationItem.fromJson(String str) => NotificationItem.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory NotificationItem.fromMap(Map<String, dynamic> json) => NotificationItem(
        site: Site.fromMap(
          json['site'] as Map<String, dynamic>? ?? <String, dynamic>{},
        ),
        isUnread: json['is_unread'] as bool? ?? false,
        creationDate: json['creation_date'] as int? ?? -1,
        notificationType: json['notification_type'] as String? ?? '',
        body: json['body'] as String? ?? '',
      );

  final Site site;
  final bool isUnread;
  final int creationDate;
  final String notificationType;
  final String body;

  NotificationItem copyWith({
    Site? site,
    bool? isUnread,
    int? creationDate,
    String? notificationType,
    String? body,
  }) =>
      NotificationItem(
        site: site ?? this.site,
        isUnread: isUnread ?? this.isUnread,
        creationDate: creationDate ?? this.creationDate,
        notificationType: notificationType ?? this.notificationType,
        body: body ?? this.body,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'site': site.toMap(),
        'is_unread': isUnread,
        'creation_date': creationDate,
        'notification_type': notificationType,
        'body': body,
      };
}
