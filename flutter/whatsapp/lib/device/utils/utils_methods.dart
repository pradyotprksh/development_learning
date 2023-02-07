import 'package:path_provider/path_provider.dart';

abstract class DeviceUtilsMethods {
  static int getCurrentTimeStamp() => DateTime.now().microsecondsSinceEpoch;

  static String getCurrentDateWithCurrentHour() =>
      '${DateTime.now().day}-${DateTime.now().month}-${DateTime.now().year}||${DateTime.now().hour}hr';

  static Future<String> getTempDirectoryPath() async {
    final tempDirectory = await getTemporaryDirectory();
    return '$tempDirectory-${DeviceUtilsMethods.getCurrentTimeStamp()}';
  }

  static int getTimeDifference(int? timestamp) {
    if (timestamp != null) {
      final currentDate = DateTime.now();
      final valueDate = DateTime.fromMicrosecondsSinceEpoch(timestamp);
      final difference = currentDate.difference(valueDate);
      return difference.inHours;
    }
    return getCurrentTimeStamp();
  }
}
