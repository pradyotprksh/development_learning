import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';
import 'package:second_hand_clothes/second_hand_clothes.dart';

/// A bloc class for the form screen which will listen to any changes raised
/// by form event and update the state by updating the value of form state.
class FormBloc extends Bloc<FormEvent, FormState> {
  FormBloc(this._authService, this._firebaseDB) : super(const FormState()) {
    on<GetDetailsFormEvent>(_getFormDetails);
    on<TextFieldChangeFormEvent>(_onTextFieldChangeEvent);
    on<ActionsFormEvent>(_onActionsFormEvent);
    on<EnableDisableComponentEvent>(_onEnableDisableComponentEvent);
    on<CheckButtonStateEvent>(_onCheckButtonStateEvent);
  }

  final ServicesFirebaseAuth _authService;
  final ServicesFirebaseDB _firebaseDB;

  /// Whenever [GetDetailsFormEvent] is raised then this method will be called.
  void _getFormDetails(
    GetDetailsFormEvent event,
    Emitter<FormState> emit,
  ) async {
    emit(
      state.copyWith(
        formStatus: FormzStatus.submissionInProgress,
        loadingMessage: LocalizationValues().fetchingFormDetailsKey,
        formData: null,
      ),
    );

    final formId = event.formId;
    final formData = await _firebaseDB.getFormDetails(formId);

    try {
      final flatFormItems = _flatten(formData.items ?? []);

      final formLabelState = flatFormItems
          .where((element) => element.type == ItemType.label)
          .map(_getLabelStateDetails)
          .toList();

      final formTextFieldState = flatFormItems
          .where((element) => element.type == ItemType.textField)
          .map(_getTextFieldStateDetails)
          .toList();

      final formButtonState = flatFormItems
          .where((element) => element.type == ItemType.button)
          .map(_getButtonStateDetails)
          .toList();

      final formRowState = flatFormItems
          .where((element) => element.type == ItemType.row)
          .map(_getRowStateDetails)
          .toList();

      final formColumnState = flatFormItems
          .where((element) => element.type == ItemType.column)
          .map(_getColumnStateDetails)
          .toList();

      await Future.delayed(
        Constants().formStatusChangeDuration,
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
              navigationAction: null,
            ),
          );
        },
      );
    } catch (e) {
      UtilsLogger().log(e);
      await Future.delayed(
        Constants().formStatusChangeDuration,
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
        itemType: ItemType.button,
      );

  /// Get label state details for [item].
  FormLabelStateDetails _getLabelStateDetails(FormItem item) =>
      FormLabelStateDetails(
        text: item.text ?? '',
        itemId: item.id,
        textAlign: item.style?.textAlignment,
        textStyle: item.style?.style,
        itemType: ItemType.label,
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
        itemType: ItemType.column,
        children: item.children,
      );

  /// Get text field state details for [item].
  FormTextFieldStateDetails _getTextFieldStateDetails(FormItem item) =>
      FormTextFieldStateDetails(
        textFieldValue: '',
        itemType: ItemType.textField,
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
    final updatedFormItemDetails = state.formTextFieldDetails?.map(
      (item) {
        if (item.itemId == event.itemId) {
          String? errorMessage;
          var formInputs = <FormzInput>[];

          item.validateOn?.forEach(
            (validator) {
              if (validator == 'EmailAddressModels') {
                formInputs.add(EmailAddressModels.dirty(event.value));
              } else if (validator == 'PasswordModels') {
                formInputs.add(PasswordModels.dirty(event.value));
              }
            },
          );

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
  ) async {
    try {
      final actionCreatorItem = _getItemDetailsBasedOnId(
        state.formData.items ?? [],
        event.itemId,
      );

      if (actionCreatorItem != null) {
        final actionDetails = event.actions;
        if (actionDetails != null) {
          switch (actionDetails.name) {
            case UserActions.signUpUser:
            case UserActions.loginUser:
              emit(
                state.copyWith(
                  formStatus: FormzStatus.submissionInProgress,
                  loadingMessage: LocalizationValues().submittingFormDetailsKey,
                ),
              );
              final parameters = actionDetails.parametersFrom;
              if (parameters != null) {
                final emailId = state.formTextFieldDetails
                    ?.firstWhere(
                      (element) => element.itemId == parameters[0],
                    )
                    .textFieldValue;
                final password = state.formTextFieldDetails
                    ?.firstWhere(
                      (element) => element.itemId == parameters[1],
                    )
                    .textFieldValue;

                if (emailId != null &&
                    emailId.isNotEmpty &&
                    password != null &&
                    password.isNotEmpty) {
                  if (actionDetails.name == UserActions.loginUser) {
                    await _authService.authenticateUser(
                      email: emailId,
                      password: password,
                      authType: AuthType.login,
                    );
                  } else {
                    // TODO: On success there is an error with redirection. Need to check.
                    await _authService.authenticateUser(
                      email: emailId,
                      password: password,
                      authType: AuthType.register,
                    );
                  }

                  if (actionDetails.onActionComplete != null) {
                    if (actionDetails.onActionComplete?.isNavigation == true) {
                      emit(
                        state.copyWith(
                          formStatus: FormzStatus.submissionSuccess,
                          navigationAction:
                              actionDetails.onActionComplete?.navigationAction,
                        ),
                      );
                    }
                  } else {
                    _submissionFailureStateUpdate(emit);
                  }
                } else {
                  _submissionFailureStateUpdate(emit);
                }
              } else {
                _submissionFailureStateUpdate(emit);
              }
              break;
            case UserActions.googleSignIn:
              break;
            case UserActions.phoneLogin:
              break;
            case UserActions.loginUserOption:
            case UserActions.signUpUserOption:
              final itemDetails = _getItemDetailsBasedOnId(
                state.formData.items ?? [],
                event.itemId,
              );

              emit(
                state.copyWith(
                  navigationAction: itemDetails?.navigationAction,
                ),
              );
              break;
            case UserActions.unknown:
            default:
              break;
          }
        } else {
          _submissionFailureStateUpdate(emit);
        }
      } else {
        _submissionFailureStateUpdate(emit);
      }
    } catch (e) {
      _submissionFailureStateUpdate(emit, e.toString());
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

          final item = _getItemDetailsBasedOnId(
            state.formData.items ?? [],
            validateOn,
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
              continueCheck ? ButtonState.enabled : ButtonState.disabled,
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
    event.checkOn?.forEach(
      (checkOn) {
        final item = _getItemDetailsBasedOnId(
          state.formData.items ?? [],
          checkOn,
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
      },
    );
  }

  /// Get the details of the item based on the [itemId] for the list of
  /// [formData].
  FormItem? _getItemDetailsBasedOnId(List<FormItem> formData, String itemId) {
    var item = _flatten(formData).firstWhere(
      (item) => item.id == itemId,
    );

    return item;
  }

  /// Fold all the form item, i.e. nested children as well, into a single
  /// dimensional list for better search and iteration.
  List<FormItem> _flatten(List<FormItem> arr) => arr.fold(
        [],
        (value, element) => [
          ...value,
          ...[element],
          ...element.children.isNotEmpty ? _flatten(element.children) : [],
        ],
      );

  /// An emitter for failure state for the form, usually called when there is
  /// a processing issue.
  void _submissionFailureStateUpdate(
    Emitter<FormState> emit, [
    String? errorMessage,
  ]) {
    emit(
      state.copyWith(
        formStatus: FormzStatus.submissionFailure,
        errorMessage:
            errorMessage ?? LocalizationValues().formProcessErrorMessageKey,
      ),
    );
  }
}
