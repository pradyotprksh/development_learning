import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

class HomeState extends Equatable {
  const HomeState({
    this.pageState = PageState.idle,
    this.askForPinConfirmation = false,
  });

  HomeState copyWith({
    PageState? pageState,
    bool? askForPinConfirmation,
  }) =>
      HomeState(
        pageState: pageState ?? this.pageState,
        askForPinConfirmation:
            askForPinConfirmation ?? this.askForPinConfirmation,
      );

  final PageState pageState;
  final bool askForPinConfirmation;

  @override
  List<Object?> get props => [
        pageState,
        askForPinConfirmation,
      ];
}
