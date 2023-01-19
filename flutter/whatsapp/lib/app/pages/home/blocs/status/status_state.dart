import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusState extends Equatable {
  const StatusState({
    this.otherStatus = const [],
    this.currentUserStatus = const [],
  });

  final List<StatusDetails> otherStatus;
  final List<StatusDetails> currentUserStatus;

  @override
  List<Object?> get props => [
        otherStatus,
        currentUserStatus,
      ];
}
