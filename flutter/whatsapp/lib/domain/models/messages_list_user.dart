import 'dart:async';

import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class MessagesListUserDetails extends Equatable {
  const MessagesListUserDetails(
      this.directMessageDetails, this.otherUserDetails);

  final DirectMessageDetails directMessageDetails;
  final StreamController<UserDetails?> otherUserDetails;

  @override
  List<Object?> get props => [directMessageDetails, otherUserDetails];
}
