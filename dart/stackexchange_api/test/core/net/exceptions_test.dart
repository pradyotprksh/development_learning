import 'package:stackexchange_api/core.dart';
import 'package:test/test.dart';

/// Testing core net exceptions
void main() {
  RequestCancelException? _requestCancelException;
  RequestOtherException? _requestOtherException;
  RequestTimeoutException? _requestTimeoutException;

  group(
    'testing RequestCancelException',
    () {
      test(
        'will throw RequestCancelException',
        () {
          expect(
            raiseRequestCancelException,
            throwsA(
              isA<RequestCancelException>(),
            ),
          );
        },
      );

      test(
        '_requestCancelException will not be null',
        () {
          try {
            raiseRequestCancelException();
          } on RequestCancelException catch (e) {
            _requestCancelException = e;
          }

          expect(_requestCancelException != null, true);
        },
      );

      test(
        '_requestCancelException will have correct message',
        () {
          expect(_requestCancelException?.message, Constants.requestCancelled);
        },
      );
    },
  );

  group(
    'testing RequestOtherException',
        () {
      test(
        'will throw RequestOtherException',
            () {
          expect(
            raiseRequestOtherException,
            throwsA(
              isA<RequestOtherException>(),
            ),
          );
        },
      );

      test(
        '_requestOtherException will not be null',
            () {
          try {
            raiseRequestOtherException();
          } on RequestOtherException catch (e) {
            _requestOtherException = e;
          }

          expect(_requestOtherException != null, true);
        },
      );

      test(
        '_requestOtherException will have correct message',
            () {
          expect(_requestOtherException?.message, '${Constants.requestFailed} (null)');
        },
      );
    },
  );

  group(
    'testing RequestTimeoutException',
        () {
      test(
        'will throw RequestTimeoutException',
            () {
          expect(
            raiseRequestTimeoutException,
            throwsA(
              isA<RequestTimeoutException>(),
            ),
          );
        },
      );

      test(
        '_requestTimeoutException will not be null',
            () {
          try {
            raiseRequestTimeoutException();
          } on RequestTimeoutException catch (e) {
            _requestTimeoutException = e;
          }

          expect(_requestTimeoutException != null, true);
        },
      );

      test(
        '_requestTimeoutException will have correct message',
            () {
          expect(_requestTimeoutException?.message, Constants.requestTimeout);
        },
      );
    },
  );
}

void raiseRequestCancelException() {
  throw RequestCancelException();
}

void raiseRequestOtherException() {
  throw RequestOtherException();
}

void raiseRequestTimeoutException() {
  throw RequestTimeoutException();
}
