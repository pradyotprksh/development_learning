import 'package:stackexchange_api/core.dart';
import 'package:test/test.dart';

/// Testing [AllErrors] and [ErrorDetails]
void main() {
  group(
    'AllErrors test cases',
    () {
      final allErrorsNoParameter = AllErrors();
      final allErrorsWithOnlyPage = AllErrors(page: 1);
      final allErrorsWithOnlyPageSize = AllErrors(pageSize: 2);
      final allErrors = AllErrors(page: 1, pageSize: 2);

      test(
        'all errors with no parameters',
        () {
          expect(
            allErrorsNoParameter.toRequest().uri?.path,
            '/2.3/errors',
          );
          expect(
            allErrorsNoParameter.toRequest().uri?.queryParameters.length,
            0,
          );
        },
      );

      test(
        'all errors with only page',
            () {
          expect(
            allErrorsWithOnlyPage.toRequest().uri?.path,
            '/2.3/errors',
          );
          expect(
            allErrorsWithOnlyPage.toRequest().uri?.queryParameters.length,
            1,
          );
          expect(
            allErrorsWithOnlyPage.toRequest().uri?.queryParameters['page'],
            '1',
          );
          expect(
            allErrorsWithOnlyPage.toRequest().uri?.queryParameters['pagesize'],
            null,
          );
        },
      );

      test(
        'all errors with only pageSize',
            () {
          expect(
            allErrorsWithOnlyPageSize.toRequest().uri?.path,
            '/2.3/errors',
          );
          expect(
            allErrorsWithOnlyPageSize.toRequest().uri?.queryParameters.length,
            1,
          );
          expect(
            allErrorsWithOnlyPageSize.toRequest().uri?.queryParameters['pagesize'],
            '2',
          );
          expect(
            allErrorsWithOnlyPageSize.toRequest().uri?.queryParameters['page'],
            null,
          );
        },
      );

      test(
        'all errors with all parameters',
            () {
          expect(
            allErrors.toRequest().uri?.path,
            '/2.3/errors',
          );
          expect(
            allErrors.toRequest().uri?.queryParameters.length,
            2,
          );
          expect(
            allErrors.toRequest().uri?.queryParameters['pagesize'],
            '2',
          );
          expect(
            allErrors.toRequest().uri?.queryParameters['page'],
            '1',
          );
        },
      );
    },
  );
}
