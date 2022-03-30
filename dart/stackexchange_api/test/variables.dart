import 'package:stackexchange_api/core.dart';

abstract class Variables {
  static int clientId = 12345;
  static String? clientSecret = '123456';
  static String? key = '123456';

  static final credentials = Credentials(
    clientId: clientId,
    clientSecret: clientSecret,
    key: key,
  );
}
