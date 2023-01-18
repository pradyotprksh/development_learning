import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

abstract class AddStatusConstants {
  static const defaultColor = 0xfff44336;
}

class AddStatusState extends Equatable {
  const AddStatusState({
    this.chosenColor = AddStatusConstants.defaultColor,
    this.currentFontFamily = Constants.defaultFontFamily,
    this.pageState = PageState.idle,
  });

  AddStatusState copyWith({
    int? chosenColor,
    String? currentFontFamily,
    PageState? pageState,
  }) =>
      AddStatusState(
        chosenColor: chosenColor ?? this.chosenColor,
        currentFontFamily: currentFontFamily ?? this.currentFontFamily,
        pageState: pageState ?? this.pageState,
      );

  final int chosenColor;
  final String currentFontFamily;
  final PageState pageState;

  @override
  List<Object?> get props => [
        chosenColor,
        currentFontFamily,
        pageState,
      ];
}
