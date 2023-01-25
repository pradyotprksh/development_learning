import 'package:get/get_rx/get_rx.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusWithSeenDetails {
  StatusWithSeenDetails(
    this.statusDetails,
    this.statusSeenBy,
    this.isSeenByCurrentUser,
  );

  final StatusDetails statusDetails;
  final RxBool isSeenByCurrentUser;
  final RxList<StatusSeenDetails> statusSeenBy;
}
