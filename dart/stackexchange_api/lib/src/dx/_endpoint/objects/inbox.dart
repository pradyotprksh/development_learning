import 'dart:convert';

import 'package:stackexchange_api/core.dart';

class Inbox extends Result {
  Inbox({
    required this.items,
    required this.hasMore,
    required this.quotaMax,
    required this.quotaRemaining,
  });

  factory Inbox.fromJson(String str) => Inbox.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory Inbox.fromMap(Map<String, dynamic> json) => Inbox(
        items: List<InboxItem>.from(
          (json['items'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => InboxItem.fromMap(
              x as Map<String, dynamic>,
            ),
          ),
        ),
        hasMore: json['has_more'] as bool? ?? false,
        quotaMax: json['quota_max'] as int? ?? -1,
        quotaRemaining: json['quota_remaining'] as int? ?? -1,
      );

  final List<InboxItem> items;
  final bool hasMore;
  final int quotaMax;
  final int quotaRemaining;

  Inbox copyWith({
    List<InboxItem>? items,
    bool? hasMore,
    int? quotaMax,
    int? quotaRemaining,
  }) =>
      Inbox(
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

class InboxItem {
  InboxItem({
    required this.site,
    required this.isUnread,
    required this.creationDate,
    required this.commentId,
    required this.answerId,
    required this.itemType,
    required this.link,
    required this.title,
    required this.questionId,
  });

  factory InboxItem.fromJson(String str) => InboxItem.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory InboxItem.fromMap(Map<String, dynamic> json) => InboxItem(
        site: Site.fromMap(
          json['site'] as Map<String, dynamic>? ?? <String, dynamic>{},
        ),
        isUnread: json['is_unread'] as bool? ?? false,
        creationDate: json['creation_date'] as int? ?? -1,
        commentId: json['comment_id'] as int? ?? -1,
        answerId: json['answer_id'] as int? ?? -1,
        itemType: json['item_type'] as String? ?? '',
        link: json['link'] as String? ?? '',
        title: json['title'] as String? ?? '',
        questionId: json['question_id'] as int? ?? -1,
      );

  final Site site;
  final bool isUnread;
  final int creationDate;
  final int commentId;
  final int answerId;
  final String itemType;
  final String link;
  final String title;
  final int questionId;

  InboxItem copyWith({
    Site? site,
    bool? isUnread,
    int? creationDate,
    int? commentId,
    int? answerId,
    String? itemType,
    String? link,
    String? title,
    int? questionId,
  }) =>
      InboxItem(
        site: site ?? this.site,
        isUnread: isUnread ?? this.isUnread,
        creationDate: creationDate ?? this.creationDate,
        commentId: commentId ?? this.commentId,
        answerId: answerId ?? this.answerId,
        itemType: itemType ?? this.itemType,
        link: link ?? this.link,
        title: title ?? this.title,
        questionId: questionId ?? this.questionId,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'site': site.toMap(),
        'is_unread': isUnread,
        'creation_date': creationDate,
        'comment_id': commentId,
        'answer_id': answerId,
        'item_type': itemType,
        'link': link,
        'title': title,
        'question_id': questionId,
      };
}
