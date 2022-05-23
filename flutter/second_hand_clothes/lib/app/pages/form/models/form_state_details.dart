import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart' as material;
import 'package:second_hand_clothes/app/app.dart';

/// A form state details for label type
class FormLabelStateDetails extends Equatable {
  /// [text] = text to be shown on the widget
  ///
  /// [textAlign] = alignment of the text in the ui
  ///
  /// [textStyle] = font style of the text widget
  const FormLabelStateDetails({
    required this.text,
    required this.itemId,
    this.textAlign,
    this.textStyle,
    this.itemType = ItemType.label,
  });

  final String text;
  final material.TextAlign? textAlign;
  final ItemTextStyle? textStyle;
  final String itemId;
  final ItemType itemType;

  @override
  List<Object?> get props => [text, textAlign, textStyle, itemId, itemType];
}

/// A form state details for a text field form item.
class FormTextFieldStateDetails extends Equatable {
  /// [errorMessage] = Error message for that particular item
  ///
  /// [textFieldValue] = Value of the item which is a text field
  const FormTextFieldStateDetails({
    this.keyboardType,
    this.textInputAction,
    this.autofocus,
    this.obscureText,
    this.obscuringCharacter,
    this.maxLength,
    required this.itemId,
    required this.textFieldValue,
    this.currentErrorMessage,
    this.errorMessage,
    this.icon,
    this.label,
    this.hint,
    this.validateTo,
    this.validateOn,
    this.itemType = ItemType.textField,
  });

  final String itemId;
  final List<String>? validateTo;
  final List<String>? validateOn;
  final ItemType itemType;
  final String? currentErrorMessage;
  final String? errorMessage;
  final String textFieldValue;
  final material.IconData? icon;
  final String? label;
  final String? hint;
  final material.TextInputType? keyboardType;
  final material.TextInputAction? textInputAction;
  final bool? autofocus;
  final bool? obscureText;
  final String? obscuringCharacter;
  final int? maxLength;

  FormTextFieldStateDetails copyWith({
    String? currentErrorMessage,
    String? errorMessage,
    String? textFieldValue,
    List<String>? validateTo,
    List<String>? validateOn,
    material.IconData? icon,
    String? label,
    String? hint,
    material.TextInputType? keyboardType,
    material.TextInputAction? textInputAction,
    bool? autofocus,
    bool? obscureText,
    String? obscuringCharacter,
    int? maxLength,
  }) =>
      FormTextFieldStateDetails(
        itemId: itemId,
        itemType: itemType,
        currentErrorMessage: currentErrorMessage ?? this.currentErrorMessage,
        errorMessage: errorMessage ?? this.errorMessage,
        textFieldValue: textFieldValue ?? this.textFieldValue,
        validateTo: validateTo ?? this.validateTo,
        validateOn: validateOn ?? this.validateOn,
        icon: icon ?? this.icon,
        label: label ?? this.label,
        hint: hint ?? this.hint,
        keyboardType: keyboardType ?? this.keyboardType,
        textInputAction: textInputAction ?? this.textInputAction,
        autofocus: autofocus ?? this.autofocus,
        obscureText: obscureText ?? this.obscureText,
        obscuringCharacter: obscuringCharacter ?? this.obscuringCharacter,
        maxLength: maxLength ?? this.maxLength,
      );

  @override
  List<Object?> get props => [
        currentErrorMessage,
        errorMessage,
        textFieldValue,
        icon,
        label,
        hint,
        keyboardType,
        textInputAction,
        autofocus,
        obscureText,
        obscuringCharacter,
        maxLength,
        itemType,
        itemId,
        validateTo,
        validateOn,
      ];
}

/// A form state details for a text field form item.
class FormButtonStateDetails extends Equatable {
  /// [buttonType] = Subtype of the button
  ///
  /// [buttonState] = Button current state
  const FormButtonStateDetails({
    required this.text,
    required this.itemId,
    this.validateOn,
    this.buttonState = ButtonState.disabled,
    required this.buttonType,
    this.buttonAction,
    this.itemType = ItemType.button,
    this.icon,
  });

  FormButtonStateDetails copyWith({
    String? text,
    List<String>? validateOn,
    ItemSubType? buttonType,
    ButtonState? buttonState,
    Actions? buttonAction,
    material.IconData? icon,
  }) =>
      FormButtonStateDetails(
        itemId: itemId,
        itemType: itemType,
        text: text ?? this.text,
        validateOn: validateOn ?? this.validateOn,
        buttonType: buttonType ?? this.buttonType,
        buttonState: buttonState ?? this.buttonState,
        buttonAction: buttonAction ?? this.buttonAction,
        icon: icon ?? this.icon,
      );

  final String itemId;
  final String text;
  final List<String>? validateOn;
  final ItemType itemType;
  final ItemSubType? buttonType;
  final ButtonState buttonState;
  final Actions? buttonAction;
  final material.IconData? icon;

  @override
  List<Object?> get props => [
        text,
        buttonState,
        buttonType,
        itemId,
        validateOn,
        itemType,
        buttonAction,
        icon,
      ];
}

/// A form state details for a row form item.
class FormRowStateDetails extends Equatable {
  const FormRowStateDetails({
    required this.itemId,
    required this.itemType,
    this.mainAxisAlignment,
    required this.children,
  });

  final String itemId;
  final ItemType itemType;
  final material.MainAxisAlignment? mainAxisAlignment;
  final List<FormItem> children;

  @override
  List<Object?> get props => [itemId, itemType, mainAxisAlignment, children];
}

/// A form state details for a column form item.
class FormColumnStateDetails extends Equatable {
  const FormColumnStateDetails({
    required this.itemId,
    required this.itemType,
    required this.children,
  });

  final String itemId;
  final ItemType itemType;
  final List<FormItem> children;

  @override
  List<Object?> get props => [itemId, itemType, children];
}
