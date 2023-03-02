import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

abstract class MessageDetailsEvent extends Equatable {
  const MessageDetailsEvent();

  @override
  List<Object?> get props => [];
}

class FetchDetails extends MessageDetailsEvent {
  const FetchDetails(this.messageRouteDetails);

  final MessageRouteDetails messageRouteDetails;
}
