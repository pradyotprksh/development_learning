import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

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
