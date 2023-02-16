import 'package:flutter/material.dart';

abstract class ThemeEdgeInsets {
  static const top20Bottom20 = EdgeInsets.only(top: 20, bottom: 20);
  static const top15Bottom15 = EdgeInsets.only(top: 15, bottom: 15);
  static const left15Right15 = EdgeInsets.only(left: 15, right: 15);
  static const top15lest15Right15 = EdgeInsets.only(
    left: 15,
    right: 15,
    top: 15,
  );
  static const left16 = EdgeInsets.only(left: 16);
  static const leftRight15TopBottom5 = EdgeInsets.only(
    top: 5,
    bottom: 5,
    left: 15,
    right: 15,
  );
  static const left15 = EdgeInsets.only(left: 15);
  static const bottom20 = EdgeInsets.only(bottom: 20);
  static const all20 = EdgeInsets.all(20);
  static const all40 = EdgeInsets.all(40);
  static const all10 = EdgeInsets.all(10);
  static const zero = EdgeInsets.zero;
  static const all15 = EdgeInsets.all(15);
  static const topLeftRight15 = EdgeInsets.only(top: 15, left: 15, right: 15);
  static const leftTopRight15Bottom150 = EdgeInsets.fromLTRB(
    15,
    15,
    15,
    150,
  );
}
