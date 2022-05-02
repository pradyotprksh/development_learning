import 'package:flutter/material.dart';

/// A paddings class for getting different kinds of edge insets used across the
/// application.
///
/// Helpful for making the widget code cleaner.
class ThemesEdgeInsets {
  factory ThemesEdgeInsets() => _instance;

  ThemesEdgeInsets._privateConstructor();

  static final ThemesEdgeInsets _instance =
      ThemesEdgeInsets._privateConstructor();

  EdgeInsets top20Bottom20 = const EdgeInsets.only(
    top: 20,
    bottom: 20,
  );

  EdgeInsets left20Right20 = const EdgeInsets.only(
    left: 20,
    right: 20,
  );

  EdgeInsets top30Bottom30 = const EdgeInsets.only(
    top: 30,
    bottom: 30,
  );

  EdgeInsets all15 = const EdgeInsets.all(15);

  EdgeInsets all10 = const EdgeInsets.all(10);

  EdgeInsets left20 = const EdgeInsets.only(left: 20);

  EdgeInsets zero = const EdgeInsets.all(0);

  EdgeInsets left20Top40Right20Bottom40 = const EdgeInsets.fromLTRB(
    20,
    40,
    20,
    40,
  );
}
