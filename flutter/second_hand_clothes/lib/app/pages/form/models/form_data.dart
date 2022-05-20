// To parse this JSON data, do
//
//     final formData = formDataFromJson(jsonString);

import 'dart:convert';

import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';

FormData formDataFromJson(String str) => FormData.fromJson(
      json.decode(str) as Map<String, dynamic>,
    );

String formDataToJson(FormData data) => json.encode(data.toJson());

class FormData extends Equatable {
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
        formType: FormType.getFormType(json['type'] as String),
        formSubType: FormSubType.getFormSubType(json['subType'] as String),
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

  @override
  List<Object?> get props => [id, items];
}

enum FormType {
  authentication,
  unknown;

  static FormType getFormType(String input) =>
      <String, FormType>{
        'authentication': FormType.authentication,
      }[input] ??
      FormType.unknown;
}

enum FormSubType {
  login,
  signUp,
  unknown;

  static FormSubType getFormSubType(String input) =>
      <String, FormSubType>{
        'login': FormSubType.login,
        'sign_up': FormSubType.signUp,
      }[input] ??
      FormSubType.unknown;
}

final axisMap = <String, Axis>{
  'horizontal': Axis.horizontal,
  'vertical': Axis.vertical,
};

class FormItem extends Equatable {
  const FormItem({
    required this.id,
    required this.type,
    required this.text,
    required this.style,
    required this.gap,
    required this.inputType,
    required this.validateTo,
    required this.subType,
    required this.actions,
    required this.validateOn,
    required this.buttonState,
    required this.children,
    required this.navigationAction,
  });

  factory FormItem.fromJson(Map<String, dynamic> json) => FormItem(
      id: json['id'] as String,
      type: ItemType.getItemType(
        json['type'] as String? ?? '',
      ),
      actions: UserActions.getUserActions(
        json['actions'] as String? ?? '',
      ),
      subType: ItemSubType.getItemSubType(
        json['subType'] as String? ?? '',
      ),
      text: json['text'] as String?,
      style: Style.fromJson(
        json['style'] as Map<String, dynamic>? ?? <String, dynamic>{},
      ),
      gap: json['gap'] as int?,
      inputType: InputType.getInputType(
        json['inputType'] as String? ?? '',
      ),
      validateTo: List<String>.from(
        (json['validateTo'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
          (dynamic x) => x,
        ),
      ),
      validateOn: List<String>.from(
        (json['validateOn'] as List<dynamic>? ?? <dynamic>[]).map<dynamic>(
          (dynamic x) => x,
        ),
      ),
      buttonState: ButtonState.getButtonState(
        json['buttonState'] as String? ?? '',
      ),
      children: List<FormItem>.from(
        (json['children'] as List<dynamic>? ?? <FormItem>[]).map<dynamic>(
          (dynamic x) => FormItem.fromJson(
            x as Map<String, dynamic>,
          ),
        ),
      ),
      navigationAction: NavigationAction.fromJson(
        json['navigationAction'] as Map<String, dynamic>? ??
            <String, dynamic>{},
      ));

  final String id;
  final ItemType type;
  final ItemSubType? subType;
  final String? text;
  final Style? style;
  final int? gap;
  final InputType? inputType;
  final List<String>? validateTo;
  final List<String>? validateOn;
  final UserActions? actions;
  final ButtonState buttonState;
  final List<FormItem> children;
  final NavigationAction? navigationAction;

  FormItem copyWith({
    String? id,
    ItemType? type,
    String? text,
    Style? style,
    int? gap,
    InputType? inputType,
    List<String>? validateTo,
    List<String>? validateOn,
    ItemSubType? subType,
    UserActions? actions,
    ButtonState? buttonState,
    List<FormItem>? children,
    NavigationAction? navigationAction,
  }) =>
      FormItem(
        children: children ?? this.children,
        id: id ?? this.id,
        type: type ?? this.type,
        subType: subType ?? this.subType,
        text: text ?? this.text,
        style: style ?? this.style,
        gap: gap ?? this.gap,
        inputType: inputType ?? this.inputType,
        validateTo: validateTo ?? this.validateTo,
        validateOn: validateOn ?? this.validateOn,
        actions: actions ?? this.actions,
        buttonState: buttonState ?? this.buttonState,
        navigationAction: navigationAction ?? this.navigationAction,
      );

  Map<String, dynamic> toJson() => <String, dynamic>{
        'id': id,
        'type': type.name,
        'subType': subType?.name,
        'actions': actions?.name,
        'buttonState': buttonState.name,
        'text': text,
        'style': style?.toJson(),
        'gap': gap,
        'inputType': inputType?.name,
        'validateTo': validateTo == null
            ? null
            : List<dynamic>.from(
                validateTo!.map<dynamic>(
                  (x) => x,
                ),
              ),
        'validateOn': validateOn == null
            ? null
            : List<dynamic>.from(
                validateOn!.map<dynamic>(
                  (x) => x,
                ),
              ),
        'children': List<dynamic>.from(
          children.map<dynamic>(
            (x) => x.toJson(),
          ),
        ),
        'navigationAction': navigationAction?.toJson(),
      };

  @override
  List<Object?> get props => [id, type, subType];
}

enum ButtonState {
  enabled,
  disabled,
  loading,
  unknown;

  static ButtonState getButtonState(String input) =>
      <String, ButtonState>{
        'enabled': ButtonState.enabled,
        'disabled': ButtonState.disabled,
        'loading': ButtonState.loading,
      }[input] ??
      ButtonState.unknown;
}

enum ItemType {
  label,
  textField,
  button,
  box,
  divider,
  row,
  column,
  unknown;

  static ItemType getItemType(String input) =>
      <String, ItemType>{
        'label': ItemType.label,
        'textField': ItemType.textField,
        'button': ItemType.button,
        'divider': ItemType.divider,
        'box': ItemType.box,
        'row': ItemType.row,
        'column': ItemType.column,
      }[input] ??
      ItemType.unknown;
}

enum ItemSubType {
  elevatedButton,
  iconButton,
  outlinedButton,
  unknown;

  static ItemSubType getItemSubType(String input) =>
      <String, ItemSubType>{
        'elevatedButton': ItemSubType.elevatedButton,
        'iconButton': ItemSubType.iconButton,
        'outlinedButton': ItemSubType.outlinedButton,
      }[input] ??
      ItemSubType.unknown;
}

enum InputType {
  email,
  password,
  unknown;

  static InputType getInputType(String input) =>
      <String, InputType>{
        'email': InputType.email,
        'password': InputType.password,
      }[input] ??
      InputType.unknown;
}

enum UserActions {
  loginUser,
  googleSignIn,
  phoneLogin,
  signUpUserOption,
  loginUserOption,
  signUpUser,
  unknown;

  static UserActions getUserActions(String input) =>
      <String, UserActions>{
        'loginUser': UserActions.loginUser,
        'googleSignIn': UserActions.googleSignIn,
        'phoneLogin': UserActions.phoneLogin,
        'signUpUser': UserActions.signUpUser,
        'signUpUserOption': UserActions.signUpUserOption,
        'loginUserOption': UserActions.loginUserOption,
      }[input] ??
      UserActions.unknown;
}

class NavigationAction extends Equatable {
  const NavigationAction({
    required this.route,
    required this.formId,
    required this.goBack,
  });

  factory NavigationAction.fromJson(Map<String, dynamic> json) =>
      NavigationAction(
        route: json['route'] as String?,
        formId: json['formId'] as String?,
        goBack: json['goBack'] as bool? ?? false,
      );

  final String? route;
  final String? formId;
  final bool? goBack;

  NavigationAction copyWith({
    String? route,
    String? formId,
    bool? goBack,
  }) =>
      NavigationAction(
        route: route ?? this.route,
        formId: formId ?? this.formId,
        goBack: goBack ?? this.goBack,
      );

  Map<String, dynamic> toJson() => <String, dynamic>{
        'route': route,
        'formId': formId,
        'goBack': goBack,
      };

  @override
  List<Object?> get props => [
        route,
        formId,
      ];
}

class Style extends Equatable {
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
    required this.indent,
    required this.mainAxisAlignment,
  });

  factory Style.fromJson(Map<String, dynamic> json) => Style(
        style: ItemTextStyle.getItemTextStyle(
          json['textStyle'] as String? ?? '',
        ),
        textAlignment: textAlignmentMap[json['textAlignment'] as String? ?? ''],
        mainAxisAlignment:
            mainAxisAlignmentMap[json['mainAxisAlignment'] as String? ?? ''],
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
        indent: Indent.fromJson(
          json['indent'] as Map<String, dynamic>? ?? <String, dynamic>{},
        ),
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
  final Indent? indent;
  final MainAxisAlignment? mainAxisAlignment;

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
    Indent? indent,
    MainAxisAlignment? mainAxisAlignment,
  }) =>
      Style(
        mainAxisAlignment: mainAxisAlignment ?? this.mainAxisAlignment,
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
        indent: indent ?? this.indent,
      );

  Map<String, dynamic> toJson() => <String, dynamic>{
        'mainAxisAlignment': mainAxisAlignment?.name,
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
        'indent': indent?.toJson(),
      };

  @override
  List<Object?> get props => [];
}

enum ItemTextStyle {
  h2,
  labelLarge,
  caption,
  unknown;

  static ItemTextStyle getItemTextStyle(String input) =>
      <String, ItemTextStyle>{
        'h2': ItemTextStyle.h2,
        'labelLarge': ItemTextStyle.labelLarge,
        'caption': ItemTextStyle.caption,
      }[input] ??
      ItemTextStyle.unknown;
}

final keyboardTypeMap = <String, TextInputType>{
  'emailAddress': TextInputType.emailAddress,
  'text': TextInputType.text,
};

final textInputActionMap = <String, TextInputAction>{
  'next': TextInputAction.next,
  'done': TextInputAction.done,
};

final mainAxisAlignmentMap = <String, MainAxisAlignment>{
  'spaceEvenly': MainAxisAlignment.spaceEvenly,
  'center': MainAxisAlignment.center,
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
  'google': MdiIcons.google,
  'phone': Icons.phone,
};

class Indent {
  const Indent({
    this.start,
    this.end,
  });

  factory Indent.fromJson(Map<String, dynamic> json) => Indent(
        start: json['start'] as double?,
        end: json['end'] as double?,
      );

  final double? start;
  final double? end;

  Indent copyWith({
    double? start,
    double? end,
    bool? goBack,
  }) =>
      Indent(
        start: start ?? this.start,
        end: end ?? this.end,
      );

  Map<String, dynamic> toJson() => <String, dynamic>{
        'start': start,
        'end': end,
      };
}
