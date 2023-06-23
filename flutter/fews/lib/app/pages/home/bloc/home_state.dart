import 'package:equatable/equatable.dart';

enum PageState {
  loading,
  error,
  idle,
  success,
}

class HomeState extends Equatable {
  const HomeState({
    this.pageState = PageState.idle,
  });

  HomeState copyWith({
    PageState? pageState,
  }) =>
      HomeState(
        pageState: pageState ?? this.pageState,
      );

  final PageState pageState;

  @override
  List<Object?> get props => [
        pageState,
      ];
}
