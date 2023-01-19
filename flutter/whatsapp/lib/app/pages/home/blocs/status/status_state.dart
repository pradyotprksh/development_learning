import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusState extends Equatable {
  const StatusState({
    this.otherStatus = const [],
    this.currentUserStatus = const [],
  });

  StatusState copyWith({
    List<UserWithSingleStatusDetails>? otherStatus,
    List<UserWithSingleStatusDetails>? currentUserStatus,
  }) =>
      StatusState(
        otherStatus: otherStatus ?? this.otherStatus,
        currentUserStatus: otherStatus ?? this.currentUserStatus,
      );

  final List<UserWithSingleStatusDetails> otherStatus;
  final List<UserWithSingleStatusDetails> currentUserStatus;

  @override
  List<Object?> get props => [
        otherStatus,
        currentUserStatus,
      ];
}
