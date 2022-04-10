import 'package:stackexchange_api/core.dart';
import 'package:test/test.dart';

/// Testing extensions.dart
void main() {
  final injector = injectLogger('extensions_test');

  test(
    'test enumToString',
    () {
      const orderType = Order.asc;
      injector.info(orderType);
      expect(orderType.toString(), 'Order.asc');

      final orderTypeString = orderType.enumToString();
      injector.info(orderTypeString);
      expect(orderTypeString, 'asc');
    },
  );

  test(
    'test semicolonJoin',
    () {
      const randList = ['a', 'b', 'c'];
      injector.info(randList);
      expect(randList.semicolonJoin(), 'a;b;c');
    },
  );
}
