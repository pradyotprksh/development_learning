import 'dart:convert';

import 'package:stackexchange_api/core.dart';

class ErrorItem extends Result {
  ErrorItem({
    required this.errorId,
    required this.description,
    required this.errorName,
  });

  factory ErrorItem.fromJson(String str) => ErrorItem.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory ErrorItem.fromMap(Map<String, dynamic> json) => ErrorItem(
        errorId: json['error_id'] as int? ?? -1,
        description: json['description'] as String? ?? '',
        errorName: json['error_name'] as String? ?? '',
      );

  final int errorId;

  final String description;

  final String errorName;

  ErrorItem copyWith({
    int? errorId,
    String? description,
    String? errorName,
  }) =>
      ErrorItem(
        errorId: errorId ?? this.errorId,
        description: description ?? this.description,
        errorName: errorName ?? this.errorName,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'error_id': errorId,
        'description': description,
        'error_name': errorName,
      };
}
