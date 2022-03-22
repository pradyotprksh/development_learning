import 'package:flublog/constants.dart';
import 'package:hive/hive.dart';

/// An abstract class for creating a service for database, which will
/// be used to manage the local storage.
abstract class DBService {
  /// Initialize the device layer, like creating the device space for storing
  /// data and adding encryption to it.
  ///
  /// [isTest] will be used while adding test for this functionality, if true
  /// then few of the functionality can be ignored in test mode
  Future<void> initializeService({bool isTest = false});

  /// Get the local storage box, which is opened while initializing the
  /// service.
  ///
  /// [storageName] is the name of the storage box which is needed, by default
  /// it's always [Constants.appName]
  Box getStorage({String storageName = Constants.appName});
}