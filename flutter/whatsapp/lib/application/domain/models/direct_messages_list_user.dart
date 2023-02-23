import 'dart:async';

import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/domain/domain.dart';

class DirectMessagesListUserDetails extends Equatable {
  const DirectMessagesListUserDetails(
      this.directMessageDetails, this.otherUserDetails);

  final DirectMessageDetails directMessageDetails;
  final Stream<UserDetails?> otherUserDetails;

  @override
  List<Object?> get props => [directMessageDetails, otherUserDetails];
}
