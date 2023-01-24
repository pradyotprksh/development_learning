import 'package:get/get_rx/get_rx.dart';
import 'package:whatsapp/domain/domain.dart';

class UserWithSingleStatusDetails {
  UserWithSingleStatusDetails(
    this.userId,
    this.statusDetails,
    this.userDetails,
  );

  final String userId;
  final List<StatusDetails> statusDetails;
  final Rx<UserDetails?> userDetails;
}
