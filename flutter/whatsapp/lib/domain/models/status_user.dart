import 'package:get/get_rx/get_rx.dart';
import 'package:whatsapp/domain/domain.dart';

class UserWithSingleStatusDetails {
  UserWithSingleStatusDetails(
    this.userId,
    this.statusWithSeenDetails,
    this.userDetails,
  );

  final String userId;
  final Rx<UserDetails?> userDetails;
  final List<StatusWithSeenDetails> statusWithSeenDetails;
}
