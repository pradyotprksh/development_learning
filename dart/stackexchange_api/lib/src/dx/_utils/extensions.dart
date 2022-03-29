/// An extension on object, on enum basically.
extension EnumToString on Object {
  /// Convert enum to string.
  String enumToString() {
    final splitEnum = toString().split('.');
    if (splitEnum.length > 1 && splitEnum[0] == runtimeType.toString()) {
      return toString().split('.')[1];
    }
    return toString();
  }
}

/// An extension on list
extension JoinList on List {
  String semicolonJoin() {
    if (length > 0) {
      return join(';').toString();
    }
    return '';
  }
}
