import 'package:whatsapp/domain/domain.dart';

class CallDetailsArguments {
  CallDetailsArguments({
    required this.userDetails,
    required this.isPhoneCall,
    required this.isVideoCall,
    required this.isGroupCall,
    this.groupId,
  });

  final List<UserDetails?> userDetails;
  final String? groupId;
  final bool isPhoneCall;
  final bool isVideoCall;
  final bool isGroupCall;
}
