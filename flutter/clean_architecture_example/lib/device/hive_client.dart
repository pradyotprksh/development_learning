import 'package:hive/hive.dart';
import 'package:pet_perfect_assignemnt/constants.dart';

/// A client class for Hive which will give us a box to be used
/// to save the details.
class HiveClient {
  factory HiveClient() => _instance;

  HiveClient._privateConstructor();

  static final HiveClient _instance = HiveClient._privateConstructor();

  final hiveBox = createHiveBox();

  static Box<String> createHiveBox() => Hive.box<String>(Constants().appName);
}
