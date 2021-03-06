import 'dart:convert';

import 'package:stackexchange_api/core.dart';

class Answers extends Result {
  Answers({
    required this.items,
    required this.hasMore,
    required this.backoff,
    required this.quotaMax,
    required this.quotaRemaining,
  });

  factory Answers.fromJson(String str) => Answers.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory Answers.fromMap(Map<String, dynamic> json) => Answers(
        items: List<AnswerItem>.from(
          (json['items'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => AnswerItem.fromMap(
              x as Map<String, dynamic>,
            ),
          ),
        ),
        hasMore: json['has_more'] as bool? ?? false,
        backoff: json['backoff'] as int? ?? -1,
        quotaMax: json['quota_max'] as int? ?? -1,
        quotaRemaining: json['quota_remaining'] as int? ?? -1,
      );

  final List<AnswerItem> items;
  final bool hasMore;
  final int backoff;
  final int quotaMax;
  final int quotaRemaining;

  Answers copyWith({
    List<AnswerItem>? items,
    bool? hasMore,
    int? backoff,
    int? quotaMax,
    int? quotaRemaining,
  }) =>
      Answers(
        items: items ?? this.items,
        hasMore: hasMore ?? this.hasMore,
        backoff: backoff ?? this.backoff,
        quotaMax: quotaMax ?? this.quotaMax,
        quotaRemaining: quotaRemaining ?? this.quotaRemaining,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'items': List<dynamic>.from(
          items.map<dynamic>(
            (dynamic x) => x.toMap(),
          ),
        ),
        'has_more': hasMore,
        'backoff': backoff,
        'quota_max': quotaMax,
        'quota_remaining': quotaRemaining,
      };
}

class AnswerItem extends Result {
  AnswerItem({
    required this.owner,
    required this.isAccepted,
    required this.score,
    required this.lastActivityDate,
    required this.lastEditDate,
    required this.creationDate,
    required this.answerId,
    required this.questionId,
    required this.contentLicense,
  });

  factory AnswerItem.fromJson(String str) => AnswerItem.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory AnswerItem.fromMap(Map<String, dynamic> json) => AnswerItem(
        owner: Owner.fromMap(
          json['owner'] as Map<String, dynamic>? ?? <String, dynamic>{},
        ),
        isAccepted: json['is_accepted'] as bool? ?? false,
        score: json['score'] as int? ?? -1,
        lastActivityDate: json['last_activity_date'] as int? ?? -1,
        lastEditDate: json['last_edit_date'] as int? ?? -1,
        creationDate: json['creation_date'] as int? ?? -1,
        answerId: json['answer_id'] as int? ?? -1,
        questionId: json['question_id'] as int? ?? -1,
        contentLicense: json['content_license'] as String? ?? '',
      );

  final Owner owner;
  final bool isAccepted;
  final int score;
  final int lastActivityDate;
  final int lastEditDate;
  final int creationDate;
  final int answerId;
  final int questionId;
  final String contentLicense;

  AnswerItem copyWith({
    Owner? owner,
    bool? isAccepted,
    int? score,
    int? lastActivityDate,
    int? lastEditDate,
    int? creationDate,
    int? answerId,
    int? questionId,
    String? contentLicense,
  }) =>
      AnswerItem(
        owner: owner ?? this.owner,
        isAccepted: isAccepted ?? this.isAccepted,
        score: score ?? this.score,
        lastActivityDate: lastActivityDate ?? this.lastActivityDate,
        lastEditDate: lastEditDate ?? this.lastEditDate,
        creationDate: creationDate ?? this.creationDate,
        answerId: answerId ?? this.answerId,
        questionId: questionId ?? this.questionId,
        contentLicense: contentLicense ?? this.contentLicense,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'owner': owner.toMap(),
        'is_accepted': isAccepted,
        'score': score,
        'last_activity_date': lastActivityDate,
        'last_edit_date': lastEditDate,
        'creation_date': creationDate,
        'answer_id': answerId,
        'question_id': questionId,
        'content_license': contentLicense,
      };
}
