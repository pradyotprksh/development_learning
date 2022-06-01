/// A constant class for data layer, which will have all the constants
/// required in the layer.
class UtilsDataConstants {
  factory UtilsDataConstants() => _instance;

  UtilsDataConstants._privateConstructor();

  static final UtilsDataConstants _instance = UtilsDataConstants._privateConstructor();

  final String firestoreCollectionUsers = 'users';
}