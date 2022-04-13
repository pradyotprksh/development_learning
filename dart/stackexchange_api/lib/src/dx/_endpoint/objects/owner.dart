import 'dart:convert';

class Owner {
  Owner({
    required this.accountId,
    required this.reputation,
    required this.userId,
    required this.userType,
    required this.profileImage,
    required this.displayName,
    required this.link,
  });

  factory Owner.fromJson(String str) => Owner.fromMap(
        json.decode(str) as Map<String, dynamic>,
      );

  factory Owner.fromMap(Map<String, dynamic> json) => Owner(
        accountId: json['account_id'] as int? ?? -1,
        reputation: json['reputation'] as int? ?? -1,
        userId: json['user_id'] as int? ?? -1,
        userType: json['user_type'] as String? ?? '',
        profileImage: json['profile_image'] as String? ?? '',
        displayName: json['display_name'] as String? ?? '',
        link: json['link'] as String? ?? '',
      );

  final int accountId;
  final int reputation;
  final int userId;
  final String userType;
  final String profileImage;
  final String displayName;
  final String link;

  Owner copyWith({
    int? accountId,
    int? reputation,
    int? userId,
    String? userType,
    String? profileImage,
    String? displayName,
    String? link,
  }) =>
      Owner(
        accountId: accountId ?? this.accountId,
        reputation: reputation ?? this.reputation,
        userId: userId ?? this.userId,
        userType: userType ?? this.userType,
        profileImage: profileImage ?? this.profileImage,
        displayName: displayName ?? this.displayName,
        link: link ?? this.link,
      );

  String toJson() => json.encode(toMap());

  Map<String, dynamic> toMap() => <String, dynamic>{
        'account_id': accountId,
        'reputation': reputation,
        'user_id': userId,
        'user_type': userType,
        'profile_image': profileImage,
        'display_name': displayName,
        'link': link,
      };
}
