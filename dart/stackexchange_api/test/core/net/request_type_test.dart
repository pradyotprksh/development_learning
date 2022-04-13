import 'package:stackexchange_api/core.dart';
import 'package:test/expect.dart';
import 'package:test/scaffolding.dart';

/// Testing [RequestType]
void main() {
  group(
    'testing GET request type',
    () {
      const requestType = RequestType.get;

      test(
        'GET connect time out',
        () {
          expect(requestType.connectTimeout, 10000);
        },
      );

      test(
        'GET method',
        () {
          expect(requestType.method, 'GET');
        },
      );

      test(
        'GET receive timeout',
        () {
          expect(requestType.receiveTimeout, 10000);
        },
      );

      test(
        'GET send timeout',
        () {
          expect(requestType.sendTimeout, 10000);
        },
      );
    },
  );

  group(
    'testing POST request type',
    () {
      const requestType = RequestType.post;

      test(
        'POST connect time out',
        () {
          expect(requestType.connectTimeout, 10000);
        },
      );

      test(
        'POST method',
        () {
          expect(requestType.method, 'POST');
        },
      );

      test(
        'POST receive timeout',
        () {
          expect(requestType.receiveTimeout, 10000);
        },
      );

      test(
        'POST send timeout',
        () {
          expect(requestType.sendTimeout, 10000);
        },
      );
    },
  );
}
