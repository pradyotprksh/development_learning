import 'dart:convert';

import 'package:equatable/equatable.dart';

UserDetails userDetailsFromJson(String str) => UserDetails.fromJson(
      json.decode(str) as Map<String, dynamic>,
    );

class UserDetails extends Equatable {
  const UserDetails({
    required this.emailId,
    required this.displayName,
    required this.uid,
    required this.profilePic,
  });

  factory UserDetails.fromJson(Map<String, dynamic> json) => UserDetails(
        emailId: json['emailId'] as String?,
        displayName: json['displayName'] as String?,
        uid: json['uid'] as String?,
        profilePic: json['profilePic'] as String?,
      );

  final String? emailId;
  final String? displayName;
  final String? uid;
  final String? profilePic;

  @override
  List<Object?> get props => [
        emailId,
        displayName,
        uid,
        profilePic,
      ];
}
