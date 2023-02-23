import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/domain/domain.dart';

class StatusState extends Equatable {
  const StatusState({
    this.otherStatus = const [],
    this.currentUserStatus,
  });

  StatusState copyWith({
    List<UserWithSingleStatusDetails>? otherStatus,
    UserWithSingleStatusDetails? currentUserStatus,
  }) =>
      StatusState(
        otherStatus: otherStatus ?? this.otherStatus,
        currentUserStatus: currentUserStatus ?? this.currentUserStatus,
      );

  final List<UserWithSingleStatusDetails> otherStatus;
  final UserWithSingleStatusDetails? currentUserStatus;

  @override
  List<Object?> get props => [
        otherStatus,
        currentUserStatus,
      ];
}
