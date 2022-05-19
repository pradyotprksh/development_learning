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

        // Get all the item id which has children
        var itemIdWithChildren = <String>[];
        formData.items?.forEach((element) {
          if (element.children.isNotEmpty) {
            itemIdWithChildren.add(element.id);
          }
        });

        final formLabelState = formData.items
            ?.where((element) => element.type == ItemType.label)
            .map(_getLabelStateDetails)
            .toList();

        final formTextFieldState = formData.items
            ?.where((element) => element.type == ItemType.textField)
            .map(_getTextFieldStateDetails)
            .toList();

        final formButtonState = formData.items
            ?.where((element) => element.type == ItemType.button)
            .map(_getButtonStateDetails)
            .toList();

        final formRowState = formData.items
            ?.where((element) => element.type == ItemType.row)
            .map(_getRowStateDetails)
            .toList();

        final formColumnState = formData.items
            ?.where((element) => element.type == ItemType.column)
            .map(_getColumnStateDetails)
            .toList();

        // Create the state details for all the children, a very long process
        // to check it up on because there might be very long
        // nested widget tree.
        while (itemIdWithChildren.isNotEmpty) {
          final itemId = itemIdWithChildren.removeLast();
          final itemDetails = formData.items?.firstWhere(
            (element) => element.id == itemId,
          );

          if (itemDetails != null) {
            for (var element in itemDetails.children) {
              if (element.children.isNotEmpty) {
                itemIdWithChildren.add(element.id);
              }
              switch (element.type) {
                case ItemType.label:
                  formLabelState?.add(_getLabelStateDetails(element));
                  break;
                case ItemType.textField:
                  formTextFieldState?.add(_getTextFieldStateDetails(element));
                  break;
                case ItemType.button:
                  formButtonState?.add(_getButtonStateDetails(element));
                  break;
                case ItemType.row:
                  formRowState?.add(_getRowStateDetails(element));
                  break;
                case ItemType.column:
                  formColumnState?.add(_getColumnStateDetails(element));
                  break;
                default:
                  break;
              }
            }
          }
        }

        await Future.delayed(
          FormConstants().formStatusChangeDuration,
          () {
            emit(
              state.copyWith(
                formData: formData,
                formStatus: FormzStatus.submissionSuccess,
                formTextFieldDetails: formTextFieldState,
                formButtonDetails: formButtonState,
                formLabelDetails: formLabelState,
                formRowDetails: formRowState,
                formColumnDetails: formColumnState,
              ),
            );
          },
        );
      } catch (_) {
        await Future.delayed(
          FormConstants().formStatusChangeDuration,
          () {
            emit(
              state.copyWith(
                formStatus: FormzStatus.invalid,
                errorMessage: LocalizationValues().formParsingErrorKey,
              ),
            );
          },
        );
      }
    } else {
      await Future.delayed(
        FormConstants().formStatusChangeDuration,
        () {
          emit(
            state.copyWith(
              formStatus: FormzStatus.invalid,
              errorMessage: LocalizationValues().wrongFormIdKey,
            ),
          );
        },
      );
    }
  }

  /// Get button state details for [item].
  FormButtonStateDetails _getButtonStateDetails(FormItem item) =>
      FormButtonStateDetails(
        itemId: item.id,
        buttonType: item.subType,
        validateOn: item.validateOn,
        text: item.text ?? '',
        buttonAction: item.actions,
        icon: item.style?.icon,
        buttonState: item.buttonState,
      );

  /// Get label state details for [item].
  FormLabelStateDetails _getLabelStateDetails(FormItem item) =>
      FormLabelStateDetails(
        text: item.text ?? '',
        itemId: item.id,
        textAlign: item.style?.textAlignment,
        textStyle: item.style?.style,
      );

  /// Get row state details for [item].
  FormRowStateDetails _getRowStateDetails(FormItem item) => FormRowStateDetails(
        itemId: item.id,
        itemType: ItemType.row,
        children: item.children,
        mainAxisAlignment: item.style?.mainAxisAlignment,
      );

  /// Get row state details for [item].
  FormColumnStateDetails _getColumnStateDetails(FormItem item) =>
      FormColumnStateDetails(
        itemId: item.id,
        itemType: ItemType.row,
        children: item.children,
      );

  /// Get text field state details for [item].
  FormTextFieldStateDetails _getTextFieldStateDetails(FormItem item) =>
      FormTextFieldStateDetails(
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
      );

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
          buttonState:
              continueCheck ? ButtonState.loading : ButtonState.disabled,
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
