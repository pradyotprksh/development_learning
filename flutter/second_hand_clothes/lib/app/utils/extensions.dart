import 'package:enum_to_string/enum_to_string.dart';

/// An extension on an object, this will be used to move the common
/// functionality to a single place rather that creating a new function
/// or redoing the same thing again on an object.
extension ObjectExtensions on Object {
  /// Convert an enum to string, removing the . from the enum value.
  String enumToString() => EnumToString.convertToString(this);
}

/// An extension on an String, this will be used to move the common
/// functionality to a single place rather that creating a new function
/// or redoing the same thing again on the String.
extension StringExtensions on String {
  /// Convert the string to enum. And it the enum values will be taken from
  /// [values].
  T? stringToEnum<T>(List<T> values) => EnumToString.fromString<T>(
    values,
    this,
  );
}
