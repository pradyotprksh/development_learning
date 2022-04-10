import 'package:stackexchange_api/core.dart';
import 'package:stackexchange_api/src/default.dart';

abstract class Variables {
  static int clientId = 12345;
  static String? clientSecret = '123456';
  static String? key = '123456';

  static final credentials = Credentials(
    clientId: clientId,
    clientSecret: clientSecret,
    key: key,
  );

  static final stackExchangeApi = StackExchangeApi(
    credentials: credentials,
  );
}
