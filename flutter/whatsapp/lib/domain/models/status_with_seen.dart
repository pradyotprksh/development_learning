import 'package:get/get_rx/get_rx.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusWithSeenDetails {
  StatusWithSeenDetails(
    this.statusDetails,
    this.statusSeenBy,
  );

  final List<StatusDetails> statusDetails;
  final RxList<StatusSeenDetails> statusSeenBy;
}
