import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

abstract class AddStatusConstants {
  static const defaultColor = 0xfff44336;
}

class AddStatusState extends Equatable {
  const AddStatusState({
    this.chosenColor = AddStatusConstants.defaultColor,
    this.currentFontFamily = AppConstants.defaultFontFamily,
    this.pageState = PageState.idle,
    this.fileDetails,
  });

  AddStatusState copyWith({
    int? chosenColor,
    String? currentFontFamily,
    PageState? pageState,
    FileDetails? fileDetails,
  }) =>
      AddStatusState(
        chosenColor: chosenColor ?? this.chosenColor,
        currentFontFamily: currentFontFamily ?? this.currentFontFamily,
        pageState: pageState ?? this.pageState,
        fileDetails: fileDetails ?? this.fileDetails,
      );

  final int chosenColor;
  final String currentFontFamily;
  final PageState pageState;
  final FileDetails? fileDetails;

  @override
  List<Object?> get props => [
        chosenColor,
        currentFontFamily,
        pageState,
        fileDetails,
      ];
}
