import 'package:equatable/equatable.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// The current state of the display screen which will be used to update the
/// ui if required.
class DisplayState extends Equatable {
  const DisplayState({
    this.displayStatus = PageStatus.idle,
    this.displayEntity,
    this.errorMessage,
    this.showFloatingActionButton = false,
  });

  DisplayState copyWith({
    PageStatus? displayStatus,
    DisplayEntity? displayEntity,
    String? errorMessage,
    bool? showFloatingActionButton,
  }) =>
      DisplayState(
        displayStatus: displayStatus ?? this.displayStatus,
        displayEntity: displayEntity ?? this.displayEntity,
        errorMessage: errorMessage ?? this.errorMessage,
        showFloatingActionButton: showFloatingActionButton ?? false,
      );

  final PageStatus displayStatus;
  final DisplayEntity? displayEntity;
  final String? errorMessage;
  final bool showFloatingActionButton;

  @override
  List<Object?> get props => [
        displayStatus,
        displayEntity,
        errorMessage,
        showFloatingActionButton,
      ];
}
