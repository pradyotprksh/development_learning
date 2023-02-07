import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:timeago/timeago.dart' as timeago;
import 'package:whatsapp/app/app.dart';

abstract class AppUtilsMethods {
  static String invitationMessage(String appName) =>
      'Let\'s chat on $appName! It\'s a fast, simple, and secure app we can use to message and call each other for free. Get it at ';

  static String emailUrl(String appName, String emailAddress) =>
      'mailto:$emailAddress?subject=Join $appName!&body=${invitationMessage(appName)}';

  static String smsUrl(String phoneNumber, String body) =>
      'sms:$phoneNumber?body=$body';

  static String getUserQrCode(String userId) => 'user/$userId';

  static String timeAgo(int? timeStamp, BuildContext context) {
    if (timeStamp != null) {
      setTimeAgoLocalMessage(
        context.read<LocalizationsBloc>().state.currentLocale.languageCode,
        TimeAgoCustomMessage(
          context,
        ),
      );
      return timeago.format(
        DateTime.fromMicrosecondsSinceEpoch(timeStamp),
      );
    }
    return '';
  }

  static void setTimeAgoLocalMessage(
    String locale,
    timeago.LookupMessages messages,
  ) {
    timeago.setLocaleMessages(
      locale,
      messages,
    );
  }

  static String? lengthShouldBeGraterThan(
    String? userName,
    int length,
    String errorMessage,
  ) {
    if (userName != null) {
      if (userName.length < length) {
        return errorMessage;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }
}
