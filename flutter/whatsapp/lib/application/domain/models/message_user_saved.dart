import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/domain/domain.dart';

class MessageUserSavedDetails extends Equatable {
  const MessageUserSavedDetails(
    this.savedMessageDetails,
    this.userDetails,
    this.messageDetails,
  );

  final SavedMessageDetails savedMessageDetails;
  final Stream<UserDetails?> userDetails;
  final Stream<SingleMessageDetails?>? messageDetails;

  @override
  List<Object?> get props => [
        savedMessageDetails,
        userDetails,
        messageDetails,
      ];
}
