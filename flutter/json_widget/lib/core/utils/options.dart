import 'package:flutter/cupertino.dart';

/// An options class which will take all the other options which are needed
/// by the package to help the application perform certain things from or
/// on the package.
///
/// This is optional, without this the UI will still render.
class Options {
  /// [onTextChanged] - Whenever a widget whose text get changed, then this
  /// method will be called
  ///
  /// [onButtonTap] - Whenever a widget on which tap is performed, then this
  /// method will be called
  ///
  /// [onAnyErrorWidget] - When an error occurs while parsing the JSON then
  /// this widget will be shown if present.
  ///
  /// [showLogs] - show logs for the library, can be disabled.
  Options({
    required this.onTextChanged,
    required this.onButtonTap,
    this.onAnyErrorWidget,
    this.showLogs = true,
  });

  final Function(String, String)? onTextChanged;
  final Function(String)? onButtonTap;
  final Widget? onAnyErrorWidget;
  final bool showLogs;
}