import 'dart:async';

import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class DirectMessagesListUserDetails extends Equatable {
  const DirectMessagesListUserDetails(
      this.directMessageDetails, this.otherUserDetails);

  final DirectMessageDetails directMessageDetails;
  final StreamController<UserDetails?> otherUserDetails;

  @override
  List<Object?> get props => [directMessageDetails, otherUserDetails];
}
