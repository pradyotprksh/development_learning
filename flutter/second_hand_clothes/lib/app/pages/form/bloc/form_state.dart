import 'package:equatable/equatable.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';

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
    this.formRowDetails,
    this.formColumnDetails,
    this.navigationAction,
    this.loadingMessage,
  });

  final FormData formData;
  final FormzStatus formStatus;
  final String? errorMessage;
  final String? loadingMessage;
  final List<FormTextFieldStateDetails>? formTextFieldDetails;
  final List<FormButtonStateDetails>? formButtonDetails;
  final List<FormLabelStateDetails>? formLabelDetails;
  final List<FormRowStateDetails>? formRowDetails;
  final List<FormColumnStateDetails>? formColumnDetails;
  final NavigationAction? navigationAction;

  FormState copyWith({
    FormData? formData,
    FormzStatus? formStatus,
    String? errorMessage,
    List<FormTextFieldStateDetails>? formTextFieldDetails,
    List<FormButtonStateDetails>? formButtonDetails,
    List<FormLabelStateDetails>? formLabelDetails,
    List<FormRowStateDetails>? formRowDetails,
    List<FormColumnStateDetails>? formColumnDetails,
    NavigationAction? navigationAction,
    String? loadingMessage,
  }) =>
      FormState(
        formData: formData ?? this.formData,
        formStatus: formStatus ?? this.formStatus,
        errorMessage: errorMessage ?? this.errorMessage,
        formTextFieldDetails: formTextFieldDetails ?? this.formTextFieldDetails,
        formButtonDetails: formButtonDetails ?? this.formButtonDetails,
        formLabelDetails: formLabelDetails ?? this.formLabelDetails,
        formRowDetails: formRowDetails ?? this.formRowDetails,
        formColumnDetails: formColumnDetails ?? this.formColumnDetails,
        navigationAction: navigationAction,
        loadingMessage: loadingMessage ?? this.loadingMessage,
      );

  @override
  List<Object?> get props => [
        formData,
        formStatus,
        errorMessage,
        formTextFieldDetails,
        formButtonDetails,
        formRowDetails,
        formRowDetails,
        formColumnDetails,
        navigationAction,
        loadingMessage,
      ];
}
