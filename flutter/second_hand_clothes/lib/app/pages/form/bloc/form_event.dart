import 'package:equatable/equatable.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A form event class which will listen to any form events made by the form
/// screen.
abstract class FormEvent extends Equatable {
  const FormEvent();

  @override
  List<Object?> get props => [];
}

/// A child of [FormEvent] which will be called to get the details of the
/// form based on the [formId].
class GetDetailsFormEvent extends FormEvent {
  /// [formId] = A form id for which the details are required.
  const GetDetailsFormEvent(
    this.formId,
  );

  final String formId;

  @override
  List<Object?> get props => [formId];
}

/// A child of [FormEvent] which will be called whenever there is a change
/// in the text widget of the form.
class TextFieldChangeFormEvent extends FormEvent {
  /// [itemId] = Id of the item for which the change started
  ///
  /// [value] = Value which is typed.
  const TextFieldChangeFormEvent(
    this.itemId,
    this.value,
  );

  final String itemId;
  final String value;

  @override
  List<Object?> get props => [itemId, value];
}

/// A child of [FormEvent] which will be called whenever an action is
/// performed.
class ActionsFormEvent extends FormEvent {
  /// [itemId] = Id of the widget which is being clicked
  ///
  /// [actions] = Action type which needs to be performed
  const ActionsFormEvent(
    this.itemId,
    this.actions,
  );

  final String itemId;
  final Actions? actions;

  @override
  List<Object?> get props => [itemId, actions];
}

/// A child for [FormEvent] which will be triggered to check for enabling and
/// disabling certain UI components.
class EnableDisableComponentEvent extends FormEvent {
  const EnableDisableComponentEvent(
    this.eventFrom,
    this.checkOn,
  );

  final String eventFrom;
  final List<String>? checkOn;

  @override
  List<Object?> get props => [checkOn, eventFrom];
}

/// A child of [FormEvent] which will be triggered when a check on the button
/// state has to be made.
class CheckButtonStateEvent extends FormEvent {
  const CheckButtonStateEvent(
    this.itemId,
  );

  final String itemId;

  @override
  List<Object?> get props => [itemId];
}
