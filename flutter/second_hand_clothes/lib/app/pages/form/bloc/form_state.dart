import 'package:equatable/equatable.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// The current state of the form screen which will be used to update the
/// ui if required.
class FormState extends Equatable {
  const FormState({
    this.formData = const FormData.noData(),
    this.formStatus = FormzStatus.submissionInProgress,
    this.formItemDetails,
    this.errorMessage,
  });

  final FormData formData;
  final FormzStatus formStatus;
  final String? errorMessage;
  final List<FormItemStateDetails>? formItemDetails;

  FormState copyWith({
    FormData? formData,
    FormzStatus? formStatus,
    String? errorMessage,
    List<FormItemStateDetails>? formItemDetails,
  }) =>
      FormState(
        formData: formData ?? this.formData,
        formStatus: formStatus ?? this.formStatus,
        errorMessage: errorMessage ?? this.errorMessage,
        formItemDetails: formItemDetails ?? this.formItemDetails,
      );

  @override
  List<Object?> get props => [
        formData,
        formStatus,
        errorMessage,
        formItemDetails,
      ];
}
