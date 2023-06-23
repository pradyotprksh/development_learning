import 'package:equatable/equatable.dart';
import 'package:fews/domain/domain.dart';

enum PageState {
  loading,
  error,
  idle,
  success,
}

class HomeState extends Equatable {
  const HomeState({
    this.pageState = PageState.idle,
    this.errorMessage,
    this.newsData = const [],
    this.pageNumber = 1,
  });

  HomeState copyWith({
    PageState? pageState,
    String? errorMessage,
    List<NewsData>? newsData,
    int? pageNumber,
  }) =>
      HomeState(
        pageState: pageState ?? this.pageState,
        errorMessage: errorMessage,
        newsData: newsData ?? [],
        pageNumber: pageNumber ?? this.pageNumber,
      );

  final PageState pageState;
  final String? errorMessage;
  final List<NewsData> newsData;
  final int pageNumber;

  @override
  List<Object?> get props => [
        pageState,
        errorMessage,
        newsData,
      ];
}
