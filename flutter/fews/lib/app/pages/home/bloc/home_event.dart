import 'package:equatable/equatable.dart';

abstract class HomeEvent extends Equatable {
  const HomeEvent();

  @override
  List<Object?> get props => [];
}

class GetNews extends HomeEvent {
  const GetNews({this.pageNumber = 1});

  final int pageNumber;
}

class UpdatePage extends HomeEvent {
  const UpdatePage();
}
