import 'package:path_provider/path_provider.dart';

abstract class DeviceUtilsMethods {
  static int getCurrentTimeStamp() => DateTime.now().microsecondsSinceEpoch;

  static Future<String> getTempDirectoryPath() async {
    final tempDirectory = await getTemporaryDirectory();
    return '$tempDirectory-${DeviceUtilsMethods.getCurrentTimeStamp()}';
  }
}
