import 'dart:async';

import 'package:whatsapp/domain/domain.dart';

class MessagesListUserDetails {
  MessagesListUserDetails(this.directMessageDetails, this.otherUserDetails);

  final DirectMessageDetails directMessageDetails;
  final StreamController<UserDetails?> otherUserDetails;
}
