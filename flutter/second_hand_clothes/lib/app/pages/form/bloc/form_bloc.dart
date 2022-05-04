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
    on<ActionsFormEvent>(_onActionsFormEvent);
    on<EnableDisableComponentEvent>(_onEnableDisableComponentEvent);
    on<CheckButtonStateEvent>(_onCheckButtonStateEvent);
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

        final formLabelState = formData.items
            ?.where((element) => element.type == ItemType.label)
            .map(
              (item) => FormLabelStateDetails(
                text: item.text ?? '',
                itemId: item.id,
                textAlign: item.style?.textAlignment,
                textStyle: item.style?.style,
              ),
            )
            .toList();

        final formTextFieldState = formData.items
            ?.where((element) => element.type == ItemType.textField)
            .map(
              (item) => FormTextFieldStateDetails(
                textFieldValue: '',
                itemId: item.id,
                validateTo: item.validateTo,
                validateOn: item.validateOn,
                errorMessage: item.style?.error,
                icon: item.style?.icon,
                label: item.style?.label,
                hint: item.style?.hint,
                keyboardType: item.style?.keyboardType,
                textInputAction: item.style?.textInputAction,
                autofocus: item.style?.autofocus,
                obscureText: item.style?.obscureText,
                obscuringCharacter: item.style?.obscuringCharacter,
                maxLength: item.style?.maxLength,
              ),
            )
            .toList();

        final formButtonState = formData.items
            ?.where((element) => element.type == ItemType.button)
            .map(
              (item) => FormButtonStateDetails(
                itemId: item.id,
                buttonType: item.subType,
                validateOn: item.validateOn,
                text: item.text ?? '',
                buttonAction: item.actions,
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
                formTextFieldDetails: formTextFieldState,
                formButtonDetails: formButtonState,
                formLabelDetails: formLabelState,
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

    final updatedFormItemDetails = state.formTextFieldDetails?.map(
      (item) {
        if (item.itemId == event.itemId) {
          String? errorMessage;
          item.validateOn?.forEach((validator) {
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

    emit(
      state.copyWith(
        formTextFieldDetails: updatedFormItemDetails,
      ),
    );

    final validateTo = state.formTextFieldDetails
        ?.firstWhere((element) => element.itemId == event.itemId)
        .validateTo;

    add(EnableDisableComponentEvent(event.itemId, validateTo));
  }

  /// Whenever [ActionsFormEvent] is triggered this method will be called.
  void _onActionsFormEvent(
    ActionsFormEvent event,
    Emitter<FormState> emit,
  ) {
    switch (event.actions) {
      case UserActions.loginUser:
        break;
      case UserActions.unknown:
      default:
        break;
    }
  }

  /// Whenever [CheckButtonStateEvent] is called then this method will
  /// be triggered.
  void _onCheckButtonStateEvent(
    CheckButtonStateEvent event,
    Emitter<FormState> emit,
  ) {
    var itemDetails = state.formButtonDetails?.firstWhere(
      (element) => element.itemId == event.itemId,
    );

    if (itemDetails != null) {
      final validateOn = itemDetails.validateOn;
      if (validateOn != null) {
        var continueCheck = true;

        for (var validateOn in validateOn) {
          if (!continueCheck) break;

          final item = state.formData.items?.firstWhere(
            (item) => item.id == validateOn,
          );

          if (item != null) {
            switch (item.type) {
              case ItemType.textField:
                final itemStateDetails = state.formTextFieldDetails?.firstWhere(
                  (state) => state.itemId == item.id,
                );

                if (itemStateDetails != null) {
                  if (continueCheck) {
                    continueCheck = itemStateDetails
                            .textFieldValue.isNotEmpty &&
                        (itemStateDetails.currentErrorMessage?.isEmpty ?? true);
                  }
                }
                break;
              default:
                break;
            }
          }
        }

        itemDetails = itemDetails.copyWith(
          buttonState: continueCheck
              ? ButtonState.loading
              : ButtonState.disabled,
        );
      }

      emit(
        state.copyWith(
          formButtonDetails: state.formButtonDetails?.map(
            (element) {
              if (element.itemId == event.itemId) {
                return itemDetails!;
              }
              return element;
            },
          ).toList(),
        ),
      );
    }
  }

  /// Whenever [EnableDisableComponentEvent] is called this method will be
  /// triggered.
  void _onEnableDisableComponentEvent(
    EnableDisableComponentEvent event,
    Emitter<FormState> emit,
  ) {
    event.checkOn?.forEach((checkOn) {
      final item = state.formData.items?.firstWhere(
        (item) => item.id == checkOn,
      );

      if (item != null) {
        switch (item.type) {
          case ItemType.button:
            add(CheckButtonStateEvent(checkOn));
            break;
          default:
            break;
        }
      }
    });
  }
}
