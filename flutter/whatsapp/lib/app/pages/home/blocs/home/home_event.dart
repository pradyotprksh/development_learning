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
