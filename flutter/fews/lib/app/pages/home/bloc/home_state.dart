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
  });

  HomeState copyWith({
    PageState? pageState,
    String? errorMessage,
    List<NewsData>? newsData,
  }) =>
      HomeState(
        pageState: pageState ?? this.pageState,
        errorMessage: errorMessage,
        newsData: newsData ?? [],
      );

  final PageState pageState;
  final String? errorMessage;
  final List<NewsData> newsData;

  @override
  List<Object?> get props => [
        pageState,
        errorMessage,
        newsData,
      ];
}
