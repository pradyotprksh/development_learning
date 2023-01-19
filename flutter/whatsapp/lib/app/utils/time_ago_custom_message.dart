import 'package:flutter/material.dart';
import 'package:timeago/timeago.dart';
import 'package:whatsapp/app/app.dart';

class TimeAgoCustomMessage extends LookupMessages {
  TimeAgoCustomMessage(this._context);

  final BuildContext _context;

  @override
  String aDay(int hours) => '$hours ${_context.translator.day}';

  @override
  String aboutAMinute(int minutes) => '$minutes ${_context.translator.minutes}';

  @override
  String aboutAMonth(int days) => '$days ${_context.translator.days}';

  @override
  String aboutAYear(int year) => '$year ${_context.translator.year}';

  @override
  String aboutAnHour(int minutes) => '$minutes ${_context.translator.minutes}';

  @override
  String days(int days) => '$days ${_context.translator.days}';

  @override
  String hours(int hours) => '$hours ${_context.translator.hours}';

  @override
  String lessThanOneMinute(int seconds) =>
      '$seconds ${_context.translator.now}';

  @override
  String minutes(int minutes) => '$minutes ${_context.translator.minutes}';

  @override
  String months(int months) => '$months ${_context.translator.months}';

  @override
  String prefixAgo() => '';

  @override
  String prefixFromNow() => '';

  @override
  String suffixAgo() => _context.translator.ago;

  @override
  String suffixFromNow() => '';

  @override
  String years(int years) => '$years ${_context.translator.years}';
}
