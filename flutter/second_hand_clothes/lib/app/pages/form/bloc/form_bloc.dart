import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A bloc class for the form screen which will listen to any changes raised
/// by form event and update the state by updating the value of form state.
class FormBloc extends Bloc<FormEvent, FormState> {
  FormBloc() : super(const FormState()) {
    on<GetDetailsFormEvent>(_getFormDetails);
    on<TextFieldChangeFormEvent>(_onTextFieldChangeEvent);
  }

  /// Whenever [GetDetailsFormEvent] is raised then this method will be called.
  void _getFormDetails(
    GetDetailsFormEvent event,
    Emitter<FormState> emit,
  ) async {
    emit(
      state.copyWith(
        formStatus: FormzStatus.submissionInProgress,
      ),
    );

    final formId = event.formId;
    if (formId == FormConstants().loginFormId) {
      try {
        final loginJson = await UtilsAssets().getJSONData(
          UtilsAssets().loginFormId,
        );
        final formData = formDataFromJson(loginJson);
        final formItemsStates = formData.items
            ?.map(
              (item) => FormItemStateDetails(
                itemId: item.id,
                textFieldValue: '',
                validators: item.validate,
                errorMessage: item.style?.error,
              ),
            )
            .toList();
        await Future.delayed(
          const Duration(seconds: 2),
          () {
            emit(
              state.copyWith(
                formData: formData,
                formStatus: FormzStatus.submissionSuccess,
                formItemDetails: formItemsStates,
              ),
            );
          },
        );
      } catch (_) {
        emit(
          state.copyWith(
            formStatus: FormzStatus.invalid,
            errorMessage:
                'Something went wrong with the form parsing. Please try again after sometime, we are looking into it.',
          ),
        );
      }
    } else {
      emit(
        state.copyWith(
          formStatus: FormzStatus.invalid,
          errorMessage: 'Wrong form id',
        ),
      );
    }
  }

  /// Whenever [TextFieldChangeFormEvent] is sent then this method will be
  /// called.
  void _onTextFieldChangeEvent(
    TextFieldChangeFormEvent event,
    Emitter<FormState> emit,
  ) {
    var formInputs = <FormzInput>[];

    final updatedFormItemDetails = state.formItemDetails?.map(
      (item) {
        if (item.itemId == event.itemId) {
          String? errorMessage;
          item.validators?.forEach((validator) {
            if (validator == 'EmailAddressModels') {
              formInputs.add(EmailAddressModels.dirty(event.value));
            } else if (validator == 'PasswordModels') {
              formInputs.add(PasswordModels.dirty(event.value));
            }
          });

          final formState = Formz.validate(formInputs);
          if (formState == FormzStatus.invalid) {
            errorMessage = item.errorMessage;
          } else {
            errorMessage = '';
          }

          return item.copyWith(
            textFieldValue: event.value,
            currentErrorMessage: errorMessage,
          );
        }

        return item;
      },
    ).toList();

    // TODO: Fix the formStatus value, valid only when all text fields are valid.
    emit(
      state.copyWith(
        formStatus: Formz.validate(formInputs),
        formItemDetails: updatedFormItemDetails,
      ),
    );
  }
}
