import 'package:equatable/equatable.dart';

abstract class CallsEvent extends Equatable {
  const CallsEvent();

  @override
  List<Object?> get props => [];
}

class FetchCurrentUserCalls extends CallsEvent {
  const FetchCurrentUserCalls();
}
