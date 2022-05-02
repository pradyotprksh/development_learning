// To parse this JSON data, do
//
//     final formData = formDataFromJson(jsonString);

import 'dart:convert';

import 'package:flutter/material.dart';

FormData formDataFromJson(String str) => FormData.fromJson(
      json.decode(str) as Map<String, dynamic>,
    );

String formDataToJson(FormData data) => json.encode(data.toJson());

class FormData {
  const FormData({
    required this.id,
    required this.formType,
    required this.formSubType,
    required this.extendBody,
    required this.extendBodyBehindAppBar,
    required this.padding,
    required this.items,
    required this.orientation,
  });

  const FormData.noData()
      : this(
          id: 'no_form',
          formType: FormType.unknown,
          formSubType: FormSubType.unknown,
          extendBody: null,
          extendBodyBehindAppBar: null,
          padding: null,
          items: null,
          orientation: null,
        );

  factory FormData.fromJson(Map<String, dynamic> json) => FormData(
        id: json['id'] as String,
        formType: formTypeMap[json['type'] as String] ?? FormType.unknown,
        formSubType:
            formSubTypeMap[json['subType'] as String] ?? FormSubType.unknown,
        orientation:
            axisMap[json['orientation'] as String? ?? ''] ?? Axis.vertical,
        extendBody: json['extendBody'] as bool?,
        extendBodyBehindAppBar: json['extendBodyBehindAppBar'] as bool?,
        padding: json['padding'] as String?,
        items: List<FormItem>.from(
          (json['items'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => FormItem.fromJson(
              x as Map<String, dynamic>,
            ),
          ),
        ),
      );

  final String id;
  final FormType formType;
  final FormSubType? formSubType;
  final bool? extendBody;
  final bool? extendBodyBehindAppBar;
  final String? padding;
  final List<FormItem>? items;
  final Axis? orientation;

  FormData copyWith({
    String? id,
    FormType? formType,
    FormSubType? formSubType,
    bool? extendBody,
    bool? extendBodyBehindAppBar,
    String? padding,
    List<FormItem>? items,
    Axis? orientation,
  }) =>
      FormData(
        id: id ?? this.id,
        formType: formType ?? this.formType,
        formSubType: formSubType ?? this.formSubType,
        extendBody: extendBody ?? this.extendBody,
        extendBodyBehindAppBar:
            extendBodyBehindAppBar ?? this.extendBodyBehindAppBar,
        padding: padding ?? this.padding,
        items: items ?? this.items,
        orientation: orientation ?? this.orientation,
      );

  Map<String, dynamic> toJson() => <String, dynamic>{
        'id': id,
        'type': formType.name,
        'subType': formSubType?.name,
        'extendBody': extendBody,
        'extendBodyBehindAppBar': extendBodyBehindAppBar,
        'padding': padding,
        'orientation': orientation?.name,
        'items': items == null
            ? null
            : List<dynamic>.from(
                items!.map<dynamic>(
                  (x) => x.toJson(),
                ),
              ),
      };
}

enum FormType {
  authentication,
  unknown,
}

final formTypeMap = <String, FormType>{
  'authentication': FormType.authentication,
};

enum FormSubType {
  login,
  unknown,
}

final formSubTypeMap = <String, FormSubType>{
  'login': FormSubType.login,
};

final axisMap = <String, Axis>{
  'horizontal': Axis.horizontal,
  'vertical': Axis.vertical,
};

class FormItem {
  const FormItem({
    required this.id,
    required this.type,
    required this.text,
    required this.style,
    required this.gap,
    required this.inputType,
    required this.validate,
    required this.subType,
  });

  factory FormItem.fromJson(Map<String, dynamic> json) => FormItem(
        id: json['id'] as String,
        type: itemTypeMap[json['type'] as String] ?? ItemType.unknown,
        subType: itemSubTypeMap[json['subType'] as String? ?? ''] ?? ItemSubType.unknown,
        text: json['text'] as String?,
        style: Style.fromJson(
          json['style'] as Map<String, dynamic>? ?? <String, dynamic>{},
        ),
        gap: json['gap'] as int?,
        inputType: inputTypeMap[json['inputType'] as String? ?? ''] ??
            InputType.unknown,
        validate: List<String>.from(
          (json['validate'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
            (dynamic x) => x,
          ),
        ),
      );

  final String id;
  final ItemType type;
  final ItemSubType? subType;
  final String? text;
  final Style? style;
  final int? gap;
  final InputType? inputType;
  final List<String>? validate;

  FormItem copyWith({
    String? id,
    ItemType? type,
    String? text,
    Style? style,
    int? gap,
    InputType? inputType,
    List<String>? validate,
    ItemSubType? subType,
  }) =>
      FormItem(
        id: id ?? this.id,
        type: type ?? this.type,
        subType: subType ?? this.subType,
        text: text ?? this.text,
        style: style ?? this.style,
        gap: gap ?? this.gap,
        inputType: inputType ?? this.inputType,
        validate: validate ?? this.validate,
      );

  Map<String, dynamic> toJson() => <String, dynamic>{
        'id': id,
        'type': type.name,
        'subType': subType?.name,
        'text': text,
        'style': style == null ? null : style!.toJson(),
        'gap': gap,
        'inputType': inputType?.name,
        'validate': validate == null
            ? null
            : List<dynamic>.from(
                validate!.map<dynamic>(
                  (x) => x,
                ),
              ),
      };
}

enum ItemType {
  label,
  textField,
  button,
  box,
  unknown,
}

final itemTypeMap = <String, ItemType>{
  'label': ItemType.label,
  'textField': ItemType.textField,
  'button': ItemType.button,
  'box': ItemType.box,
};

enum ItemSubType {
  elevatedButton,
  unknown,
}

final itemSubTypeMap = <String, ItemSubType>{
  'elevatedButton': ItemSubType.elevatedButton,
};

enum InputType {
  email,
  password,
  unknown,
}

final inputTypeMap = <String, InputType>{
  'email': InputType.email,
  'password': InputType.password,
};

class Style {
  const Style({
    required this.style,
    required this.textAlignment,
    required this.alignment,
    required this.icon,
    required this.label,
    required this.hint,
    required this.error,
    required this.keyboardType,
    required this.textInputAction,
    required this.autofocus,
    required this.obscureText,
    required this.obscuringCharacter,
    required this.maxLength,
    required this.height,
    required this.width,
  });

  factory Style.fromJson(Map<String, dynamic> json) => Style(
        style: textStyleMap[json['textStyle'] as String? ?? ''] ??
            ItemTextStyle.unknown,
        textAlignment: textAlignmentMap[json['textAlignment'] as String? ?? ''],
        alignment: alignmentMap[json['alignment'] as String? ?? ''],
        icon: iconMap[json['icon'] as String? ?? ''],
        label: json['label'] as String?,
        hint: json['hint'] as String?,
        error: json['error'] as String?,
        keyboardType: keyboardTypeMap[json['keyboardType'] as String? ?? ''],
        textInputAction:
            textInputActionMap[json['textInputAction'] as String? ?? ''],
        autofocus: json['autofocus'] as bool?,
        obscureText: json['obscureText'] as bool?,
        obscuringCharacter: json['obscuringCharacter'] as String?,
        maxLength: json['maxLength'] as int?,
        height: json['height'] as double?,
        width: json['width'] as double?,
      );

  final ItemTextStyle? style;
  final TextAlign? textAlignment;
  final Alignment? alignment;
  final IconData? icon;
  final String? label;
  final String? hint;
  final String? error;
  final TextInputType? keyboardType;
  final TextInputAction? textInputAction;
  final bool? autofocus;
  final bool? obscureText;
  final String? obscuringCharacter;
  final int? maxLength;
  final double? height;
  final double? width;

  Style copyWith({
    ItemTextStyle? style,
    TextAlign? textAlignment,
    Alignment? alignment,
    IconData? icon,
    String? label,
    String? hint,
    String? error,
    TextInputType? keyboardType,
    TextInputAction? textInputAction,
    bool? autofocus,
    bool? obscureText,
    String? obscuringCharacter,
    int? maxLength,
    double? height,
    double? width,
  }) =>
      Style(
        style: style ?? this.style,
        textAlignment: textAlignment ?? this.textAlignment,
        alignment: alignment ?? this.alignment,
        icon: icon ?? this.icon,
        label: label ?? this.label,
        hint: hint ?? this.hint,
        error: error ?? this.error,
        keyboardType: keyboardType ?? this.keyboardType,
        textInputAction: textInputAction ?? this.textInputAction,
        autofocus: autofocus ?? this.autofocus,
        obscureText: obscureText ?? this.obscureText,
        obscuringCharacter: obscuringCharacter ?? this.obscuringCharacter,
        maxLength: maxLength ?? this.maxLength,
        height: height ?? this.height,
        width: width ?? this.width,
      );

  Map<String, dynamic> toJson() => <String, dynamic>{
        'textStyle': style?.name,
        'textAlignment': textAlignment?.name,
        'alignment': alignment.toString(),
        'icon': icon,
        'label': label,
        'hint': hint,
        'error': error,
        'keyboardType': keyboardType.toString(),
        'textInputAction': textInputAction,
        'autofocus': autofocus,
        'obscureText': obscureText,
        'obscuringCharacter': obscuringCharacter,
        'maxLength': maxLength,
        'height': height,
        'width': width,
      };
}

enum ItemTextStyle {
  h2,
  labelLarge,
  unknown,
}

final textStyleMap = <String, ItemTextStyle>{
  'h2': ItemTextStyle.h2,
  'labelLarge': ItemTextStyle.labelLarge,
};

final keyboardTypeMap = <String, TextInputType>{
  'emailAddress': TextInputType.emailAddress,
  'text': TextInputType.text,
};

final textInputActionMap = <String, TextInputAction>{
  'next': TextInputAction.next,
  'done': TextInputAction.done,
};

final textAlignmentMap = <String, TextAlign>{
  'center': TextAlign.center,
};

final alignmentMap = <String, Alignment>{
  'center': Alignment.center,
};

final iconMap = <String, IconData>{
  'alternate_email': Icons.alternate_email,
  'password': Icons.password,
};
