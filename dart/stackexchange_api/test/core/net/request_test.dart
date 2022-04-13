import 'package:stackexchange_api/core.dart';
import 'package:test/test.dart';

import '../../variables.dart';

/// Testing [Request]
void main() {
  final _lazyLogger = injectLogger('request_test');

  test(
    'GET request details should match',
    () {
      final _request = Request.get(
        uri: Uri(
          scheme: 'https',
          host: 'api.stackexchange.com',
          queryParameters: <String, dynamic>{
            if (Variables.credentials.key != null)
              'key': Variables.credentials.key,
          },
        ),
        headers: <String, String>{
          'key_1': 'value_1',
          'key_2': 'value_2',
        },
      );

      _lazyLogger.info(_request.toString());

      expect(
        _request.toString(),
        'Request { [${_request.type}] ${_request.uri} }',
      );

      expect(
        _request.type,
        RequestType.get,
      );
    },
  );

  test(
    'POST request details should match',
    () {
      final _request = Request.post(
        uri: Uri(
          scheme: 'https',
          host: 'api.stackexchange.com',
        ),
      );

      _lazyLogger.info(_request.toString());

      expect(
        _request.toString(),
        'Request { [${_request.type}] ${_request.uri} }',
      );

      expect(
        _request.type,
        RequestType.post,
      );

      expect(
        _request.headers,
        null,
      );
    },
  );
}
