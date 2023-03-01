import 'package:path_provider/path_provider.dart';

abstract class DeviceUtilsMethods {
  static int getCurrentTimeStamp() => DateTime.now().microsecondsSinceEpoch;

  static String getCurrentDateWithCurrentHour() =>
      '${DateTime.now().day}-${DateTime.now().month}-${DateTime.now().year}||${DateTime.now().hour}hr';

  static Future<String> getTempDirectoryPath() async {
    final tempDirectory = await getTemporaryDirectory();
    return '$tempDirectory-${DeviceUtilsMethods.getCurrentTimeStamp()}';
  }

  static int getTimeDifferenceInHrs(int? timestamp) {
    if (timestamp != null) {
      return _getTimeDifference(timestamp).inHours;
    }
    return getCurrentTimeStamp();
  }

  static int getTimeDifferenceInMins(int? timestamp) {
    if (timestamp != null) {
      return _getTimeDifference(timestamp).inMinutes;
    }
    return getCurrentTimeStamp();
  }

  static int getTimeDifferenceInDays(int? timestamp) {
    if (timestamp != null) {
      return _getTimeDifference(timestamp).inDays;
    }
    return getCurrentTimeStamp();
  }

  static Duration _getTimeDifference(int timestamp) {
    final currentDate = DateTime.now();
    final valueDate = DateTime.fromMicrosecondsSinceEpoch(timestamp);
    return currentDate.difference(valueDate);
  }
}
