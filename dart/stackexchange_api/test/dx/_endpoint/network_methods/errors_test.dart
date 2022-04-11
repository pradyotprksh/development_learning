import 'package:stackexchange_api/core.dart';
import 'package:test/test.dart';

/// Testing [AllErrors] and [ErrorDetails]
void main() {
  group(
    'all errors test cases',
    () {
      final allErrorsNoParameter = AllErrors();
      final allErrorsWithOnlyPage = AllErrors(page: 1);
      final allErrorsWithOnlyPageSize = AllErrors(pageSize: 2);
      final allErrors = AllErrors(page: 1, pageSize: 2);

      test(
        'with no parameters',
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
        'with only page',
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
        'with only pageSize',
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
            allErrorsWithOnlyPageSize
                .toRequest()
                .uri
                ?.queryParameters['pagesize'],
            '2',
          );
          expect(
            allErrorsWithOnlyPageSize.toRequest().uri?.queryParameters['page'],
            null,
          );
        },
      );

      test(
        'with all parameters',
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

  group(
    'error details test cases',
    () {
      final errorDetails = ErrorDetails(id: 400);

      test(
        'with a new instance',
        () {
          expect(
            errorDetails.toRequest().uri?.path,
            '/2.3/errors/${errorDetails.id}',
          );
          expect(
            errorDetails.toRequest().uri?.queryParameters.length,
            0,
          );
        },
      );
    },
  );
}
