import 'package:stackexchange_api/core.dart';
import 'package:test/test.dart';

import '../../variables.dart';

/// Testing [Credentials]
void main() {
  test(
    'should match credential details',
    () {
      expect(
        Variables.credentials.toString(),
        'Credentials = clientId: ${Variables.clientId}, clientSecret: '
        '${Variables.clientSecret} key:${Variables.key}',
      );
    },
  );
}
