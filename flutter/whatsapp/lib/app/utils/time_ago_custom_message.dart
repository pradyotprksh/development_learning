import 'package:flutter/material.dart';
import 'package:timeago/timeago.dart';
import 'package:whatsapp/app/app.dart';

class TimeAgoCustomMessage extends LookupMessages {
  TimeAgoCustomMessage(this._context);

  final BuildContext _context;

  @override
  String aDay(int hours) =>
      '$hours ${_context.translator.day} ${_context.translator.ago}';

  @override
  String aboutAMinute(int minutes) =>
      '$minutes ${_context.translator.minutes} ${_context.translator.ago}';

  @override
  String aboutAMonth(int days) =>
      '$days ${_context.translator.days} ${_context.translator.ago}';

  @override
  String aboutAYear(int year) =>
      '$year ${_context.translator.year} ${_context.translator.ago}';

  @override
  String aboutAnHour(int minutes) =>
      '$minutes ${_context.translator.minutes} ${_context.translator.ago}';

  @override
  String days(int days) =>
      '$days ${_context.translator.days} ${_context.translator.ago}';

  @override
  String hours(int hours) =>
      '$hours ${_context.translator.hours} ${_context.translator.ago}';

  @override
  String lessThanOneMinute(int seconds) =>
      '$seconds ${_context.translator.now}';

  @override
  String minutes(int minutes) =>
      '$minutes ${_context.translator.minutes} ${_context.translator.ago}';

  @override
  String months(int months) =>
      '$months ${_context.translator.months} ${_context.translator.ago}';

  @override
  String prefixAgo() => '';

  @override
  String prefixFromNow() => '';

  @override
  String suffixAgo() => '';

  @override
  String suffixFromNow() => '';

  @override
  String years(int years) => '$years ${_context.translator.years}';
}
