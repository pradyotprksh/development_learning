import 'package:equatable/equatable.dart';

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
