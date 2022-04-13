import 'dart:convert';

import 'package:stackexchange_api/core.dart';

class AnswersComments extends Result {
  AnswersComments({
    required this.items,
    required this.hasMore,
    required this.quotaMax,
    required this.quotaRemaining,
  });

  factory AnswersComments.fromJson(String str) => AnswersComments.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory AnswersComments.fromMap(Map<String, dynamic> json) => AnswersComments(
        items: List<AnswersCommentsItem>.from(
          (json['items'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => AnswersCommentsItem.fromMap(
              x as Map<String, dynamic>,
            ),
          ),
        ),
        hasMore: json['has_more'] as bool? ?? false,
        quotaMax: json['quota_max'] as int? ?? -1,
        quotaRemaining: json['quota_remaining'] as int? ?? -1,
      );

  final List<AnswersCommentsItem> items;
  final bool hasMore;
  final int quotaMax;
  final int quotaRemaining;

  AnswersComments copyWith({
    List<AnswersCommentsItem>? items,
    bool? hasMore,
    int? quotaMax,
    int? quotaRemaining,
  }) =>
      AnswersComments(
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

class AnswersCommentsItem {
  AnswersCommentsItem({
    required this.owner,
    required this.replyToUser,
    required this.edited,
    required this.score,
    required this.creationDate,
    required this.postId,
    required this.commentId,
    required this.contentLicense,
  });

  factory AnswersCommentsItem.fromJson(String str) =>
      AnswersCommentsItem.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory AnswersCommentsItem.fromMap(Map<String, dynamic> json) =>
      AnswersCommentsItem(
        owner: Owner.fromMap(
          json['owner'] as Map<String, dynamic>? ?? <String, dynamic>{},
        ),
        replyToUser: Owner.fromMap(
          json['reply_to_user'] as Map<String, dynamic>? ?? <String, dynamic>{},
        ),
        edited: json['edited'] as bool? ?? false,
        score: json['score'] as int? ?? -1,
        creationDate: json['creation_date'] as int? ?? -1,
        postId: json['post_id'] as int? ?? -1,
        commentId: json['comment_id'] as int? ?? -1,
        contentLicense: json['content_license'] as String? ?? '',
      );

  final Owner owner;
  final Owner replyToUser;
  final bool edited;
  final int score;
  final int creationDate;
  final int postId;
  final int commentId;
  final String contentLicense;

  AnswersCommentsItem copyWith({
    Owner? owner,
    Owner? replyToUser,
    bool? edited,
    int? score,
    int? creationDate,
    int? postId,
    int? commentId,
    String? contentLicense,
  }) =>
      AnswersCommentsItem(
        owner: owner ?? this.owner,
        replyToUser: replyToUser ?? this.replyToUser,
        edited: edited ?? this.edited,
        score: score ?? this.score,
        creationDate: creationDate ?? this.creationDate,
        postId: postId ?? this.postId,
        commentId: commentId ?? this.commentId,
        contentLicense: contentLicense ?? this.contentLicense,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'owner': owner.toMap(),
        'reply_to_user': replyToUser.toMap(),
        'edited': edited,
        'score': score,
        'creation_date': creationDate,
        'post_id': postId,
        'comment_id': commentId,
        'content_license': contentLicense,
      };
}
