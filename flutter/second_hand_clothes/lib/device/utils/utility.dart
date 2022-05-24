import 'package:flutter/services.dart';

/// A utility class for device layer which will contain the common
/// functionality required in the device layer.
class DeviceUtility {
  factory DeviceUtility() => _instance;

  DeviceUtility._privateConstructor();

  static final DeviceUtility _instance = DeviceUtility._privateConstructor();

  /// Get file data which is in json format, the path of the file will be
  /// stored in [filePath].
  Future<String> getJSONData(String filePath) async =>
      await rootBundle.loadString(filePath);
}