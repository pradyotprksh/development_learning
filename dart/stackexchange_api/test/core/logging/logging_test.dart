import 'package:stackexchange_api/core.dart';
import 'package:test/test.dart';

/// Testing [ILogger]
void main() {
  test(
    'should return the logger from Zone.current',
    () async {
      var logger = FakeLogger();

      await provideLogger(
        logger,
        () async {
          injectLogger('test.logger').info('test');
        },
      );

      expect(logger.messages, equals(['test']));
    },
  );

  test(
    'should return the PrinterLogger if run without provideLogger',
    () {
      var lazyLogger = injectLogger('some.scope');

      expect(lazyLogger.logger, isA<Log>());

      expect(
        () {
          lazyLogger.info('ignored message');
        },
        returnsNormally,
      );
    },
  );
}

/// A fake logger class which will be used for test purposes.
class FakeLogger extends ILogger {
  List<String> messages = [];

  @override
  ILogger get(String scope) => this;

  @override
  void log(int level, dynamic message) {
    messages.add(message as String);
  }
}
