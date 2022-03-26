import 'dart:convert';

import 'package:stackexchange_api/core.dart';

class Styling extends Result  {
  Styling({
    required this.tagBackgroundColor,
    required this.tagForegroundColor,
    required this.linkColor,
  });

  factory Styling.fromJson(String str) => Styling.fromMap(
    json.decode(str) as Map<String, dynamic>,
  );

  factory Styling.fromMap(Map<String, dynamic> json) => Styling(
    tagBackgroundColor: json['tag_background_color'] as String? ?? '',
    tagForegroundColor: json['tag_foreground_color'] as String? ?? '',
    linkColor: json['link_color'] as String? ?? '',
  );

  final String tagBackgroundColor;
  final String tagForegroundColor;
  final String linkColor;

  Styling copyWith({
    String? tagBackgroundColor,
    String? tagForegroundColor,
    String? linkColor,
  }) =>
      Styling(
        tagBackgroundColor: tagBackgroundColor ?? this.tagBackgroundColor,
        tagForegroundColor: tagForegroundColor ?? this.tagForegroundColor,
        linkColor: linkColor ?? this.linkColor,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
    'tag_background_color': tagBackgroundColor,
    'tag_foreground_color': tagForegroundColor,
    'link_color': linkColor,
  };
}
