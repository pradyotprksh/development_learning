import 'package:equatable/equatable.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// The current state of the form screen which will be used to update the
/// ui if required.
class FormState extends Equatable {
  const FormState({
    this.formData = const FormData.noData(),
    this.formStatus = FormzStatus.submissionInProgress,
    this.formTextFieldDetails,
    this.formButtonDetails,
    this.formLabelDetails,
    this.errorMessage,
  });

  final FormData formData;
  final FormzStatus formStatus;
  final String? errorMessage;
  final List<FormTextFieldStateDetails>? formTextFieldDetails;
  final List<FormButtonStateDetails>? formButtonDetails;
  final List<FormLabelStateDetails>? formLabelDetails;

  FormState copyWith({
    FormData? formData,
    FormzStatus? formStatus,
    String? errorMessage,
    List<FormTextFieldStateDetails>? formTextFieldDetails,
    List<FormButtonStateDetails>? formButtonDetails,
    List<FormLabelStateDetails>? formLabelDetails,
  }) =>
      FormState(
        formData: formData ?? this.formData,
        formStatus: formStatus ?? this.formStatus,
        errorMessage: errorMessage ?? this.errorMessage,
        formTextFieldDetails: formTextFieldDetails ?? this.formTextFieldDetails,
        formButtonDetails: formButtonDetails ?? this.formButtonDetails,
        formLabelDetails: formLabelDetails ?? this.formLabelDetails,
      );

  @override
  List<Object?> get props => [
        formData,
        formStatus,
        errorMessage,
        formTextFieldDetails,
        formButtonDetails,
      ];
}
