import 'package:stackexchange_api/core.dart';
import 'package:test/test.dart';

import '../variables.dart';

/// Testing [NetworkingModule]
void main() {
  final networkModule = Variables.stackExchangeApi.networking;

  injectLogger('networking_test').info(networkModule.getOrigin());

  test(
    'test URI scheme',
    () {
      expect(
        networkModule.getOrigin().scheme,
        'https',
      );
    },
  );

  test(
    'test URI host',
    () {
      expect(
        networkModule.getOrigin().host,
        'api.stackexchange.com',
      );
    },
  );

  test(
    'test URI queryParameters',
    () {
      expect(
        networkModule.getOrigin().queryParameters,
        <String, String>{
          'key': '123456',
        },
      );
    },
  );
}
