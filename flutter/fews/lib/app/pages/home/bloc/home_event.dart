import 'package:equatable/equatable.dart';
import 'package:fews/app/app.dart';

abstract class HomeEvent extends Equatable {
  const HomeEvent();

  @override
  List<Object?> get props => [];
}

class GetNews extends HomeEvent {
  const GetNews({
    this.pageNumber = 1,
    this.language = AppConstants.defaultLanguage,
  });

  final int pageNumber;
  final String language;

  @override
  List<Object?> get props => [pageNumber, language];
}

class UpdatePage extends HomeEvent {
  const UpdatePage({
    this.language = AppConstants.defaultLanguage,
  });

  final String language;

  @override
  List<Object?> get props => [language];
}
