import 'package:equatable/equatable.dart';

/// A class for user details which will hold the details of the user.
class UserDetails extends Equatable {
  /// [emailId] = Email address of the user
  ///
  /// [displayName] = Name of the user
  const UserDetails({
    required this.emailId,
    required this.displayName,
    required this.uid,
  });

  final String? emailId;
  final String? displayName;
  final String? uid;

  @override
  List<Object?> get props => [
        emailId,
        displayName,
        uid,
      ];
}
