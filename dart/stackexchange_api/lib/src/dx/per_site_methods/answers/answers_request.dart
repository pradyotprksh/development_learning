import 'package:stackexchange_api/core.dart';

final _logger = injectLogger('stackexchangeApi.dx.answers');

/// A mixin on [Core] which will handle or initiate the answers request to
/// StackExchangeApi.
mixin AnswersRequest on Core {
  /// Returns all the undeleted answers in the system.
  ///
  /// The sorts accepted by this method operate on the following fields of
  /// the answer object:
  ///
  /// activity – last_activity_date
  /// creation – creation_date
  /// votes – score
  /// activity is the default sort.
  ///
  /// It is possible to create moderately complex queries using sort, min,
  /// max, fromdate, and todate.
  ///
  /// This method returns a list of answers.
  ///
  /// Check [AllAnswers] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/answers](https://api.stackexchange.com/docs/answers)
  Future<Answers> getAllAnswers({
    int? page,
    int? pageSize,
    int? fromDate,
    int? toDate,
    Order? orderBy,
    int? minDate,
    int? maxDate,
    Sort? sortBy,
  }) async {
    Ensure(page != null && page < 0).isTrue('page should be > 0');
    Checker(pageSize != null && pageSize <= 0, _logger).isTrueWarning(
      'pageSize is <= 0. Will be getting empty data.',
    );
    Checker(fromDate != null && fromDate <= 0, _logger).isTrueWarning(
      'fromDate is <= 0. Will be getting empty data.',
    );
    Checker(toDate != null && toDate <= 0, _logger).isTrueWarning(
      'toDate is <= 0. Will be getting empty data.',
    );
    Checker(minDate != null && minDate <= 0, _logger).isTrueWarning(
      'minDate is <= 0. Will be getting empty data.',
    );
    Checker(maxDate != null && maxDate <= 0, _logger).isTrueWarning(
      'maxDate is <= 0. Will be getting empty data.',
    );
    Checker(minDate != null && maxDate != null && maxDate < minDate, _logger)
        .isTrueWarning(
      'minDate > maxDate. Will be getting empty data.',
    );
    Checker(fromDate != null && toDate != null && fromDate < toDate, _logger)
        .isTrueWarning(
      'toDate > fromDate. Will be getting empty data.',
    );

    final params = AllAnswers(
      page: page,
      pageSize: pageSize,
      fromDate: fromDate,
      toDate: toDate,
      orderBy: orderBy,
      minDate: minDate,
      maxDate: maxDate,
      sortBy: sortBy,
    );

    _logger.info('Getting all answers');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => Answers.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }
}
