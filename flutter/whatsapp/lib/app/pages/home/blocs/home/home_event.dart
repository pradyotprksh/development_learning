import 'package:equatable/equatable.dart';

abstract class HomeEvent extends Equatable {
  const HomeEvent();

  @override
  List<Object?> get props => [];
}

class UpdateLoginHistory extends HomeEvent {
  const UpdateLoginHistory();
}

class DeleteCallLogs extends HomeEvent {
  const DeleteCallLogs();
}

class ApplicationBackgroundCheck extends HomeEvent {
  const ApplicationBackgroundCheck();
}

class AskForPinConfirmation extends HomeEvent {
  const AskForPinConfirmation(this.lastPinConfirmationTimeStamp);

  final int? lastPinConfirmationTimeStamp;
}

class PinVerified extends HomeEvent {
  const PinVerified();
}
